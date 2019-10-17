package com.cg.ibs.investment.dao;

public interface QueryMapper {
	public static final String VIEW_GOLD_PRICE = "select update_date,gold_price from gold_price where update_date= ?";
	public static final String VIEW_SILVER_PRICE = "select update_date,silver_price from silver_price where update_date= ?";
	public static final String UPDATE_GOLD_PRICE = "insert into gold_price values(?,?)";
	public static final String UPDATE_SILVER_PRICE = "insert into silver_price values(?,?)";
	public static final String GOLD_UNITS = "update investment_bean set gold_units=(?) where uci=?";
	public static final String SILVER_UNITS = "update investment_bean set silver_units=(?) where uci=?";
	public static final String BALANCE = "update accounts set current_balance=(?) where uci=?";
	public static final String BUY_GOLD = "select investment_bean.gold_units,accounts.current_balance FROM investment_bean INNER JOIN accounts ON investment_bean.account_number=accounts.account_number";
	public static final String GET_UCI ="SELECT uci, user_id from customers WHERE user_id=?";
}
