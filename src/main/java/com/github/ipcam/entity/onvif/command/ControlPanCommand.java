package com.github.ipcam.entity.onvif.command;

import com.github.ipcam.entity.onvif.OnvifCommand;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;

/**
 * ControlPanCommand
 *
 * @author echils
 */
public class ControlPanCommand implements OnvifCommand<Void> {

    private int index;

    public ControlPanCommand(int presetNo) {
        this.index = presetNo;
    }

    @Override
    public String uri() {
        return "/onvif/Provisioning";
    }

    @Override
    public String content(OnvifMediaProfile mediaProfile) {
        return "<PanMove xmlns=\"http://www.onvif.org/ver10/provisioning/wsdl\">" +
                "<VideoSource>" + "VideoSource_1" + "</VideoSource>" +
                "<Direction>" + "Left" + "</Direction>" +
                "</PanMove>";
    }

    @Override
    public Void parse(String response) {
        return null;
    }

}
