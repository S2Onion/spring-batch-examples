package com.batch.example.batch.job;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

@Log4j2
public class JobPropertiesPath {

    public static String getJobPropertiesPath(String jobName) {
        String propPath = "";

        if ( StringUtils.equals("executePsnlDataMask", jobName) ) {
            propPath = "properties/maskPsnlDataJob.properties";
        } else {
            log.warn("Fail to search a properties path.");
        }
        return propPath;
    }
}
