package com.github.ipcam.entity.onvif.command;

import com.github.ipcam.entity.onvif.OnvifCommand;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;

/**
 * RemovePresetCommand
 *
 * @author echils
 */
public class RemovePresetCommand implements OnvifCommand<Void> {

    private int index;

    public RemovePresetCommand(int presetNo) {
        this.index = presetNo;
    }

    @Override
    public String uri() {
        return "/onvif/ptz_service";
    }

    @Override
    public String content(OnvifMediaProfile mediaProfile) {
        return "<RemovePreset xmlns=\"http://www.onvif.org/ver20/ptz/wsdl\">" +
                "<ProfileToken>" + mediaProfile.getToken() + "</ProfileToken>" +
                "<PresetToken>" + index + "</PresetToken>" +
                "</RemovePreset>";
    }

    @Override
    public Void parse(String response) {
        return null;
    }

}
