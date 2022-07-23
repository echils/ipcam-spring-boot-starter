package com.github.ipcam.onvif.digest.fromhttpclient;

import java.io.Serializable;

/**
 * BasicNameValuePair
 *
 * @author echils
 * @see "https://mvnrepository.com/artifact/com.burgstaller/okhttp-digest"
 */
public class BasicNameValuePair implements NameValuePair, Cloneable, Serializable {

    private static final long serialVersionUID = -6437800749411518984L;
    private final String name;
    private final String value;

    public BasicNameValuePair(String name, String value) {
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        } else {
            this.name = name;
            this.value = value;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        if (this.value == null) {
            return this.name;
        } else {
            int len = this.name.length() + 1 + this.value.length();
            StringBuilder buffer = new StringBuilder(len);
            buffer.append(this.name);
            buffer.append("=");
            buffer.append(this.value);
            return buffer.toString();
        }
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (this == object) {
            return true;
        } else if (!(object instanceof NameValuePair)) {
            return false;
        } else {
            BasicNameValuePair that = (BasicNameValuePair) object;
            return this.name.equals(that.name) && (this.value == that.value) ||
                    (this.value != null && this.value.equals(that.value));
        }
    }

    public int hashCode() {
        byte hash = 17;
        int hash1 = LangUtils.hashCode(hash, this.name);
        hash1 = LangUtils.hashCode(hash1, this.value);
        return hash1;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
