package com.Trading.Assignment2;

import java.util.ArrayList;


import java.util.List;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
    public class FX {


        private int tradeNo;
        private String customername;
        private String currencypair;
        private int amt;
        static  float rate = 66;
         static int globaltradenumber = 1;
		private static String validmsg;
		private static String validmsg1;
         private String rateAns;
         private String bookAns;
        
         FX(){
        	 
         }
    public FX(int tradeNo, String customername, String currencypair, int amt, String rateAns, String bookAns) {
		
			this.tradeNo = tradeNo;
			this.customername = customername;
			this.currencypair = currencypair;
			this.amt = amt;
			this.rateAns = rateAns;
			this.bookAns = bookAns;
		}
	public int getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(int tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCurrencypair() {
		return currencypair;
	}
	public void setCurrencypair(String currencypair) {
		this.currencypair = currencypair;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	public static float getRate() {
		return rate;
	}
	public static void setRate(float rate) {
		FX.rate = rate;
	}
	public String getRateAns() {
		return rateAns;
	}
	public void setRateAns(String rateAns) {
		this.rateAns = rateAns;
	}
	public String getBookAns() {
		return bookAns;
	}
	public void setBookAns(String bookAns) {
		this.bookAns = bookAns;
	}
	
	@Override
	public String toString() {
		return "FX [tradeNo=" + tradeNo + ", customername=" + customername + ", currencypair=" + currencypair + ", amt="
				+ amt + ", rateAns=" + rateAns + ", bookAns=" + bookAns + "]";
	}
	List<FX> ForeignExchanges = new ArrayList<>();
	
	@PostMapping("/book-trade")
	public String receivetrade(@RequestBody FX f) {
	f.bookTrade(f);
	ForeignExchanges.add(f);
	return "Trade Received" +f.display();
	}
        public void bookTrade(FX f) {
		
    	 double inrAmount = amt * rate;

            if (rateAns.equalsIgnoreCase("Yes")) {
               validmsg1="you are transferred to "+customername;

            }
            if(bookAns.equalsIgnoreCase("Book")){
               validmsg = "Trade for USDINR has been booked with rate " + rate + " , The amount of Rs " +
                    inrAmount + " will be transferred in 2 working days to " + customername + ".";
                globaltradenumber ++;


            }
            else {
                System.out.println("Invalid action. Trade is Canceled.");
                validmsg="";


            }
        }
	public String display() {
		return validmsg+" \n"+validmsg1;
	}
    @GetMapping("/print-trades")
    public List<FX> getTrades(){
    	return ForeignExchanges;
}
}
