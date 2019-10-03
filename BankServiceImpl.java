package com.cg.ibs.investment.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.cg.ibs.investment.dao.BankDao;
import com.cg.ibs.investment.dao.InvestmentDaoImpl;
import com.cg.ibs.investment.bean.MutualFund;
import com.cg.ibs.investment.exception.IBSException;

public class BankServiceImpl implements BankService {
	BankDao daoObject = new InvestmentDaoImpl();
	
	
	public boolean isValidGoldSilver(double price) {
		boolean check = false;
		if(price>0){
			check=true;
		}
		return check;
	}
	public boolean isValidMutualFund(MutualFund mutualFund){
		boolean check = false;
		if(mutualFund.getNav()>0 && mutualFund.getMfunits()>0){
			check=true;
		}
		return check;
	}
	public void updateGoldPrice(double goldPrice) throws IBSException {
		if(isValidGoldSilver(goldPrice)){
			daoObject.updateGoldPrice(goldPrice);
		}
		else{
			throw new IBSException("Please, Enter a valid price of Gold");
		}
	}
	public void updateSilverPrice(double silverPrice) throws IBSException {
		if(isValidGoldSilver(silverPrice)){
			daoObject.updateSilverPrice(silverPrice);
		}
		else{
			throw new IBSException("Please, Enter a valid price of Silver");
		}
	}
	public void updateMF(MutualFund mutualFund) throws IBSException {
		if(isValidMutualFund(mutualFund)){
			daoObject.updateMF(mutualFund);
		}
		else{
			throw new IBSException("enter a valid mutual funds units");
		}
	}
		
	}
