package com.datawarehouse.dto;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DealModelDTO {

	private Integer id;
	private String fromCurrencyIso;
	private String toCurrencyIso;
	private Date date;
	private BigInteger amount;
	private String fileName;
	
	public DealModelDTO() {
	}

	public DealModelDTO(int id) {
		this.id = id;
	}
	
	public DealModelDTO(Integer id, String fromCurrencyIso, String toCurrencyIso, Date date, BigInteger amount,
			String fileName) {
		super();
		this.id = id;
		this.fromCurrencyIso = fromCurrencyIso;
		this.toCurrencyIso = toCurrencyIso;
		this.date = date;
		this.amount = amount;
		this.fileName = fileName;
	}
	
	@Id
	@Column(name="deal_unique_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "from_currency")
	public String getFromCurrencyIso() {
		return fromCurrencyIso;
	}
	public void setFromCurrencyIso(String fromCurrencyIso) {
		this.fromCurrencyIso = fromCurrencyIso;
	}
	@Column(name = "to_currency")
	public String getToCurrencyIso() {
		return toCurrencyIso;
	}
	public void setToCurrencyIso(String toCurrencyIso) {
		this.toCurrencyIso = toCurrencyIso;
	}
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name = "deal_amount")
	public BigInteger getAmount() {
		return amount;
	}
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}
	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
