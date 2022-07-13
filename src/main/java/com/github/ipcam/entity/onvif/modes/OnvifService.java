package com.github.ipcam.entity.onvif.modes;

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
