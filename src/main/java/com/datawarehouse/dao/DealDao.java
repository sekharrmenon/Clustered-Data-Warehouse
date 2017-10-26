package com.datawarehouse.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datawarehouse.dto.CurrencyCountDTO;
import com.datawarehouse.dto.DealDTO;
import com.datawarehouse.dto.InValidDeal;
import com.datawarehouse.dto.ValidDeal;
import com.datawarehouse.service.DealService;

@Repository
public class DealDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory
	        .getLogger(DealDao.class);

	public String saveDeals(List<CurrencyCountDTO> currencyCount, List<ValidDeal> validDeals, List<InValidDeal> inValidDeals)  {
		
		try {
			saveCurrencyCount(currencyCount);
			if(validDeals.size()>0) {
				saveDeals(validDeals);
			}
			if(inValidDeals.size()>0) {
				saveDeals(inValidDeals);
			}
		} catch (Exception e) {
			logger.info("Error Inserting into the db" + e.getMessage());
			return "Failure";
		}	
		return "Success";
	}
	
	/**
	 * This method is used to save both valid and invalid deal
	 *
	 * @param ValidDeal,InValidDeal
	 * 
	*/

	private <T> void saveDeals(Collection<T> totalDeals)throws Exception{
		Session session = sessionFactory.openSession();
	    try {
			Transaction tx = session.beginTransaction();
			for(T deal:totalDeals) {
				session.save(deal);
			}
			tx.commit();
			session.close();
			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
		
	
	/**
	 * This method is used to save accumulative currency
	 *
	 * @param CurrencyCountDTO
	 * 
	*/
	private void saveCurrencyCount(List<CurrencyCountDTO> currencyCount)throws HibernateException{
		Session session = sessionFactory.openSession();
	    try {
			Transaction tx = session.beginTransaction();
			for(CurrencyCountDTO currency:currencyCount) {
				session.save(currency);
			}
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkfileName(String name) {
		Session session = sessionFactory.openSession();
		boolean status=false;
	    try {
			Transaction tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(ValidDeal.class);
			criteria.add(Restrictions.eq("fileName", name));
			criteria.setProjection(Projections.rowCount());
			 long count = (Long) criteria.uniqueResult();
			tx.commit();
			session.close();
			 if(count != 0){ status= true;}
			   else{ status= false; }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}
		

}
