package com.Trading.FxTrading.entity;

public class CurrencyExchange {
	
	private String From;
	private String To;
	private String ccy;
	private double Amount;
	public String getFrom() {
		return From;
	}
	public void setFrom(String from) {
		From = from;
	}
	public String getTo() {
		return To;
	}
	public void setTo(String to) {
		To = to;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		this.Amount = amount;
	}
	
	@Override
	public String toString() {
		return "CurrencyExchane [from=" + From + ", to=" + To + ", ccy=" + ccy + ", amount=" + Amount + "]";
	}

}
