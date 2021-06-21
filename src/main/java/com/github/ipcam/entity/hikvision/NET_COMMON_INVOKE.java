package com.github.ipcam.entity.hikvision;


import com.github.ipcam.entity.Temperature;
import com.github.ipcam.entity.comm.LONG_STRUCTURE;
import com.github.ipcam.entity.comm.STRUCTURE_CONTEXT;
import com.github.ipcam.entity.exception.CameraConnectionException;
import com.github.ipcam.entity.exception.HikException;
import com.github.ipcam.entity.infos.NVRChannelInfo;
import com.sun.jna.ptr.IntByReference;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

/**
 * NET_COMMON_INVOKE
 *
 * @author echils
 */
public class NET_COMMON_INVOKE {


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
                if (status == STRUCTURE_CONTEXT.NET_DVR_FILE_SUCCESS) {
                    break;
                } else {
                    if (status == STRUCTURE_CONTEXT.NET_DVR_ISFINDING) {
                        continue;
                    } else {
                        if (status == STRUCTURE_CONTEXT.NET_DVR_FILE_NOFIND) {
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

    /**
     * Convert object to NVRChannelInfo
     */
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
        } catch (NumberFormatException e) {
            throw new CameraConnectionException(e);
        }
        return nvrChannel;
    }

    /**
     * handle the chanelNum to get the realChanelNum
     * <p>
     * the "A" ,"I","D" Used to distinguish between different types of cameras
     * example：channelNum= 'D10',  After deal with  get the realChanelNum = '10';
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
     */
    public static String getErrorMsg() {
        int i = HCNetSDK.hcNetSDK.NET_DVR_GetLastError();
        return "Error code：[" + i + "],Error msg: [" + HCNetSDK.hcNetSDK.NET_DVR_GetErrorMsg(new LONG_STRUCTURE(i).getPointer()) + "]";
    }


    /**
     * Format the PTZ data
     */
    public static PTZ_POSITION_STRUCTURE handlePTZ(PTZ_POSITION_STRUCTURE position, boolean beSet) {
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

    /**
     * data filter
     */
    public static Temperature convert(NET_DVR_THERMOMETRY_UPLOAD thermometry) {

        Temperature temperature = new Temperature();
        temperature.setInfraredNo(thermometry.byRuleID);

        byte type = thermometry.byRuleCalibType;

        if (type == 0) {
            temperature.setMaxTemperature(thermometry.struPointThermCfg.fTemperature);
            temperature.setMinTemperature(thermometry.struPointThermCfg.fTemperature);
            NET_VCA_POINT point = thermometry.struPointThermCfg.struPoint;
            Temperature.Region region = new Temperature.Region(point.fX, point.fY);
            temperature.setRegions(new Temperature.Region[]{region, region, region, region});
        } else {

            temperature.setMaxTemperature(thermometry.struLinePolygonThermCfg.fMaxTemperature);
            temperature.setMinTemperature(thermometry.struLinePolygonThermCfg.fMinTemperature);

            NET_VCA_POINT[] points = thermometry.struLinePolygonThermCfg.struRegion.struPos;
            int pointNum = thermometry.struLinePolygonThermCfg.struRegion.dwPointNum;

            List<Float> abscissa = new ArrayList<>();
            List<Float> ordinate = new ArrayList<>();

            for (int i = 0; i < points.length; i++) {
                abscissa.add(points[i].fX);
                ordinate.add(points[i].fY);
            }

            int abscissaNum = abscissa.size();
            int ordinateNum = ordinate.size();

            Collections.sort(abscissa);
            Collections.sort(ordinate);

            abscissa = abscissa.subList(abscissaNum - pointNum, abscissaNum);
            ordinate = ordinate.subList(ordinateNum - pointNum, ordinateNum);

            Float xMin = abscissa.get(0);
            Float xMax = abscissa.get(abscissa.size() - 1);
            Float yMin = ordinate.get(0);
            Float yMax = ordinate.get(ordinate.size() - 1);
            Temperature.Region regionA = new Temperature.Region(xMin, yMax);
            Temperature.Region regionB = new Temperature.Region(xMax, yMax);
            Temperature.Region regionC = new Temperature.Region(xMin, yMin);
            Temperature.Region regionD = new Temperature.Region(xMax, yMin);

            temperature.setRegions(new Temperature.Region[]{regionA, regionB, regionC, regionD});
        }

        return temperature;

    }


    /**
     * get camera config
     */
    public static NET_DVR_CAMERAPARAMCFG_EX getCameraConfig(int userHandle, String channelNum) {
        NET_DVR_CAMERAPARAMCFG_EX param = new NET_DVR_CAMERAPARAMCFG_EX();
        param.write();
        int size = param.size();
        boolean getDVRConfig = HCNetSDK.hcNetSDK.NET_DVR_GetDVRConfig(userHandle,
                STRUCTURE_CONTEXT.NET_DVR_CAMERAPARAMCFG_EX_GET, handleChannel(channelNum),
                param.getPointer(), size, new IntByReference(param.size()));
        param.read();
        if (!getDVRConfig) {
            throw new HikException(getErrorMsg());
        }
        return param;
    }


    /**
     * get camera config
     */
    public static NET_DVR_AEMODECFG getExposureConfig(int userHandle, String channelNum) {

        NET_DVR_AEMODECFG param = new NET_DVR_AEMODECFG();
        param.write();
        int size = param.size();
        boolean getDVRConfig = HCNetSDK.hcNetSDK.NET_DVR_GetDVRConfig(userHandle,
                STRUCTURE_CONTEXT.NET_DVR_AEMODECFG_GET, handleChannel(channelNum),
                param.getPointer(), size, new IntByReference(param.size()));
        param.read();
        if (!getDVRConfig) {
            throw new HikException(getErrorMsg());
        }
        return param;
    }


    /**
     * get camera config
     */
    public static NET_DVR_COMPRESSIONCFG_V30 getCompressionConfig(int userHandle, String channelNum) {

        NET_DVR_COMPRESSIONCFG_V30 compression = new NET_DVR_COMPRESSIONCFG_V30();
        compression.write();
        boolean get = HCNetSDK.hcNetSDK.NET_DVR_GetDVRConfig(userHandle,
                STRUCTURE_CONTEXT.NET_DVR_COMPRESSIONCFG_V30_GET, handleChannel(channelNum),
                compression.getPointer(), compression.size(), new IntByReference());
        compression.read();
        if (!get) {
            throw new HikException(getErrorMsg());
        }
        return compression;
    }


    /**
     * get audio input config
     */
    public static NET_DVR_AUDIO_INPUT_PARAM getAudioInputConfig(int userHandle, String channelNum) {

        NET_DVR_AUDIO_INPUT_PARAM param = new NET_DVR_AUDIO_INPUT_PARAM();
        param.write();
        boolean get = HCNetSDK.hcNetSDK.NET_DVR_GetDVRConfig(userHandle,
                STRUCTURE_CONTEXT.NET_DVR_AUDIO_INPUT_PARAM_GET, handleChannel(channelNum),
                param.getPointer(), param.size(), new IntByReference());
        param.read();
        if (!get) {
            throw new HikException(getErrorMsg());
        }
        return param;
    }

    /**
     * Get the information under the setting point of the infrared camera
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
        boolean config = HCNetSDK.hcNetSDK.NET_DVR_GetSTDConfig(userHandle,
                STRUCTURE_CONTEXT.NET_DVR_GET_THERMOMETRY_PRESETINFO, stdConfig.getPointer());
        stdConfig.read();
        info.read();

        if (!config) {
            throw new HikException(getErrorMsg());
        }
        return info;
    }


    /**
     * byte[] to string with charset
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
     */
    public static Double numFormat(Float source, int num) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(num);
        numberFormat.setRoundingMode(RoundingMode.UP);
        return Double.valueOf(numberFormat.format(source));
    }

}
