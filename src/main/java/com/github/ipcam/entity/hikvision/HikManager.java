package com.github.ipcam.entity.hikvision;


import com.github.ipcam.entity.LongStructure;
import com.github.ipcam.entity.NVRChannelInfo;
import com.github.ipcam.entity.NetworkCameraContext;
import com.github.ipcam.entity.exception.CameraConnectionException;
import com.github.ipcam.entity.exception.HikException;
import com.sun.jna.ptr.IntByReference;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * HikManager
 *
 * @author echils
 * @since 2018/12/19 18:04
 */
public class HikManager {

    private static NumberFormat numberFormat = NumberFormat.getNumberInstance();

    /**
     * Find video files from camera
     */
    public static int findRecord(int userHandle, String channelNum, Date startDate, Date endDate) {
        int status;
        NET_DVR_FIND_DATA net_dvr_find_data = new NET_DVR_FIND_DATA();
        NET_DVR_TIME startTime = transferTime(startDate);
        NET_DVR_TIME endTime = transferTime(endDate);
        net_dvr_find_data.struStartTime = startTime;
        net_dvr_find_data.struStopTime = endTime;
        int findHandle = HCNetSDK.hcNetSDK.NET_DVR_FindFile(userHandle, handleChannel(channelNum), 0, startTime, endTime);
        if (findHandle == -1) {
            throw new HikException(getErrorMsg());
        } else {
            while (true) {
                status = HCNetSDK.hcNetSDK.NET_DVR_FindNextFile(findHandle, net_dvr_find_data);
                if (status == NetworkCameraContext.NET_DVR_FILE_SUCCESS) {
                    break;
                } else {
                    if (status == NetworkCameraContext.NET_DVR_ISFINDING) {
                        continue;
                    } else {
                        if (status == NetworkCameraContext.NET_DVR_FILE_NOFIND) {
                            break;
                        } else {
                            boolean flag = HCNetSDK.hcNetSDK.NET_DVR_FindClose_V30(findHandle);
                            if (!flag) {
                                throw new HikException(getErrorMsg());
                            }
                            throw new HikException(getErrorMsg());
                        }
                    }
                }
            }
        }
        return status;
    }


    /**
     * Conversion time type
     */
    public static NET_DVR_TIME transferTime(Date date) {
        if (date == null) {
            throw new CameraConnectionException("Time is null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        NET_DVR_TIME netDvrTime = new NET_DVR_TIME();
        netDvrTime.dwYear = calendar.get(Calendar.YEAR);
        netDvrTime.dwMonth = calendar.get(Calendar.MONTH) + 1;
        netDvrTime.dwDay = calendar.get(Calendar.DAY_OF_MONTH);
        netDvrTime.dwHour = calendar.get(Calendar.HOUR_OF_DAY);
        netDvrTime.dwMinute = calendar.get(Calendar.MINUTE);
        netDvrTime.dwSecond = calendar.get(Calendar.SECOND);
        return netDvrTime;
    }


    public static NVRChannelInfo convert(Object channelInfo) {
        NVRChannelInfo nvrChannel = new NVRChannelInfo();
        try {
            Map<String, Object> channelInfoParam = (Map) channelInfo;
            nvrChannel.setChannelNo(String.valueOf(channelInfoParam.get("id")));
            nvrChannel.setChannelName(String.valueOf(channelInfoParam.get("name")));

            Map<String, Object> sourceInputPortDescriptor
                    = (Map<String, Object>) channelInfoParam.get("sourceInputPortDescriptor");
            nvrChannel.setIpv4(String.valueOf(sourceInputPortDescriptor.get("ipAddress")));
            nvrChannel.setManagerPort(Integer.parseInt(sourceInputPortDescriptor.get("managePortNo").toString()));
            nvrChannel.setUsername(String.valueOf(sourceInputPortDescriptor.get("userName")));
            nvrChannel.setProxyProtocol(String.valueOf(sourceInputPortDescriptor.get("proxyProtocol")));
            nvrChannel.setDeviceChannelNo(String.valueOf(sourceInputPortDescriptor.get("srcInputPort")));
            nvrChannel.setStreamType(String.valueOf(sourceInputPortDescriptor.get("streamType")));
            nvrChannel.setAddressFormatType(String.valueOf(sourceInputPortDescriptor.get("addressingFormatType")));
        } catch (NumberFormatException e) {
            throw new CameraConnectionException(e);
        }
        return nvrChannel;
    }


    /**
     * get video reference
     *
     * @return
     */
    public static NET_DVR_RECORD_V30 getDWSize(int userHandle, String channelNum) {
        IntByReference intByReference = new IntByReference(0);
        NET_DVR_RECORD_V30 dvrRecord = new NET_DVR_RECORD_V30();
        dvrRecord.write();
        boolean result = HCNetSDK.hcNetSDK.NET_DVR_GetDVRConfig(userHandle, NetworkCameraContext.NET_DVR_GET_RECORDCFG_V30,
                handleChannel(channelNum), dvrRecord.getPointer(), dvrRecord.size(), intByReference);
        dvrRecord.read();
        if (!result) {
            throw new HikException(getErrorMsg());
        }
        return dvrRecord;
    }

    /**
     * handle the chanelNum to get the realChanelNum
     * <p>
     * the "A" ,"I","D" Used to distinguish between different types of cameras
     * example：channelNum= 'D10',  After deal with  get the realChanelNum = '10';
     *
     * @param channelNum
     * @return
     */
    public static int handleChannel(String channelNum) {
        if (channelNum.charAt(0) == 'A') {
            return Integer.parseInt(channelNum.substring(1));
        } else if (channelNum.charAt(0) == 'I') {
            return Integer.parseInt(channelNum.substring(1));
        } else if (channelNum.charAt(0) == 'D') {
            return Integer.parseInt(channelNum.substring(1)) + 32;
        } else {
            throw new HikException("Error code：[null],Error msg: [channelNum error]");
        }
    }

    /**
     * Get exception code and message
     *
     * @return
     */
    public static String getErrorMsg() {
        int i = HCNetSDK.hcNetSDK.NET_DVR_GetLastError();
        return "Error code：[" + i + "],Error msg: [" + HCNetSDK.hcNetSDK.NET_DVR_GetErrorMsg(new LongStructure(i).getPointer()) + "]";
    }


    /**
     * Format the PTZ data
     *
     * @param position
     * @return
     */
    public static PTZPosition handlePTZ(PTZPosition position, boolean beSet) {
        if (position != null) {
            if (beSet) {
                position.panPos = Short.parseShort(String.valueOf(position.panPos), 16);
                if (position.tiltPos >= -900 && position.tiltPos < 0) {
                    position.tiltPos = (short) (3600 + position.tiltPos);
                }
                position.tiltPos = Short.parseShort(String.valueOf(position.tiltPos), 16);
                position.zoomPos = Short.parseShort(String.valueOf(position.zoomPos), 16);
            } else {

                short tiltTemp = Short.parseShort(intToHex(position.tiltPos).equals("") ? "0" : intToHex(position.tiltPos));
                if (tiltTemp >= 2700) {
                    position.tiltPos = (short) (tiltTemp - 3600);
                } else {
                    position.tiltPos = tiltTemp;
                }
                position.panPos = Short.parseShort(intToHex(position.panPos).equals("") ? "0" : intToHex(position.panPos));
                position.zoomPos = Short.parseShort(intToHex(position.zoomPos).equals("") ? "0" : intToHex(position.zoomPos));
            }
        }
        return position;
    }

    /**
     * Decimal to hexadecimal
     *
     * @param var1
     * @return
     */
    public static String intToHex(int var1) {
        StringBuilder var2 = new StringBuilder(8);
        char[] var3 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (var1 != 0) {
            var2 = var2.append(var3[var1 % 16]);
            var1 = var1 / 16;
        }
        return var2.reverse().toString();
    }

//    /**
//     * data filter
//     *
//     * @param thermometry
//     * @return
//     */
//    public static Temperature convert(NET_DVR_THERMOMETRY_UPLOAD thermometry) {
//
//        Box box = new Box();
//        Temperature temperature = new Temperature();
//        temperature.setRuleId(String.valueOf(thermometry.byRuleID));
//
//        byte type = thermometry.byRuleCalibType;
//        temperature.setRuleType(String.valueOf(type));
//
//        if (type == 0) {
//
//            temperature.setMaxTemperature(thermometry.struPointThermCfg.fTemperature);
//            temperature.setMinTemperature(thermometry.struPointThermCfg.fTemperature);
//
//            NET_VCA_POINT point = thermometry.struPointThermCfg.struPoint;
//
//            box.setXmin(point.fX);
//            box.setXmax(point.fX);
//            box.setYmin(point.fY);
//            box.setYmax(point.fY);
//
//            temperature.setBox(box);
//        } else {
//
//            temperature.setMaxTemperature(thermometry.struLinePolygonThermCfg.fMaxTemperature);
//            temperature.setMinTemperature(thermometry.struLinePolygonThermCfg.fMinTemperature);
//
//            NET_VCA_POINT[] points = thermometry.struLinePolygonThermCfg.struRegion.struPos;
//            int pointNum = thermometry.struLinePolygonThermCfg.struRegion.dwPointNum;
//
//            List<Float> abscissa = new ArrayList<>();
//            List<Float> ordinate = new ArrayList<>();
//
//            for (int i = 0; i < points.length; i++) {
//                abscissa.add(points[i].fX);
//                ordinate.add(points[i].fY);
//            }
//
//            int abscissaNum = abscissa.size();
//            int ordinateNum = ordinate.size();
//
//            Collections.sort(abscissa);
//            Collections.sort(ordinate);
//
//            abscissa = abscissa.subList(abscissaNum - pointNum, abscissaNum);
//            ordinate = ordinate.subList(ordinateNum - pointNum, ordinateNum);
//
//            box.setXmin(abscissa.get(0));
//            box.setXmax(abscissa.get(abscissa.size() - 1));
//            box.setYmin(ordinate.get(0));
//            box.setYmax(ordinate.get(ordinate.size() - 1));
//
//            temperature.setBox(box);
//        }
//
//        return temperature;
//
//    }


    /**
     * get camera config
     *
     * @param userHandle
     * @param channelNum
     * @return
     */
    public static NET_DVR_CAMERAPARAMCFG_EX getCameraConfig(int userHandle, String channelNum) {
        NET_DVR_CAMERAPARAMCFG_EX param = new NET_DVR_CAMERAPARAMCFG_EX();
        param.write();
        int size = param.size();
        boolean getDVRConfig = HCNetSDK.hcNetSDK.NET_DVR_GetDVRConfig(userHandle, NetworkCameraContext.NET_DVR_CAMERAPARAMCFG_EX_GET, handleChannel(channelNum),
                param.getPointer(), size, new IntByReference(param.size()));
        param.read();
        if (!getDVRConfig) {
            throw new HikException(getErrorMsg());
        }
        return param;
    }


    /**
     * get camera config
     *
     * @param userHandle
     * @param channelNum
     * @return
     */
    public static NET_DVR_AEMODECFG getExposureConfig(int userHandle, String channelNum) {

        NET_DVR_AEMODECFG param = new NET_DVR_AEMODECFG();
        param.write();
        int size = param.size();
        boolean getDVRConfig = HCNetSDK.hcNetSDK.NET_DVR_GetDVRConfig(userHandle, NetworkCameraContext.NET_DVR_AEMODECFG_GET, handleChannel(channelNum),
                param.getPointer(), size, new IntByReference(param.size()));
        param.read();
        if (!getDVRConfig) {
            throw new HikException(getErrorMsg());
        }
        return param;
    }


    /**
     * get camera config
     *
     * @param userHandle
     * @param channelNum
     * @return
     */
    public static NET_DVR_COMPRESSIONCFG_V30 getCompressionConfig(int userHandle, String channelNum) {

        NET_DVR_COMPRESSIONCFG_V30 compression = new NET_DVR_COMPRESSIONCFG_V30();
        compression.write();
        boolean get = HCNetSDK.hcNetSDK.NET_DVR_GetDVRConfig(userHandle, NetworkCameraContext.NET_DVR_COMPRESSIONCFG_V30_GET, handleChannel(channelNum),
                compression.getPointer(), compression.size(), new IntByReference());
        compression.read();
        if (!get) {
            throw new HikException(getErrorMsg());
        }
        return compression;
    }


    /**
     * get audio input config
     *
     * @param userHandle
     * @param channelNum
     * @return
     */
    public static NET_DVR_AUDIO_INPUT_PARAM getAudioInputConfig(int userHandle, String channelNum) {

        NET_DVR_AUDIO_INPUT_PARAM param = new NET_DVR_AUDIO_INPUT_PARAM();
        param.write();
        boolean get = HCNetSDK.hcNetSDK.NET_DVR_GetDVRConfig(userHandle, NetworkCameraContext.NET_DVR_AUDIO_INPUT_PARAM_GET, handleChannel(channelNum),
                param.getPointer(), param.size(), new IntByReference());
        param.read();
        if (!get) {
            throw new HikException(getErrorMsg());
        }
        return param;
    }

    /**
     * 获取红外摄像头制定预置点下的信息
     *
     * @param userHandle
     * @param presetNo
     * @return
     */
    public static NET_DVR_THERMOMETRY_PRESETINFO getInfraredInfo(int userHandle, int presetNo) {

        LPNET_DVR_STD_CONFIG stdConfig = new LPNET_DVR_STD_CONFIG();
        NET_DVR_THERMOMETRY_COND cond = new NET_DVR_THERMOMETRY_COND();
        NET_DVR_THERMOMETRY_PRESETINFO info = new NET_DVR_THERMOMETRY_PRESETINFO();

        //lpCondBuffer config
        cond.dwSize = cond.size();
        cond.dwChannel = 2;
        cond.wPresetNo = (short) presetNo;
        stdConfig.lpCondBuffer = cond.getPointer();
        stdConfig.dwCondSize = cond.size();
        cond.write();

        //lpOutBuffer
        info.dwSize = info.size();
        info.wPresetNo = (short) presetNo;
        stdConfig.dwOutSize = info.size();
        info.write();
        stdConfig.lpOutBuffer = info.getPointer();

        stdConfig.write();
        boolean config = HCNetSDK.hcNetSDK.NET_DVR_GetSTDConfig(userHandle, NetworkCameraContext.NET_DVR_GET_THERMOMETRY_PRESETINFO, stdConfig.getPointer());
        stdConfig.read();
        info.read();

        if (!config) {
            throw new HikException(getErrorMsg());
        }
        return info;
    }

//    /**
//     * xml参数转换
//     *
//     * @param channelInfo
//     * @return
//     */
//    public static NVRChannelInfo convertChannelInfo(Object channelInfo) {
//        Map<String, Object> channelInfoParam = (Map) channelInfo;
//        NVRChannelInfo nvrChannel = new NVRChannelInfo();
//        nvrChannel.setChannelNo("D"+channelInfoParam.get("id"));
//        nvrChannel.setChannelName(String.valueOf(channelInfoParam.get("name")));
//
//        Map<String, Object> sourceInputPortDescriptor = (Map<String, Object>) channelInfoParam.get("sourceInputPortDescriptor");
//        nvrChannel.setIpv4(String.valueOf(sourceInputPortDescriptor.get("ipAddress")));
//        nvrChannel.setManagerPort(Integer.parseInt(sourceInputPortDescriptor.get("managePortNo").toString()));
//        nvrChannel.setUsername(String.valueOf(sourceInputPortDescriptor.get("userName")));
//        nvrChannel.setProxyProtocol(String.valueOf(sourceInputPortDescriptor.get("proxyProtocol")));
//        nvrChannel.setDeviceChannelNo("A" + sourceInputPortDescriptor.get("srcInputPort"));
//        nvrChannel.setStreamType(String.valueOf(sourceInputPortDescriptor.get("streamType")));
//        nvrChannel.setAddressFormatType(String.valueOf(sourceInputPortDescriptor.get("addressingFormatType")));
//
//        return nvrChannel;
//    }

    /**
     * byte[] to string with charset
     *
     * @param buffer
     * @param charsetName
     * @return
     */
    public static String byteToStr(byte[] buffer, String charsetName) {
        try {
            int length = 0;
            for (int i = 0; i < buffer.length; ++i) {
                if (buffer[i] == 0) {
                    length = i;
                    break;
                }
            }
            return new String(buffer, 0, length, charsetName);
        } catch (Exception e) {
            throw new HikException("charset convert failed");
        }
    }

    /**
     * number format
     *
     * @param source
     * @param num
     * @return
     */
    public static Double numFormat(Float source, int num) {
        numberFormat.setMaximumFractionDigits(num);
        numberFormat.setRoundingMode(RoundingMode.UP);
        return Double.valueOf(numberFormat.format(source));
    }
}
