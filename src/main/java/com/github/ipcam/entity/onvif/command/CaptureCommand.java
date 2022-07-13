package com.github.ipcam.entity.onvif.command;

import com.github.ipcam.entity.onvif.OnvifCommand;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;
import com.github.ipcam.entity.onvif.xml.DefaultXmlPullParser;
import com.github.ipcam.entity.onvif.xml.XmlPullParser;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.UUID;

/**
 * CaptureCommand
 *
 * @author echils
 */
public class CaptureCommand implements OnvifCommand<File> {

    private static final String KEY_URI = "Uri";

    private OnvifMediaProfile mediaProfile;

    public CaptureCommand(OnvifMediaProfile mediaProfile) {
        this.mediaProfile = mediaProfile;
    }

    @Override
    public String uri() {
        return "/onvif/Media";
    }

    @Override
    public String content() {
        return "<GetSnapshotUri xmlns=\"http://www.onvif.org/ver10/media/wsdl\">" +
                "<ProfileToken>" + mediaProfile.getToken() + "</ProfileToken>" +
                "</GetSnapshotUri>";
    }

    @Override
    public File parse(String response) throws Exception {
        XmlPullParser xmlParser = new DefaultXmlPullParser();
        xmlParser.setInput(new StringReader(response));
        int eventType = xmlParser.getEventType();
        String url = null;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(KEY_URI)) {
                xmlParser.next();
                url = xmlParser.getText();
                break;
            }
            eventType = xmlParser.next();
        }
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("Capture url is blank");
        }
        URLConnection urlConnection = new URL(url).openConnection();
        String encoding = Base64.getEncoder().encodeToString(("admin" + ":" + "JunAseit2018!").getBytes());
        urlConnection.setRequestProperty("Authorization", "Basic " + encoding);
        File file = new File(snapshotPath());
        StreamUtils.copy(urlConnection.getInputStream(), new FileOutputStream(file));
        return file;
    }

    private String snapshotPath() {
        return System.getProperty("java.io.tmpdir") + File.separator + UUID.randomUUID() + ".jpg";
    }

}
