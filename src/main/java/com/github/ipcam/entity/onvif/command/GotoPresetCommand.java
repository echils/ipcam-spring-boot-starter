package com.github.ipcam.entity.onvif.command;

import com.github.ipcam.entity.onvif.OnvifCommand;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;

/**
 * GotoPresetCommand
 *
 * @author echils
 */
public class GotoPresetCommand implements OnvifCommand<Void> {

    private int index;

    public GotoPresetCommand(int presetNo) {
        this.index = presetNo;
    }

    @Override
    public String uri() {
        return "/onvif/ptz_service";
    }

    @Override
    public String content(OnvifMediaProfile mediaProfile) {
        return "<GotoPreset xmlns=\"http://www.onvif.org/ver20/ptz/wsdl\">" +
                "<ProfileToken>" + mediaProfile.getToken() + "</ProfileToken>" +
                "<PresetToken>" + index + "</PresetToken>" +
                "</GotoPreset>";
    }

    @Override
    public Void parse(String response) {
        return null;
    }

}
