package com.github.ipcam.onvif.command;

import com.github.ipcam.entity.reference.PTZControlEnum;
import com.github.ipcam.onvif.OnvifCommand;
import com.github.ipcam.onvif.models.OnvifMediaProfile;

/**
 * ControlMoveCommand
 *
 * @author echils
 */
public class ControlMoveCommand implements OnvifCommand<Void> {

    private OnvifMediaProfile mediaProfile;

    private PTZControlEnum control;

    private float speed;

    public ControlMoveCommand(OnvifMediaProfile mediaProfile, PTZControlEnum control, int speed) {
        this.mediaProfile = mediaProfile;
        this.control = control;
        this.speed = speed * 0.1F;
    }

    @Override
    public String uri() {
        return "/onvif/PTZ";
    }

    @Override
    public String content() {
        String data = null;
        switch (control) {
            case TILT_UP:
                data = "<PanTilt y = \"" + speed + "\" xmlns = \"http://www.onvif.org/ver10/schema\" />";
                break;
            case TILT_DOWN:
                data = "<PanTilt y = \"" + (-speed) + "\" xmlns = \"http://www.onvif.org/ver10/schema\" />";
                break;
            case PAN_LEFT:
                data = "<PanTilt x = \"" + (-speed) + "\" xmlns = \"http://www.onvif.org/ver10/schema\" />";
                break;
            case PAN_RIGHT:
                data = "<PanTilt x = \"" + speed + "\" xmlns = \"http://www.onvif.org/ver10/schema\" />";
                break;
            case UP_LEFT:
                data = "<PanTilt x = \"" + (-speed) + "\" y = \"" + speed + "\" xmlns = \"http://www.onvif.org/ver10/schema\" />";
                break;
            case UP_RIGHT:
                data = "<PanTilt x = \"" + speed + "\" y = \"" + speed + "\" xmlns = \"http://www.onvif.org/ver10/schema\" />";
                break;
            case DOWN_LEFT:
                data = "<PanTilt x = \"" + (-speed) + "\" y = \"" + (-speed) + "\" xmlns = \"http://www.onvif.org/ver10/schema\" />";
                break;
            case DOWN_RIGHT:
                data = "<PanTilt x = \"" + speed + "\" y = \"" + (-speed) + "\" xmlns = \"http://www.onvif.org/ver10/schema\" />";
                break;
            case ZOOM_IN:
                data = "<Zoom x =\"" + speed + "\" xmlns = \"http://www.onvif.org/ver10/schema\" />";
                break;
            case ZOOM_OUT:
                data = "<Zoom x =\"" + (-speed) + "\" xmlns = \"http://www.onvif.org/ver10/schema\" />";
                break;
        }
        return "<ContinuousMove xmlns=\"http://www.onvif.org/ver20/ptz/wsdl\">" +
                "<ProfileToken>" + mediaProfile.getToken() + "</ProfileToken>" +
                "<Velocity>" + data + "</Velocity>" +
                "</ContinuousMove>";
    }

    @Override
    public Void parse(String response) {
        return null;
    }

}
