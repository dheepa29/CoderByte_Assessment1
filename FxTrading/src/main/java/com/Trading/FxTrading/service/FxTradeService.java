package com.Trading.FxTrading.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trading.FxTrading.entity.CurrencyExchange;
import com.Trading.FxTrading.utility.CcyAvailabilityCheck;
//import com.trade.model.ExchaneCurrency;


@Service
public class FxTradeService {
	
	@Autowired
	private CcyAvailabilityCheck checker;

	public int addCcy(String ccy) {
		int status = 0;
		File file = new File("src/main/resources/currencyData.properties");
		Properties properties = new Properties();
		try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {

			String[] parts = ccy.split("=");

			if (parts.length == 2) {
				String currencyCode = parts[0];
				String exchangeRate = parts[1];

				String property = checker.isPropertyExists(currencyCode);

				if (property == null) {
					properties.setProperty(currencyCode, exchangeRate);

					properties.store(fileOutputStream, "comment");
					status = 1;
				}

				else {
					status = 2;
				}

			} else {
				status = 3;
			}

		} catch (Exception e) {
			e.printStackTrace();
			status = 4;
		}

		return status;

	}
	public int exchangeCurrency(CurrencyExchange currency) {
		int status = 0;

		File file = new File("src/main/resources/exchangeData.properties");
		Properties properties = new Properties();

		String property = checker.isPropertyExists(currency.getCcy());

		if (property != null) {
			// exchange logic

			double exchangeAmount = Double.parseDouble(property) * currency.getAmount();

			try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {

				properties.setProperty(currency.getFrom() + "-" + currency.getTo(), String.valueOf(exchangeAmount));
				properties.store(fileOutputStream, "comment");
				status = 1;

			} catch (Exception e) {
				status = 3;
			}

		} else {
			status = 2;
		}

		try {

		} catch (Exception e) {
			e.printStackTrace();
			status=3;
		}

		return status;

	}

	public Map<String, String> getAllExchanges() {

		Properties properties = new Properties();
		Map<String, String> map = new HashMap<>();
		try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/exchangeData.properties")) {

			properties.load(fileInputStream);

			Set<Object> keys = properties.keySet();

			for (Object key : keys) {
				String k = (String) key;
				String v = properties.getProperty(k);
				map.put(k, v);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;

	}





}
