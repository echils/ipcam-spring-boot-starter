package com.github.ipcam.entity.onvif.digest;

/**
 * CredentialInfo
 *
 * @author echils
 * @see "https://mvnrepository.com/artifact/com.burgstaller/okhttp-digest"
 */
public class CredentialInfo {

    private String userName;
    private String password;

    public CredentialInfo(String userName, String password) {
        if (userName == null || password == null) {
            throw new IllegalArgumentException("username and password cannot be null");
        }
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
