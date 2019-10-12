package com.cg.ibs.investment.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.RegexConversion;

import com.cg.ibs.investment.bean.BankMutualFund;
import com.cg.ibs.investment.bean.MutualFund;
import com.cg.ibs.investment.exception.IBSException;

class BankServiceImplTest {
	private BankServiceImpl bank;

	@BeforeEach
	void setUp() {
		bank = new BankServiceImpl();
	}

	@Test
	void testIsValidGoldSilverPositive() {
		assertEquals(true, bank.isValidGoldSilver(10.00));
	}

	@Test
	void testisvalidgoldSilverNegative() {
		assertNotEquals(true, bank.isValidGoldSilver(-10));
	}

	@Test
	void testIsValidMutualFundPositive() {
		assertEquals(true, bank.isValidMutualFund(new BankMutualFund(10001, "IBS Basic Plan", 200)));
	}

	@Test
	void testIsValidMutualFundNegative() {
		assertNotEquals(true, bank.isValidMutualFund(new BankMutualFund(10001, "IBS Basic Plan", -200)));
	}

	/*
	 * @Disabled
	 * 
	 * @Test void testValidateBankPositive() {
	 * 
	 * }
	 */

	@Test
	void testUpdateGoldPrice() {
		Assertions.assertThrows(IBSException.class, () -> {
			bank.updateGoldPrice(-100);
		});
	}

	@Test
	void testUpdateSilverPrice() {
		Assertions.assertThrows(IBSException.class, () -> {
			bank.updateSilverPrice(-100);
		});
	}

	@Test
	void testUpdateMFPlanPositiveCaseIfIdAlreadyExits() {
		Assertions.assertThrows(IBSException.class, () -> {
			bank.addMF(new BankMutualFund(10001, "IBS Basic Plan", 200));
		});
	}
}
