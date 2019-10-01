package com.cg.ibs.im.service;
import com.cg.ibs.im.bean.MutualFund;
public interface BankService {
	public void updateMFPlans(MutualFund[] mf);
	public void updateGoldPrice(double goldPrice);
	public void updateSilverPrice(double silverPrice);
}
