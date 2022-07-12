package com.github.ipcam.entity.onvif.command;

import com.github.ipcam.entity.onvif.OnvifCommand;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;
import com.github.ipcam.entity.onvif.modes.OnvifVideoSource;
import com.github.ipcam.entity.onvif.xml.DefaultXmlPullParser;
import com.github.ipcam.entity.onvif.xml.XmlPullParser;

import java.io.StringReader;

/**
 * GetMediaProfilesCommand
 *
 * @author echils
 */
public class GetVideoSourceCommand implements OnvifCommand<OnvifVideoSource> {

    private static final String KEY_PROFILES = "Token";

    @Override
    public String uri() {
        return "/onvif/DeviceIO";
    }

    @Override
    public String content(OnvifMediaProfile mediaProfile) {
        return "<GetVideoSources xmlns=\"http://www.onvif.org/ver10/deviceIO/wsdl\"/>";
    }

    @Override
    public OnvifVideoSource parse(String response) throws Exception {
        XmlPullParser xmlParser = new DefaultXmlPullParser();
        xmlParser.setInput(new StringReader(response));
        int eventType = xmlParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_PROFILES)) {
                return new OnvifVideoSource(xmlParser.nextText());
            }
            eventType = xmlParser.next();
        }
        return null;
    }


}
