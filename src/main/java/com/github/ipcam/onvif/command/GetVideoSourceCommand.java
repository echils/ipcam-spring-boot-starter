package com.github.ipcam.onvif.command;

import com.github.ipcam.onvif.OnvifCommand;
import com.github.ipcam.onvif.models.OnvifVideoSource;
import com.github.ipcam.onvif.xml.DefaultXmlPullParser;
import com.github.ipcam.onvif.xml.XmlPullParser;

import java.io.StringReader;

/**
 * GetVideoSourceCommand
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
    public String content() {
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
