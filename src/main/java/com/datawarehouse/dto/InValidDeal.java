package com.datawarehouse.dto;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "improper_deal")
public class InValidDeal extends  DealModelDTO implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	
}
