create table bank_mutual_fund(mf_plan_id number(5) PRIMARY KEY,mf_title varchar2() NOT NULL,nav number(5,2) NOT NULL);

insert into bank_mutual_fund values(10001,'IBS CHILD',200.00);

insert into bank_mutual_fund values(10002,'IBS ADULT',150.00);