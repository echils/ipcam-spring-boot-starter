package com.github.ipcam.entity.onvif.command;

import com.github.ipcam.entity.infos.PresetPointInfo;
import com.github.ipcam.entity.onvif.OnvifCommand;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;
import com.github.ipcam.entity.onvif.xml.DefaultXmlPullParser;
import com.github.ipcam.entity.onvif.xml.XmlPullParser;

import java.io.StringReader;

/**
 * UpdatePresetCommand
 *
 * @author echils
 */
public class UpdatePresetCommand implements OnvifCommand<PresetPointInfo> {

    private static final String KEY_PRESET_TOKEN = "PresetToken";

    private int index;

    private OnvifMediaProfile mediaProfile;

    public UpdatePresetCommand(OnvifMediaProfile mediaProfile, int index) {
        this.index = index;
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
                "<PresetToken>" + index + "</PresetToken>" +
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
