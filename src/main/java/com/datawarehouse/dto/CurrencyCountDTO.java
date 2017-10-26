package com.datawarehouse.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency_count")
public class CurrencyCountDTO {
	
	private Integer id;
	private String oderingCurrency;
	private BigInteger count ;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "from_currency")
	public String getOderingCurrency() {
		return oderingCurrency;
	}
	public void setOderingCurrency(String oderingCurrency) {
		this.oderingCurrency = oderingCurrency;
	}
	@Column(name = "count")
	public BigInteger getCount() {
		return count;
	}
	public void setCount(BigInteger count) {
		this.count = count;
	}
	
	

}
