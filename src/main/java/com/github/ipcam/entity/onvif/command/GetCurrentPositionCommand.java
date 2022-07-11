package com.github.ipcam.entity.onvif.command;

import com.github.ipcam.entity.PTZ;
import com.github.ipcam.entity.onvif.OnvifCommand;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;
import com.github.ipcam.entity.onvif.xml.DefaultXmlPullParser;
import com.github.ipcam.entity.onvif.xml.XmlPullParser;
import org.springframework.util.StringUtils;

import java.io.StringReader;

/**
 * DeviceInfoCommand
 *
 * @author echils
 */
public class GetCurrentPositionCommand implements OnvifCommand<PTZ> {

    //    pan=-1.0, tilt=0.142857, zoom=0.0
    private static final String KEY_PRESET = "Preset";
    private static final String ATTR_TOKEN = "token";
    private static final String ATTR_NAME = "Name";
    private static final String KEY_POSITION = "PTZPosition";
    private static final String KEY_POSITION_PANTILT = "PanTilt";
    private static final String KEY_POSITION_ZOOM = "Zoom";

    @Override
    public String uri() {
        return "/onvif/ptz_service";
    }

    @Override
    public String content(OnvifMediaProfile mediaProfile) {
        return "<GetStatus xmlns=\"http://www.onvif.org/ver20/ptz/wsdl\">" +
                "<ProfileToken>" + mediaProfile.getToken() + "</ProfileToken>" +
                "</GetStatus>";
    }

    @Override
    public PTZ parse(String response) throws Exception {
        PTZ ptz = new PTZ();
        XmlPullParser xmlParser = new DefaultXmlPullParser();
        xmlParser.setInput(new StringReader(response));
        int eventType = xmlParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_POSITION_PANTILT)) {
                for (int i = 0; i < xmlParser.getAttributeCount(); i++) {
                    String attributeValue = xmlParser.getAttributeValue(i);
                    if (xmlParser.getAttributeName(i).equals("x")) {
                        if (!StringUtils.isEmpty(attributeValue)) ptz.setPan(Double.parseDouble(attributeValue));
                    } else if (xmlParser.getAttributeName(i).equals("y")) {
                        if (!StringUtils.isEmpty(attributeValue)) ptz.setTilt(Double.parseDouble(attributeValue));

                    }
                }
            } else if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_POSITION_ZOOM)) {
                for (int i = 0; i < xmlParser.getAttributeCount(); i++) {
                    if (xmlParser.getAttributeName(i).equals("x")) {
                        String attributeValue = xmlParser.getAttributeValue(i);
                        if (!StringUtils.isEmpty(attributeValue)) ptz.setZoom(Double.parseDouble(attributeValue));
                    }
                }
            } else if (eventType == XmlPullParser.END_TAG && xmlParser.getName().equals(KEY_POSITION)) {
                return ptz;
            }
            eventType = xmlParser.next();
        }
        return ptz;
    }

}
