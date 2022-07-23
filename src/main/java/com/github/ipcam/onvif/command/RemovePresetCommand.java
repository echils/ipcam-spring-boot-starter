package com.github.ipcam.onvif.command;

import com.github.ipcam.onvif.OnvifCommand;
import com.github.ipcam.onvif.models.OnvifMediaProfile;

/**
 * RemovePresetCommand
 *
 * @author echils
 */
public class RemovePresetCommand implements OnvifCommand<Void> {

    private int index;

    private OnvifMediaProfile mediaProfile;

    public RemovePresetCommand(OnvifMediaProfile mediaProfile, int index) {
        this.index = index;
        this.mediaProfile = mediaProfile;
    }

    @Override
    public String uri() {
        return "/onvif/PTZ";
    }

    @Override
    public String content() {
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
