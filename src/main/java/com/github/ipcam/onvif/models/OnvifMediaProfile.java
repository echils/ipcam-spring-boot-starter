package com.github.ipcam.onvif.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OnvifMediaProfile
 *
 * @author echils
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnvifMediaProfile {

    private String name;

    private String token;

}
