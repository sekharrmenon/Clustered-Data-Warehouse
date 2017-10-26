package com.datawarehouse.dto;

import java.math.BigInteger;
import java.util.Date;

public class DealDTO {
	
	private Integer id;
	private String fromCurrencyIso;
	private String toCurrencyIso;
	private Date date;
	private BigInteger amount;
	private String fileName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFromCurrency() {
		return fromCurrencyIso;
	}

	public void setFromCurrency(String fromCurrencyIso) {
		this.fromCurrencyIso = fromCurrencyIso;
	}

	public String getToCurrency() {
		return toCurrencyIso;
	}

	public void setToCurrency(String toCurrencyIso) {
		this.toCurrencyIso = toCurrencyIso;
	}

	public Date getDealDate() {
		return date;
	}

	public void setDealDate(Date date) {
		this.date = date;
	}

	public BigInteger getAmount() {
		return amount;
	}
	
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
