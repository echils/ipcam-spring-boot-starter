package com.github.ipcam.entity.onvif.command;

import com.github.ipcam.entity.onvif.OnvifCommand;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;
import com.github.ipcam.entity.onvif.xml.DefaultXmlPullParser;
import com.github.ipcam.entity.onvif.xml.XmlPullParser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * GetMediaProfilesCommand
 *
 * @author echils
 */
public class GetMediaProfilesCommand implements OnvifCommand<List<OnvifMediaProfile>> {

    private static final String KEY_PROFILES = "Profiles";
    private static final String ATTR_TOKEN = "token";
    private static final String ATTR_NAME = "Name";

    @Override
    public String uri() {
        return "/onvif/PTZ";
    }

    @Override
    public String content(OnvifMediaProfile mediaProfile) {
        return "<GetProfiles xmlns=\"http://www.onvif.org/ver10/media/wsdl\"/>";
    }

    @Override
    public List<OnvifMediaProfile> parse(String response) throws Exception {
        List<OnvifMediaProfile> mediaProfiles = new ArrayList<>();
        XmlPullParser xmlParser = new DefaultXmlPullParser();
        xmlParser.setInput(new StringReader(response));
        int eventType = xmlParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_PROFILES)) {
                String token = xmlParser.getAttributeValue(null, ATTR_TOKEN);
                xmlParser.nextTag();
                if (xmlParser.getName().equals(ATTR_NAME)) {
                    xmlParser.next();
                    String name = xmlParser.getText();
                    mediaProfiles.add(new OnvifMediaProfile(name, token));
                }
            }
            eventType = xmlParser.next();
        }
        return mediaProfiles;
    }

}
