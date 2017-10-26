package com.datawarehouse.serviceImpl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.datawarehouse.dao.DealDao;
import com.datawarehouse.dto.CurrencyCountDTO;
import com.datawarehouse.dto.DealDTO;
import com.datawarehouse.dto.InValidDeal;
import com.datawarehouse.dto.ValidDeal;
import com.datawarehouse.service.DealService;

@Service
public class DealServiceImpl implements DealService {
	
	@Autowired
	DealDao dealDao;
	
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	
	 private static final String DEAL_ID = "id";
	 private static final String FROM_CURRENCY = "fromCurrency";
	 private static final String TO_CURRENCY = "toCurrency";
	 private static final String DEAL_TIMESTAMP = "timestamp"; 
	 private static final String DEAL_AMOUNT = "amount";

	private static final Logger logger = LoggerFactory
	        .getLogger(DealService.class);
	
	public String validateDeals(List<CSVRecord> csvRecords, String name){
		List<DealDTO> validDeals = new ArrayList<>();
        List<DealDTO> inValidDeals = new ArrayList<>();
		for(CSVRecord record : csvRecords) {			
			DealDTO deal = new DealDTO();
			deal.setId(new Integer(record.get(DEAL_ID)));
			deal.setToCurrency(record.get(TO_CURRENCY));
			deal.setFromCurrency(record.get(FROM_CURRENCY));
			
			try {
				if(StringUtils.isEmpty(record.get(DEAL_TIMESTAMP))) {
					deal.setDealDate(null);
				}else {
					deal.setDealDate(dateFormatter.parse(record.get(DEAL_TIMESTAMP)));
				}			
			} catch (ParseException e) {
				logger.info("Date formatted exception"+e.getMessage());			
				return "failure";
				
			}
			try {
				deal.setAmount(new BigInteger(record.get(DEAL_AMOUNT)));	
				deal.setFileName(name);
			}catch (NumberFormatException e) {
				logger.info("Cannot convert invalid amount"+e.getMessage());
				return "failure";
				
			}
					
			if(StringUtils.isEmpty(deal.getFromCurrency()) ||
					StringUtils.isEmpty(deal.getToCurrency()) ||
					StringUtils.isEmpty(deal.getDealDate()) ||
					StringUtils.isEmpty(deal.getAmount())
					
			){
				inValidDeals.add(deal);
			}
			else{
				validDeals.add(deal);	
			}

		}
		List<CurrencyCountDTO> currencyCount= getCurrencyCount(validDeals);
		List<ValidDeal> valid = validEntity(validDeals);
		List<InValidDeal> invalid = invalidEntity(inValidDeals);
			
		logger.info("No of valid Deals"+validDeals.size());
		logger.info("No of invalid Deals"+inValidDeals.size());
		
	
		return dealDao.saveDeals(currencyCount,valid,invalid);

	}

	/**
	 * This method is used to create the DAO entity for persistence valid
	 *
	 * @param DealDTO
	 * 
	*/

	private List<ValidDeal> validEntity(List<DealDTO> validdeals) {
		List<ValidDeal>validDeal= new ArrayList<ValidDeal>();
		for(DealDTO deal : validdeals) {
			ValidDeal properDeal = new ValidDeal();
			properDeal.setId(deal.getId());
			properDeal.setDate(deal.getDealDate());
			properDeal.setFileName(deal.getFileName());
			properDeal.setFromCurrencyIso(deal.getFromCurrency());
			properDeal.setToCurrencyIso(deal.getToCurrency());
			properDeal.setAmount(deal.getAmount());
			//BeanUtils.copyProperties(deal,properDeal);
			validDeal.add(properDeal);
		}
		return validDeal;
	}
	
	/**
	 * This method is used to create the DAO entity for persistence invalid
	 *
	 * @param DealDTO
	 * 
	 */
	private List<InValidDeal> invalidEntity(List<DealDTO> invaliddeals) {
		List<InValidDeal>invalidDeal= new ArrayList<InValidDeal>();
		for(DealDTO deal : invaliddeals) {
			InValidDeal improperDeal = new InValidDeal();
			improperDeal.setId(deal.getId());
			improperDeal.setDate(deal.getDealDate());
			improperDeal.setFileName(deal.getFileName());
			improperDeal.setFromCurrencyIso(deal.getFromCurrency());
			improperDeal.setToCurrencyIso(deal.getToCurrency());
			improperDeal.setAmount(deal.getAmount());
			//BeanUtils.copyProperties(deal,improperDeal );
			invalidDeal.add(improperDeal);
		}
		return invalidDeal;
	}
	
	
	/**
	 * This method is used to get the accumulative count of the currency
	 *
	 * @param DealDTO
	 * 
	 */

	private List<CurrencyCountDTO> getCurrencyCount(List<DealDTO> validDeals) {
		Map<String,Integer> totalCountMap= new HashMap<String, Integer>();
		 for(DealDTO deal :validDeals) {
			 if(totalCountMap.containsKey(deal.getFromCurrency())){
					int value = Integer.parseInt(String.valueOf((totalCountMap.get(deal.getFromCurrency()))));
					totalCountMap.put(deal.getFromCurrency(), ++value);
				}
				else{
					totalCountMap.put(deal.getFromCurrency(), 1);
				}
		 }	 
		 List<CurrencyCountDTO> count = new ArrayList<CurrencyCountDTO>();
			for (Object key : totalCountMap.keySet()) {
				CurrencyCountDTO currencyDeal = new CurrencyCountDTO();
				currencyDeal.setCount(new BigInteger(String.valueOf(totalCountMap.get(key))));
				currencyDeal.setOderingCurrency(key.toString());
				count.add(currencyDeal);
			}
		return count;
	}

	@Override
	public boolean checkFileExist(String name) {
		return dealDao.checkfileName(name);
	}
}
