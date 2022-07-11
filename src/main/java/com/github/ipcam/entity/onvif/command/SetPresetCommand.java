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
public class SetPresetCommand implements OnvifCommand<PresetPointInfo> {

    private static final String KEY_PRESET_TOKEN = "PresetToken";

    @Override
    public String uri() {
        return "/onvif/ptz_service";
    }

    @Override
    public String content(OnvifMediaProfile mediaProfile) {
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