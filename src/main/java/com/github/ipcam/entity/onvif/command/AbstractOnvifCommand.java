package com.github.ipcam.entity.onvif.command;

import com.github.ipcam.entity.onvif.OnvifCommand;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * AbstractOnvifCommand
 *
 * @author echils
 */
abstract class AbstractOnvifCommand<T> implements OnvifCommand<T> {

    private XmlPullParser xmlParser;

    AbstractOnvifCommand() {
        try {
            XmlPullParserFactory xmlFactory = XmlPullParserFactory.newInstance();
            xmlFactory.setNamespaceAware(true);
            xmlParser = xmlFactory.newPullParser();
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        }
    }

    XmlPullParser getXmlParser() {
        return xmlParser;
    }

}
