package com.github.ipcam.entity.reference;

/**
 * ResolutionEnum
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public enum ResolutionEnum {

    /**
     * 1080P
     */
    PIXEL1080P("1920#1080"),

    /**
     * 720P
     */
    PIXEL720P("1280#720");

    public static final String RESOLUTION_SEPARATOR = "#";

    private String resolution;

    ResolutionEnum(String resolution) {
        this.resolution = resolution;
    }

    public String getResolution() {
        return resolution;
    }

}
