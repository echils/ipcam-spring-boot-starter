package com.github.ipcam.onvif.command;

import com.github.ipcam.entity.PTZ;
import com.github.ipcam.onvif.OnvifCommand;
import com.github.ipcam.onvif.models.OnvifMediaProfile;
import com.github.ipcam.onvif.xml.DefaultXmlPullParser;
import com.github.ipcam.onvif.xml.XmlPullParser;
import org.springframework.util.StringUtils;

import java.io.StringReader;

/**
 * GetCurrentPositionCommand
 *
 * @author echils
 */
public class GetCurrentPositionCommand implements OnvifCommand<PTZ> {

    private static final String KEY_POSITION = "PTZPosition";
    private static final String KEY_POSITION_PANTILT = "PanTilt";
    private static final String KEY_POSITION_ZOOM = "Zoom";

    private OnvifMediaProfile mediaProfile;

    public GetCurrentPositionCommand(OnvifMediaProfile mediaProfile) {
        this.mediaProfile = mediaProfile;
    }

    @Override
    public String uri() {
        return "/onvif/PTZ";
    }

    @Override
    public String content() {
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
