package com.github.ipcam.onvif.command;

import com.github.ipcam.entity.PresetPointInfo;
import com.github.ipcam.onvif.OnvifCommand;
import com.github.ipcam.onvif.models.OnvifMediaProfile;
import com.github.ipcam.onvif.xml.DefaultXmlPullParser;
import com.github.ipcam.onvif.xml.XmlPullParser;

import java.io.StringReader;

/**
 * SetPresetCommand
 *
 * @author echils
 */
public class SetPresetCommand implements OnvifCommand<PresetPointInfo> {

    private static final String KEY_PRESET_TOKEN = "PresetToken";

    private OnvifMediaProfile mediaProfile;

    public SetPresetCommand(OnvifMediaProfile mediaProfile) {
        this.mediaProfile = mediaProfile;
    }

    @Override
    public String uri() {
        return "/onvif/PTZ";
    }

    @Override
    public String content() {
        return "<SetPreset xmlns=\"http://www.onvif.org/ver20/ptz/wsdl\">" +
                "<ProfileToken>" + mediaProfile.getToken() + "</ProfileToken>" +
                "</SetPreset>";
    }

    @Override
    public PresetPointInfo parse(String response) throws Exception {
        PresetPointInfo presetPointInfo = new PresetPointInfo();
        XmlPullParser xmlParser = new DefaultXmlPullParser();
        xmlParser.setInput(new StringReader(response));
        int eventType = xmlParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_PRESET_TOKEN)) {
                xmlParser.next();
                presetPointInfo.setId(Integer.parseInt(xmlParser.getText()));
                return presetPointInfo;
            }
            eventType = xmlParser.next();
        }
        return presetPointInfo;
    }

}
