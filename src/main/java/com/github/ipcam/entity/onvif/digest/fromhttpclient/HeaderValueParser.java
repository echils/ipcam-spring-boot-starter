package com.github.ipcam.entity.onvif.digest.fromhttpclient;

/**
 * HeaderValueParser
 *
 * @author echils
 * @see "https://mvnrepository.com/artifact/com.burgstaller/okhttp-digest"
 */
public interface HeaderValueParser {

    HeaderElement[] parseElements(CharArrayBuffer var1, ParserCursor var2) throws ParseException;

    HeaderElement parseHeaderElement(CharArrayBuffer var1, ParserCursor var2) throws ParseException;

    NameValuePair[] parseParameters(CharArrayBuffer var1, ParserCursor var2) throws ParseException;

    NameValuePair parseNameValuePair(CharArrayBuffer var1, ParserCursor var2) throws ParseException;

}
