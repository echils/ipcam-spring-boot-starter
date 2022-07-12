package com.github.ipcam.entity.onvif.command;

import com.github.ipcam.entity.infos.PresetPointInfo;
import com.github.ipcam.entity.onvif.OnvifCommand;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;
import com.github.ipcam.entity.onvif.xml.DefaultXmlPullParser;
import com.github.ipcam.entity.onvif.xml.XmlPullParser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * GetPresetsCommand
 *
 * @author echils
 */
public class GetPresetsCommand implements OnvifCommand<List<PresetPointInfo>> {

    private static final String KEY_PRESET = "Preset";
    private static final String ATTR_TOKEN = "token";
    private static final String ATTR_NAME = "Name";
    private static final String KEY_POSITION = "PTZPosition";
    private static final String KEY_POSITION_PANTILT = "PanTilt";
    private static final String KEY_POSITION_ZOOM = "Zoom";

    @Override
    public String uri() {
        return "/onvif/PTZ";
    }

    @Override
    public String content(OnvifMediaProfile mediaProfile) {
        return "<GetPresets xmlns=\"http://www.onvif.org/ver20/ptz/wsdl\">" +
                "<ProfileToken>" + mediaProfile.getToken() + "</ProfileToken>" +
                "</GetPresets>";
    }

    @Override
    public List<PresetPointInfo> parse(String response) throws Exception {
        List<PresetPointInfo> presetPointInfos = new ArrayList<>();
        XmlPullParser xmlParser = new DefaultXmlPullParser();
        xmlParser.setInput(new StringReader(response));
        int eventType = xmlParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            PresetPointInfo pointInfo = new PresetPointInfo();
            if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_PRESET)) {
                String token = xmlParser.getAttributeValue(null, ATTR_TOKEN);
                pointInfo.setId(Integer.parseInt(token));
                xmlParser.nextTag();
                if (xmlParser.getName().equals(ATTR_NAME)) {
                    xmlParser.next();
                    String name = xmlParser.getText();
                    pointInfo.setPresetName(name);
                    xmlParser.nextTag();
                }
                xmlParser.nextTag();
                if (xmlParser.getName().equals(KEY_POSITION)) {
                    //Not all cameras can successfully obtain the PTZ value
//                    PTZ ptz = new PTZ();
                    xmlParser.nextTag();
                    if (xmlParser.getName().equals(KEY_POSITION_PANTILT)) {
//                        ptz.setPan(Double.parseDouble(xmlParser.getAttributeValue(null, "x")));
//                        ptz.setTilt(Double.parseDouble(xmlParser.getAttributeValue(null, "y")));
                        xmlParser.nextTag();
                    }
                    xmlParser.nextTag();
                    if (xmlParser.getName().equals(KEY_POSITION_ZOOM)) {
//                        ptz.setZoom(Double.parseDouble(xmlParser.getAttributeValue(null, "x")));
                    }
                    xmlParser.nextTag();
                }
                presetPointInfos.add(pointInfo);
            }
            eventType = xmlParser.next();
        }
        return presetPointInfos;
    }

}
