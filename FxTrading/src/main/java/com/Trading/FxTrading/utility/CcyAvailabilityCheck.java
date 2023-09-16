package com.Trading.FxTrading.utility;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class CcyAvailabilityCheck {

	public String isPropertyExists(String currencyCode) {
		String property = null;
		Properties properties = new Properties();
		try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/currencyData.properties")) {
			properties.load(fileInputStream);

			 property = properties.getProperty(currencyCode);
				return property;
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return property;
	}

}
