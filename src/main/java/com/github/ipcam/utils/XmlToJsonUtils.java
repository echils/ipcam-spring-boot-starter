package com.github.ipcam.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * XmlToJsonUtils
 *
 * @author echils
 */
public class XmlToJsonUtils {


    private static final Logger logger = LoggerFactory.getLogger(XmlToJsonUtils.class);


    /**
     * Convert the xml to json
     *
     * @param xmlFile the xml file
     */
    public static JsonObject toJson(File xmlFile) {
        return toJson(readXml(xmlFile));
    }


    /**
     * Convert the xml to json
     *
     * @param xmlStr the xml value
     */
    public static JsonObject toJson(String xmlStr) {
        if (StringUtils.isEmpty(xmlStr)) {
            return null;
        }
        try {
            Document doc = DocumentHelper.parseText(xmlStr);
            JsonObject json = new JsonObject();
            parse(doc.getRootElement(), json);
            return json;
        } catch (DocumentException e) {
            logger.error("Parse xml failed:{}", e.getMessage());
            throw new RuntimeException("Convert the xml to json failed");
        }
    }


    /**
     * Convert the xml to json
     *
     * @param element {@link Element}
     * @param json    {@link JsonObject}
     */
    private static void parse(Element element, JsonObject json) {
        for (Object object : element.attributes()) {
            Attribute attribute = (Attribute) object;
            if (isNotEmpty(attribute.getValue())) {
                json.addProperty("@" + attribute.getName(), attribute.getValue());
            }
        }
        List<Element> childrenElement = element.elements();
        if (childrenElement.isEmpty() && isNotEmpty(element.getText())) {
            json.addProperty(element.getName(), element.getText());
            return;
        }
        for (Element e : childrenElement) {
            String name = e.getName();
            if (CollectionUtils.isEmpty(e.elements())) {
                for (Object o : element.attributes()) {
                    Attribute attr = (Attribute) o;
                    if (isNotEmpty(attr.getValue())) {
                        json.addProperty("@" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty()) {
                    json.addProperty(name, e.getText());
                }
            } else {
                JsonObject childrenJson = new JsonObject();
                parse(e, childrenJson);
                Object obj = json.get(name);
                if (obj != null) {
                    JsonArray jsonArray = null;
                    if (obj instanceof JsonObject) {
                        JsonObject jsonObject = (JsonObject) obj;
                        json.remove(name);
                        jsonArray = new JsonArray();
                        jsonArray.add(jsonObject);
                        jsonArray.add(childrenJson);
                    }
                    if (obj instanceof JsonArray) {
                        jsonArray = (JsonArray) obj;
                        jsonArray.add(childrenJson);
                    }
                    json.add(name, jsonArray);
                } else {
                    json.add(name, childrenJson);
                }
            }
        }
    }


    /**
     * Read the content of the xml
     *
     * @param file the xml file
     * @return the value of xml
     */
    private static String readXml(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            try (FileChannel fileChannel = fileInputStream.getChannel()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(new Long(file.length()).intValue());
                fileChannel.read(byteBuffer);
                byteBuffer.flip();
                return new String(byteBuffer.array(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            logger.error("Read the content of the xml failed:{}", e.getMessage());
            return null;
        }
    }


    /**
     * Determine whether the string is empty
     *
     * @param value the value of string
     */
    private static boolean isNotEmpty(String value) {
        return !StringUtils.isEmpty(value) && !"null".equals(value);
    }


}
