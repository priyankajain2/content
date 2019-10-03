package com.cg.ibs.investment.service;

import java.util.Map;
import java.util.Set;

import com.cg.ibs.investment.bean.MutualFund;
import com.cg.ibs.investment.exception.IBSException;

public interface BankService {
	public void updateGoldPrice(double goldPrice) throws IBSException;
	public void updateSilverPrice(double silverPrice) throws IBSException;
	public void updateMF(MutualFund mutualFund) throws IBSException;
}
