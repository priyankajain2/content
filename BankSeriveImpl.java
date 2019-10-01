package com.cg.ibs.im.service;
import com.cg.ibs.im.service.BankService;
import com.cg.ibs.im.bean.MutualFund;
import com.cg.ibs.im.dao.InvestmentDao;
public class BankSeriveImpl implements BankService{
	InvestmentDao daoObject = new InvestmentDao();
	public void updateMFPlans(MutualFund[] mf) {
		daoObject.mf = mf;
	}
	public void updateGoldPrice(double goldPrice) {
		 daoObject.goldPrice = goldPrice;
	}
	public void updateSilverPrice(double silverPrice) {
		daoObject.silverPrice = silverPrice;
	}
}
