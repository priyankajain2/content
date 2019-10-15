create table Transaction (
    account_number number(12) NOT NULL,
    trans_id number(10) PRIMARY KEY,
    trans_type varchar(6) NOT NULL,
    trans_date_time TIMESTAMP NOT NULL,
    amount number(20,2) NOT NULL,
    description varchar(15) NOT NULL
);