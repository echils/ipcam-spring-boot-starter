package com.github.ipcam.onvif.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OnvifService
 *
 * @author echils
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnvifService {

    private String address;

    private String namespace;

}
