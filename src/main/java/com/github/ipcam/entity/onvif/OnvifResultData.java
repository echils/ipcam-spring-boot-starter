package com.github.ipcam.entity.onvif;

import lombok.Data;

/**
 * OnvifResultData
 *
 * @author echils
 */
@Data
public class OnvifResultData<T> {

    /**
     * The state of he onvif result
     */
    private boolean success;

    /**
     * The data of he onvif result
     */
    private T data;

    /**
     * The msg of he onvif result
     */
    private String msg;


    public static <T> OnvifResultData<T> failed(String msg) {
        OnvifResultData<T> resultData = new OnvifResultData<>();
        resultData.setSuccess(false);
        resultData.setMsg(msg);
        return resultData;
    }

    public static <T> OnvifResultData<T> success(T data) {
        OnvifResultData<T> resultData = new OnvifResultData<>();
        resultData.setSuccess(true);
        resultData.setData(data);
        return resultData;
    }

}
