package org.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationInfoService {

    @Value("${version.number}")
    private String versionNumber;

    public String getVersionNumber() {
        return versionNumber;
    }
}
