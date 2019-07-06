select * from department;
--Employee table
create table employee(
	employee_id serial primary key, 
	department_id integer references department (department_id) not null,
	fullname varchar not null,
	email varchar unique not null,
	employee_password varchar not null,
	available_reimbursement numeric not null default 1000.00,
	awarded_reimbursement numeric not null default 0.00,
	pending_reimbursement numeric not null default 0.00
);

insert into employee (department_id, fullname, email, employee_password) values (4, 'Marin Enters', 'mar@icloud.com', '1234');
insert into department (level) values ('BenCo');
--Employee Level table
create table department(
	department_id serial primary key,
	level varchar
);
select * from reimbursement_application;

--Application Table--

create table reimbursement_application(
	reimbursement_id serial primary key,
	employee_id integer references employee(employee_id),
	submission_date timestamptz default localtimestamp,
	status integer default 1,
	additional_information varchar default null,
	event_type varchar not null,
	event_date date not null,
	event_time varchar not null,
	event_location varchar not null,
	event_cost numeric not null,
	description varchar not null,
	percentage_payback numeric not null,
	grading_format varchar not null,
	passing_grade varchar not null,
	completed_grade varchar default null
);

create table reimbursement_application_status(
	status serial primary key,
	approval_type varchar not null
);
select r.reimbursement_id, e.email, e.fullname, r.event_type, r.event_date, r.event_cost, 
r.description, e.available_reimbursement, e.pending_reimbursement, e.awarded_reimbursement from 
reimbursement_application r inner join employee e on r.employee_id = e.employee_id where status = 1;
create table reimbursement_application_status(
	status_id serial primary key,
	status_type varchar
);

select * from reimbursement_application inner join employee where employee_id = 1;
insert into reimbursement_application_status values (8, 'Denied');
