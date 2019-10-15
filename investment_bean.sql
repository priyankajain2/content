create table investment_bean(uci number(16) PRIMARY KEY,account_number number(11) FOREIGN KEY REFERENCES accounts(account_number),gold_units number(10,2) DEFAULT 0.00,silver_units number(10,2) DEFAULT 0.00);

insert into investment_bean values(1234561234561231,12345678901,50.00,158.5);

insert into investment_bean values(1234561234561232,12345678902,27.75,378.5);