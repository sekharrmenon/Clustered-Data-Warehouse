package com.datawarehouse.dto;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "proper_deal")
public class ValidDeal extends  DealModelDTO implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;

}
