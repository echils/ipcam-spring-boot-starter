package com.github.ipcam.onvif.command;

import com.github.ipcam.onvif.OnvifCommand;
import com.github.ipcam.onvif.models.OnvifService;
import com.github.ipcam.onvif.xml.DefaultXmlPullParser;
import com.github.ipcam.onvif.xml.XmlPullParser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * GetServicesCommand
 *
 * @author echils
 */
public class GetServicesCommand implements OnvifCommand<List<OnvifService>> {

    private static final String NAMESPACE = "Namespace";
    private static final String X_ADDR = "XAddr";
    private static final String SERVICE = "Service";

    @Override
    public String uri() {
        return "/onvif/device_service";
    }

    @Override
    public String content() {
        return "<GetServices xmlns=\"http://www.onvif.org/ver10/device/wsdl\">" +
                "<IncludeCapability>true</IncludeCapability>" +
                "</GetServices>";
    }

    @Override
    public List<OnvifService> parse(String response) throws Exception {
        List<OnvifService> services = new ArrayList<>();
        XmlPullParser xmlParser = new DefaultXmlPullParser();
        xmlParser.setInput(new StringReader(response));
        int eventType = xmlParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && xmlParser.getName().equals(SERVICE)) {
                OnvifService service = new OnvifService();
                xmlParser.next();
                if (xmlParser.getName().equals(NAMESPACE)) {
                    service.setNamespace(xmlParser.nextText());
                }
                xmlParser.next();
                xmlParser.next();
                if (xmlParser.getName().equals(X_ADDR)) {
                    service.setAddress(xmlParser.nextText());
                }
                services.add(service);
            }
            eventType = xmlParser.next();
        }
        return services;
    }

}
