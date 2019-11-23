insert into medicalfile values(1);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','test1',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test',1);
insert into patient values (1, 1);

insert into medicalfile values(2);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(false,'test','test','test2',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test2',2);
insert into patient values (2, 2);

insert into medicalfile values(3);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(false,'test','test','test3',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test3',3);
insert into patient values (3, 3);

insert into medicalfile values(4);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','test4',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test4',4);
insert into patient values (4, 4);

insert into medicalfile values(5);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','test5',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test5',5);
insert into patient values (5, 5);

insert into medicalfile values(6);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(false,'test','test','test6',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test6',6);
insert into patient values (6, 6);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','test7',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test7',7);
insert into medical_staff (id,on_vacation) values (7,false);            
insert into nurse values (7);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','test8',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test8',8);
insert into medical_staff (id,on_vacation) values (8,false);            
insert into doctor (id,specialization) values (8,'Stomatologist');

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','test9',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test9',9);
insert into clinic_admin (id,clinic) values (9, 1);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'SECRET','Novi Sad','admin@team47.com',true,'SECRET','SECRET','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','Serbia','SECRET','0000',10);
insert into clinic_centre_admin (id) values (10);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','test11',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test11',11);
insert into medical_staff (id,on_vacation) values (11,false);            
insert into nurse values (11);

SELECT setval('hibernate_id_seq"', 21, true);
ALTER SEQUENCE hibernate_id_seq RESTART WITH 22;

insert into authority (name) values ('ROLE_PATIENT');
insert into authority (name) values ('ROLE_DOCTOR');
insert into authority (name) values ('ROLE_NURSE');
insert into authority (name) values ('ROLE_CADMIN');
insert into authority (name) values ('ROLE_CCADMIN');

insert into user_authority (user_id, authority_id) values (1,1);
insert into user_authority (user_id, authority_id) values (2,1);
insert into user_authority (user_id, authority_id) values (3,1); 
insert into user_authority (user_id, authority_id) values (4,1);
insert into user_authority (user_id, authority_id) values (5,1); 
insert into user_authority (user_id, authority_id) values (6,1);
insert into user_authority (user_id, authority_id) values (7,3); 
insert into user_authority (user_id, authority_id) values (8,2);
insert into user_authority (user_id, authority_id) values (9,4);
insert into user_authority (user_id, authority_id) values (10,5); 
insert into user_authority (user_id, authority_id) values (11,3); 


