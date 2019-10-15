create table mutual_fund(uci number(16) FOREIGN KEY REFERENCES investment_bean(uci),mf_id number(3) PRIMARY KEY,mf_plan_id number(5) FOREIGN KEY REFERENCES BANK_MUTUAL_FUND(MF_PLAN_ID),mf_units number(10,2),mf_amount number(20,2),opening_date DATE,closing_date DATE DEFAULT "N/A");

insert into mutual_fund values(1234561234561231,101,10001,270.00,9000.00,'2019-11-19');

insert into mutual_fund values(1234561234561232,102,10002,300.00,90000.00,'2005-11-19','2018-05-20');