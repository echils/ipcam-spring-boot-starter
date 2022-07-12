package com.github.ipcam.entity.onvif.command;

import com.github.ipcam.entity.onvif.OnvifCommand;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;

/**
 * ControlStopCommand
 *
 * @author echils
 */
public class ControlStopCommand implements OnvifCommand<Void> {

    @Override
    public String uri() {
        return "/onvif/PTZ";
    }

    @Override
    public String content(OnvifMediaProfile mediaProfile) {
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
