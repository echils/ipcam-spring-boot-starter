package com.github.ipcam.onvif.command;

import com.github.ipcam.onvif.OnvifCommand;
import com.github.ipcam.onvif.models.OnvifMediaProfile;

/**
 * ControlStopCommand
 *
 * @author echils
 */
public class ControlStopCommand implements OnvifCommand<Void> {

    private OnvifMediaProfile mediaProfile;

    public ControlStopCommand(OnvifMediaProfile mediaProfile) {
        this.mediaProfile = mediaProfile;
    }

    @Override
    public String uri() {
        return "/onvif/PTZ";
    }

    @Override
    public String content() {
        return "<Stop xmlns=\"http://www.onvif.org/ver20/ptz/wsdl\">" +
                "<ProfileToken>" + mediaProfile.getToken() + "</ProfileToken>" +
                "<PanTilt>true</PanTilt>" +
                "<Zoom>true</Zoom>" +
                "</Stop>";
    }

    @Override
    public Void parse(String response) {
        return null;
    }

}
