package com.github.ipcam.onvif.command;

import com.github.ipcam.onvif.OnvifCommand;
import com.github.ipcam.onvif.models.OnvifDeviceInfo;
import com.github.ipcam.onvif.xml.DefaultXmlPullParser;
import com.github.ipcam.onvif.xml.XmlPullParser;

import java.io.StringReader;

/**
 * DeviceInfoCommand
 *
 * @author echils
 */
public class DeviceInfoCommand implements OnvifCommand<OnvifDeviceInfo> {

    private static final String KEY_MANUFACTURER = "Manufacturer";
    private static final String KEY_MODEL = "Model";
    private static final String KEY_FIRMWARE_VERSION = "FirmwareVersion";
    private static final String KEY_SERIAL_NUMBER = "SerialNumber";
    private static final String KEY_HARDWARE_ID = "HardwareId";

    @Override
    public String uri() {
        return "/onvif/device_service";
    }

    @Override
    public String content() {
        return "<GetDeviceInformation xmlns=\"http://www.onvif.org/ver10/device/wsdl\">" + "</GetDeviceInformation>";
    }

    @Override
    public OnvifDeviceInfo parse(String response) throws Exception {
        OnvifDeviceInfo deviceInfo = new OnvifDeviceInfo();
        XmlPullParser xmlParser = new DefaultXmlPullParser();
        xmlParser.setInput(new StringReader(response));
        int eventType = xmlParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_MANUFACTURER)) {
                xmlParser.next();
                deviceInfo.setManufacturer(xmlParser.getText());
            } else if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_MODEL)) {
                xmlParser.next();
                deviceInfo.setModel(xmlParser.getText());
            } else if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_FIRMWARE_VERSION)) {
                xmlParser.next();
                deviceInfo.setFirmwareVersion(xmlParser.getText());
            } else if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_SERIAL_NUMBER)) {
                xmlParser.next();
                deviceInfo.setSerialNumber(xmlParser.getText());
            } else if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_HARDWARE_ID)) {
                xmlParser.next();
                deviceInfo.setHardwareId(xmlParser.getText());
            }
            eventType = xmlParser.next();
        }
        return deviceInfo;
    }

}
