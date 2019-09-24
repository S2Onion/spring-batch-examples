package com.batch.example.common.util;

import com.sun.istack.internal.NotNull;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ReadPropertiesUtil {

	public static Properties readResource(@NotNull String resource) {
		Properties properties = new Properties();
		try {
			properties = Resources.getResourceAsProperties(resource);
		} catch (IOException ioe) {
			log.error("Properties read error, ", ioe);
		}
		return properties;
	}
}
