package com.cg.ibs.investment.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import com.cg.ibs.common.bean.TransactionBean;
import com.cg.ibs.common.bean.TransactionType;
import com.cg.ibs.investment.bean.BankMutualFund;
import com.cg.ibs.investment.bean.InvestmentBean;
import com.cg.ibs.investment.bean.MutualFund;

import com.cg.ibs.investment.dao.ConnectionProvider;
import com.cg.ibs.investment.exception.IBSException;

public class InvestmentDaoImpl implements BankDao, ClientDao {

	// Declaring data members
	private static double goldPrice;
	private static double silverPrice;
	private static HashMap<Integer, BankMutualFund> mutualFunds = new HashMap<>();
	private static Set<MutualFund> mutualFundscust1;
	private static Set<MutualFund> mutualFundscust2;
	private static Set<MutualFund> mutualFundscust3;
	private static Set<MutualFund> mutualFundscust4;
	private static Set<MutualFund> mutualFundscust5;
	private static HashMap<String, InvestmentBean> investmentBeans = new HashMap<>();
	private static TreeSet<TransactionBean> transactionBeancust1;
	private static TreeSet<TransactionBean> transactionBeancust2;
	private static TreeSet<TransactionBean> transactionBeancust3;
	private static TreeSet<TransactionBean> transactionBeancust4;
	private static TreeSet<TransactionBean> transactionBeancust5;
	static long count;

	static {

		// Assigning values to data members
		mutualFundscust1 = new HashSet<>();
		mutualFundscust2 = new HashSet<>();
		mutualFundscust3 = new HashSet<>();
		mutualFundscust4 = new HashSet<>();
		mutualFundscust5 = new HashSet<>();

		transactionBeancust1 = new TreeSet<>(new TransactionsComparator());
		transactionBeancust2 = new TreeSet<>(new TransactionsComparator());
		transactionBeancust3 = new TreeSet<>(new TransactionsComparator());
		transactionBeancust4 = new TreeSet<>(new TransactionsComparator());
		transactionBeancust5 = new TreeSet<>(new TransactionsComparator());

		BankMutualFund plan1 = new BankMutualFund(10001, "IBS Basic Plan", 200);
		BankMutualFund plan2 = new BankMutualFund(10002, "IBS Child Plan", 400);
		BankMutualFund plan3 = new BankMutualFund(10003, "IBS Smart Plan", 600);

		mutualFunds.put(10001, plan1);
		mutualFunds.put(10002, plan2);
		mutualFunds.put(10003, plan3);
		LocalDate dt1 = LocalDate.parse("2018-12-07");
		mutualFundscust1.add(new MutualFund(10001, "IBS Basic Plan", 200, 50, dt1, null, true));
		mutualFundscust2.add(new MutualFund(10002, "IBS Premium Plan", 400, 50, dt1, null, true));
		mutualFundscust3.add(new MutualFund(10003, "IBS Smart Plan", 600, 50, dt1, null, true));
		mutualFundscust4.add(new MutualFund(10003, "IBS Smart Plan", 600, 50, dt1, null, true));
		mutualFundscust5.add(new MutualFund(10003, "IBS Smart Plan", 600, 50, dt1, null, true));

		TransactionBean trxn1 = new TransactionBean(new BigInteger("100100100"), TransactionType.CREDIT, dt1,
				new BigDecimal("2000"));
		TransactionBean trxn2 = new TransactionBean(new BigInteger("100100101"), TransactionType.DEBIT, dt1,
				new BigDecimal("3000"));
		TransactionBean trxn3 = new TransactionBean(new BigInteger("100100102"), TransactionType.CREDIT, dt1,
				new BigDecimal("4000"));
		TransactionBean trxn4 = new TransactionBean(new BigInteger("100100103"), TransactionType.CREDIT, dt1,
				new BigDecimal("1000"));
		TransactionBean trxn5 = new TransactionBean(new BigInteger("100100104"), TransactionType.CREDIT, dt1,
				new BigDecimal("6000"));
		transactionBeancust1.add(trxn1);
		transactionBeancust2.add(trxn2);
		transactionBeancust3.add(trxn3);
		transactionBeancust4.add(trxn4);
		transactionBeancust5.add(trxn5);

		InvestmentBean cust1 = new InvestmentBean("4000500060000001", "Sachin", "mumbai", 220.0, 2000.0, 100000.0,
				mutualFundscust1, transactionBeancust1);
		InvestmentBean cust2 = new InvestmentBean("4000500060000002", "Gautam", "delhi", 300.0, 3000.0, 50000.0,
				mutualFundscust2, transactionBeancust2);
		InvestmentBean cust3 = new InvestmentBean("4000500060000003", "Sehwag", "noida", 200.0, 2000.0, 100000.0,
				mutualFundscust1, transactionBeancust3);
		InvestmentBean cust4 = new InvestmentBean("4000500060000004", "Dhoni", "ranchi", 100.0, 5000.0, 100000.0,
				mutualFundscust1, transactionBeancust4);
		InvestmentBean cust5 = new InvestmentBean("4000500060000005", "Kohli", "gurgaon", 250.0, 4000.0, 100000.0,
				mutualFundscust1, transactionBeancust5);
		InvestmentBean bank = new InvestmentBean("Bank", "password");
		investmentBeans.put("Sachin", cust1);
		investmentBeans.put("Gautam", cust2);
		investmentBeans.put("Sehwag", cust3);
		investmentBeans.put("Dhoni", cust4);
		investmentBeans.put("Kohli", cust5);
		investmentBeans.put("Bank", bank);

		goldPrice = 4000;
		silverPrice = 400;
		count = 0;

	}

	// Method to view Gold Price
	@Override
	public double viewGoldPrice() {
		double gold_price = 0;
		try {
			Connection con = ConnectionProvider.getInstance().getConnection();
			PreparedStatement pst = con.prepareStatement(QueryMapper.VIEW_GOLD_PRICE);
			pst.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ResultSet resultSet = pst.executeQuery();

			if (resultSet.next()) {

				gold_price = resultSet.getDouble(2);
			}
			pst.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return gold_price;

	}

	// Method to view Silver Price
	@Override
	public double viewSilverPrice() {
		double silver_price = 0;
		try {
			Connection con = ConnectionProvider.getInstance().getConnection();
			PreparedStatement pst = con.prepareStatement(QueryMapper.VIEW_SILVER_PRICE);
			System.out.println(LocalDate.now());
			pst.setDate(1, java.sql.Date.valueOf(LocalDate.now()));

			ResultSet resultSet = pst.executeQuery();
			if (resultSet.next()) {

				silver_price = resultSet.getDouble(2);
			}
			pst.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return silver_price;
	}

	// Method to view Investments of the Customer
	@Override
	public InvestmentBean viewInvestments(String uCI) {

		return investmentBeans.get(uCI);
	}

	// Method to view Mutual Fund plans provided by the bank
	@Override
	public HashMap<Integer, BankMutualFund> viewMF() {

		return mutualFunds;
	}

	//// Method to update transactions of the customer
	@Override
	public void updateTransaction(String uCI, int choice, InvestmentBean investmentBean, double amount) {
		if (choice == 1) {

			BigInteger TransactionId = new BigInteger("1001001000031").add(BigInteger.valueOf(count));
			TransactionBean trxn1 = new TransactionBean(TransactionId, TransactionType.DEBIT, LocalDate.now(),
					BigDecimal.valueOf(amount));
			investmentBean.getTransactionList().add(trxn1);
			investmentBean.setTransactionList(investmentBean.getTransactionList());
			count++;
		} else if (choice == 2) {

			BigInteger TransactionId = new BigInteger("10010010000311").add(BigInteger.valueOf(count));
			TransactionBean trxn1 = new TransactionBean(TransactionId, TransactionType.CREDIT, LocalDate.now(),
					BigDecimal.valueOf(amount));
			investmentBean.getTransactionList().add(trxn1);
			investmentBean.setTransactionList(investmentBean.getTransactionList());
			count++;
		}

	}

	// Method to update Gold Price
	@Override
	public boolean updateGoldPrice(double goldprice) {
		boolean status = false;
		try {
			Connection con = ConnectionProvider.getInstance().getConnection();
			PreparedStatement pst = con.prepareStatement(QueryMapper.VIEW_GOLD_PRICE);
			pst.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				status = false;
			} else {
				PreparedStatement pst1 = con.prepareStatement(QueryMapper.UPDATE_GOLD_PRICE);
				pst1.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
				pst1.setDouble(2, goldprice);
				pst1.execute();
				status = true;
			}

		} catch (SQLException | IOException e) {
		}
		return status;

	}

	// Method to update Silver Price
	@Override
	public boolean updateSilverPrice(double y) {
		boolean status = false;
		try {
			Connection con = ConnectionProvider.getInstance().getConnection();
			PreparedStatement pst = con.prepareStatement(QueryMapper.VIEW_SILVER_PRICE);
			pst.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				status = false;
			} else {
				PreparedStatement pst1 = con.prepareStatement(QueryMapper.UPDATE_SILVER_PRICE);
				pst1.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
				pst1.setDouble(2, y);
				pst1.execute();
				status = true;
			}

		} catch (SQLException | IOException e) {
		}
		return status;

	}

	// Method to add new Mutual Fund plans
	@Override
	public boolean addMF(BankMutualFund mutualFund) {
		boolean status = false;
		if (!mutualFunds.containsKey(mutualFund.getmfid())) {
			mutualFunds.put(mutualFund.getmfid(), mutualFund);
			status = true;
		}
		return status;
	}

	// Method to view details of Bank representative
	@Override
	public InvestmentBean viewDetails(String userId) {
		return investmentBeans.get(userId);
	}

	// Method to update Gold and Silver units of the customer
	@Override
	public void updateUnits(String userId, double units, InvestmentBean investmentBean, int choice) throws IBSException {
		if (choice == 1) {
			try {
				Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.GOLD_UNITS);
				System.out.println(LocalDate.now());
				pst.setString(1,getUcibyUseriD(userId));
				pst.setDouble(3, investmentBean.getGoldunits() + units);
				PreparedStatement preparedStatement = con.prepareStatement(QueryMapper.BALANCE);
				preparedStatement.setString(2, getUcibyUseriD(userId));
				preparedStatement.setDouble(3, investmentBean.getBalance() - units * viewGoldPrice());
				preparedStatement.execute();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}

			// investmentBean.setGoldunits(investmentBean.getGoldunits() + units);
			// investmentBean.setBalance(investmentBean.getBalance() - units * goldPrice);
		} else if (choice == 2) {
			try {
				Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.GOLD_UNITS);
				System.out.println(LocalDate.now());
				pst.setString(1,getUcibyUseriD(userId));
				pst.setDouble(3, investmentBean.getGoldunits() - units);
				PreparedStatement preparedStatement = con.prepareStatement(QueryMapper.BALANCE);
				preparedStatement.setString(2, getUcibyUseriD(userId));
				preparedStatement.setDouble(3, investmentBean.getBalance() + units * viewGoldPrice());
				preparedStatement.execute();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			// investmentBean.setGoldunits(investmentBean.getGoldunits() - units);
			// investmentBean.setBalance(investmentBean.getBalance() + units * goldPrice);
		} else if (choice == 3) {
			try {
				Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.SILVER_UNITS);
				System.out.println(LocalDate.now());
				pst.setString(1,getUcibyUseriD(userId));
				pst.setDouble(4, investmentBean.getGoldunits() + units);
				PreparedStatement preparedStatement = con.prepareStatement(QueryMapper.BALANCE);
				preparedStatement.setString(2, getUcibyUseriD(userId));
				preparedStatement.setDouble(3, investmentBean.getBalance() - units * viewGoldPrice());
				preparedStatement.execute();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			// investmentBean.setSilverunits(investmentBean.getSilverunits() + units);
			// investmentBean.setBalance(investmentBean.getBalance() - units * silverPrice);
		} else if (choice == 4) {
			try {
				Connection con = ConnectionProvider.getInstance().getConnection();
				PreparedStatement pst = con.prepareStatement(QueryMapper.SILVER_UNITS);
				System.out.println(LocalDate.now());
				pst.setString(1,getUcibyUseriD(userId));
				pst.setDouble(4, investmentBean.getGoldunits() - units);
				PreparedStatement preparedStatement = con.prepareStatement(QueryMapper.BALANCE);
				preparedStatement.setString(2, getUcibyUseriD(userId));
				preparedStatement.setDouble(3, investmentBean.getBalance() + units * viewGoldPrice());
				preparedStatement.execute();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			// investmentBean.setSilverunits(investmentBean.getSilverunits() - units);
			// investmentBean.setBalance(investmentBean.getBalance() + units * silverPrice);
		}

	}

	public String getUcibyUseriD(String userId) throws IBSException {
		String uci = null;
		try {
			Connection con = ConnectionProvider.getInstance().getConnection();
			PreparedStatement pst = con.prepareStatement(QueryMapper.GET_UCI);
			pst.setString(1, userId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uci = rs.getString("uci");

			}
		} catch (SQLException | IOException e) {
			throw new IBSException(e.getMessage());

		}
		return uci;
	}

	// Method to invest in Mutual funds for a customer
	@Override
	public void addMFInvestments(double mfAmount, int mfId, InvestmentBean investmentBean) {

		double nav = mutualFunds.get(mfId).getNav();
		MutualFund mutualFund = new MutualFund();
		mutualFund.setmfid(mfId);
		mutualFund.setTitle(mutualFunds.get(mfId).getTitle());
		mutualFund.setNav(mutualFunds.get(mfId).getNav());
		LocalDate dt = LocalDate.now();
		double mfunits = (mfAmount / nav);
		mutualFund.setMfUnits(mfunits);
		mutualFund.setOpeningDate(dt);
		mutualFund.setStatus(true);

		investmentBean.getFunds().add(mutualFund);
		investmentBean.setFunds(investmentBean.getFunds());
		investmentBean.setBalance(investmentBean.getBalance() - mfAmount);

	}

	// Method to withdraw Mutual fund of a customer
	@Override
	public void withdrawMF(String userId, MutualFund mutualFund, InvestmentBean investmentBean) {
		investmentBean.setBalance(investmentBean.getBalance() + mutualFund.getMfAmount());

		investmentBean.getFunds().remove(mutualFund);

	}

	// Method to save Transactions history of a customer
	@Override
	public TreeSet<TransactionBean> getTransactions(String userId) {
		return investmentBeans.get(userId).getTransactionList();

	}

}