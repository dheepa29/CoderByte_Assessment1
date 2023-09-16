package com.Trading.FxTrading.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Trading.FxTrading.entity.CurrencyExchange;
import com.Trading.FxTrading.exception.AlreadyExistsCcyException;
import com.Trading.FxTrading.exception.CcyExchangeDoesnotExistsException;
import com.Trading.FxTrading.exception.InvalidCcyException;
import com.Trading.FxTrading.service.FxTradeService;





@RestController
public class FxTradeController {

	@Autowired
	private FxTradeService serviceImpl;
	
	@GetMapping("/add-ccy/{ccy}")
	public ResponseEntity<String> addCcy(@PathVariable String ccy) {
		int status = serviceImpl.addCcy(ccy);
		if (status == 1) {
			return new ResponseEntity<String>("CCY Added !!", HttpStatus.CREATED);
		} else if (status == 2) {
			throw new AlreadyExistsCcyException("Ccy" + ccy + " Already Added In Properties File");
		} else if (status == 3) {
			throw new InvalidCcyException("Invalid CCY ");
		}else {
			return new ResponseEntity<String>("Something Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@PostMapping("/exchange-currency")
	public ResponseEntity<String> exchangeCurrency(@RequestBody CurrencyExchange currency) {

		int status = serviceImpl.exchangeCurrency(currency);
		if (status == 1) {
			return new ResponseEntity<String>("Currency Exchanged !!", HttpStatus.CREATED);
		} else if (status == 2) {
			throw new InvalidCcyException("Ccy Not Exists in ccy-data.properties");
		} else {
			return new ResponseEntity<String>("Something Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/get-all-exchanges")
	public ResponseEntity<Map<String, String>> getAllExchanges() {

		Map<String, String> map = serviceImpl.getAllExchanges();
		if (map != null) {
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.FOUND);
		} else {
			throw new CcyExchangeDoesnotExistsException("No Records Found");
		}

	}


	
}


