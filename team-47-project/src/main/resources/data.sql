insert into medicalfile values(1);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'adresa1','Liverpool','test1',true,'John','Doe','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','England','064521520','test',1);
insert into patient values (1, 1);


insert into medicalfile values(2);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(false,'adresa2','test','test2',true,'Pera','Peric','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','Serbia','064578212','test2',2);
insert into patient values (2, 2);

insert into medicalfile values(3);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(false,'adresa3','Sabac','test3',true,'Mitar','Miric','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','Serbia','065211225','test3',3);
insert into patient values (3, 3);

insert into medicalfile values(4);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'adresa4','Novi Sad','test4',true,'Pera','Petrovic','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','Serbia','065322252','test4',4);
insert into patient values (4, 4);



insert into medicalfile values(5);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','Sabac','test5',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','Serbia','064578522','test5',5);
insert into patient values (5, 5);

insert into medicalfile values(6);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(false,'test','test','test6',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test6',6);
insert into patient values (6, 6);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','test7',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test7',7);
insert into medical_staff (id,on_vacation,clinic_id) values (7,false,1);            
insert into nurse values (7);

------------------------------- Doctors
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','doctor1',true,'Jovana','Jovanic','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','doctor1',8);
insert into medical_staff (id,on_vacation,clinic_id) values (8,false,1);            
insert into doctor (id,specialization,shift) values (8,'Ginecologist',1);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','doctor2',true,'Zivko','Zivkovic','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','doctor2',12);
insert into medical_staff (id,on_vacation,clinic_id) values (12,false,2);            
insert into doctor (id,specialization,shift) values (12,'Stomatologist',1);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','doctor3',true,'Nikola','Nikolic','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','doctor3',13);
insert into medical_staff (id,on_vacation,clinic_id) values (13,false,2);            
insert into doctor (id,specialization,shift) values (13,'Plastic surgeon',2);

--------------------------------
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','test9',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test9',9);
insert into clinic_admin (id,clinic) values (9, 1);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','cadmin',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','cadmin',14);
insert into clinic_admin (id,clinic) values (14, 2);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'SECRET','Novi Sad','admin@team47.com',true,'SECRET','SECRET','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','Serbia','SECRET','0000',10);
insert into clinic_centre_admin (id) values (1);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','test11',true,'test','test',null,'$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','test11',11);
insert into medical_staff (id,on_vacation) values (11,false);            
insert into nurse values (11);

-----------------------------------------------------------------------Rooms
insert into room (id,name,number,clinic_id,roomtype) values (1, 'room1','1',1,'Operation');
insert into room (id,name,number,clinic_id,roomtype) values (2, 'room2','2',1,'Operation');
insert into room (id,name,number,clinic_id,roomtype) values (3, 'room3','3',1,'Examination');
insert into room (id,name,number,clinic_id,roomtype) values (4, 'room4','4',1,'Examination');
insert into room (id,name,number,clinic_id,roomtype) values (5, 'room5','5',1,'Examination');
insert into room (id,name,number,clinic_id,roomtype) values (6, 'room6','6',1,'Examination');
insert into room (id,name,number,clinic_id,roomtype) values (7, 'room7','7',2,'Operation');
insert into room (id,name,number,clinic_id,roomtype) values (8, 'room8','8',2,'Examination');
insert into room (id,name,number,clinic_id,roomtype) values (9, 'room9','9',2,'Examination');
insert into room (id,name,number,clinic_id,roomtype) values (10, 'room10','10',3,'Operation');
insert into room (id,name,number,clinic_id,roomtype) values (11, 'room11','11',3,'Examination');
insert into room (id,name,number,clinic_id,roomtype) values (12, 'room12','12',3,'Examination');

ALTER SEQUENCE room_id_seq RESTART WITH 13;


------------------------------------------------------------------------

SELECT setval('hibernate_id_seq', 21, true);
ALTER SEQUENCE hibernate_id_seq RESTART WITH 22;
SELECT setval('clinic_id_seq', 21, true);
ALTER SEQUENCE clinic_id_seq RESTART WITH 10;
SELECT setval('procedure_id_seq', 21, true);
ALTER SEQUENCE procedure_id_seq RESTART WITH 22;

SELECT setval('rate_id_seq', 21, true);
ALTER SEQUENCE rate_id_seq RESTART WITH 22;

insert into authority (name) values ('ROLE_PATIENT');
insert into authority (name) values ('ROLE_DOCTOR');
insert into authority (name) values ('ROLE_NURSE');
insert into authority (name) values ('ROLE_CADMIN');
insert into authority (name) values ('ROLE_CCADMIN');
insert into authority (name) values ('ROLE_USER');

insert into user_authority (user_id, authority_id) values (1,1);
insert into user_authority (user_id, authority_id) values (2,1);
insert into user_authority (user_id, authority_id) values (3,1); 
insert into user_authority (user_id, authority_id) values (4,1);
insert into user_authority (user_id, authority_id) values (5,1); 
insert into user_authority (user_id, authority_id) values (6,1);
insert into user_authority (user_id, authority_id) values (7,3); 
insert into user_authority (user_id, authority_id) values (8,2);
insert into user_authority (user_id, authority_id) values (12,2);
insert into user_authority (user_id, authority_id) values (13,2);
insert into user_authority (user_id, authority_id) values (9,4);
insert into user_authority (user_id, authority_id) values (10,5); 
insert into user_authority (user_id, authority_id) values (11,3);
insert into user_authority (user_id, authority_id) values (14,4);

insert into clinic(id,name,address,description) values (1,'klinika1','dr.andre','lepa bas onako');
insert into clinic(id,name,address,description) values (2,'klinika2','Ulica 2','nije najsrecnija');
insert into clinic(id,name,address,description) values (3,'klinika3','Ulica 3','odlicna');

--- all users 
insert into user_authority (user_id, authority_id) values (1,6);
insert into user_authority (user_id, authority_id) values (2,6);
insert into user_authority (user_id, authority_id) values (3,6); 
insert into user_authority (user_id, authority_id) values (4,6);
insert into user_authority (user_id, authority_id) values (5,6); 
insert into user_authority (user_id, authority_id) values (6,6);
insert into user_authority (user_id, authority_id) values (7,6); 
insert into user_authority (user_id, authority_id) values (8,6);
insert into user_authority (user_id, authority_id) values (9,6);
insert into user_authority (user_id, authority_id) values (10,6); 
insert into user_authority (user_id, authority_id) values (11,6);
insert into user_authority (user_id, authority_id) values (12,6);
insert into user_authority (user_id, authority_id) values (13,6);
insert into user_authority (user_id, authority_id) values (14,6);

-- Doctors
insert into doctors()
insert into examination(id,type,date,patient_id,doctor_id,room) values(1,'obican pregled','04.09.2020 15:24',2,13,8);
insert into examination(id,type,date,patient_id,doctor_id,room) values(2,'operacija','07.10.2019 15:24',3,8,3);
insert into examination(id,type,date,patient_id,doctor_id,room) values(3,'kontrola','04.09.2018 20:24',2,8,4);

-------------------------------------- Dodavanje doktora,cadmin u klinike
insert into clinic_doctor values(1,8);
insert into clinic_doctor values(2,12);
insert into clinic_doctor values(2,13);
insert into clinic_admins values(1,9);
insert into clinic_admins values(2,14);
-------------------------------------

------------------------------------- Dodavanje room u klinike
insert into clinic_rooms values(1,1);
insert into clinic_rooms values(1,2);
insert into clinic_rooms values(1,3);
insert into clinic_rooms values(1,4);
insert into clinic_rooms values(1,5);
insert into clinic_rooms values(1,6);
insert into clinic_rooms values(2,7);
insert into clinic_rooms values(2,8);
insert into clinic_rooms values(2,9);
insert into clinic_rooms values(3,10);
insert into clinic_rooms values(3,11);
insert into clinic_rooms values(3,12);
-------------------------------------------


insert into diagnosis (id, description, name) values (1, 'Antibiotici, il operacija jbg', 'Upala krajnika');
insert into diagnosis (id, description, name) values (2, 'Fervex i kapi za nos', 'Prehlada');
insert into diagnosis (id, description, name) values (3, 'Mirovanje, cajevi, krompir u carapu', 'Temperatura, grip');

insert into prescription (id, name) values (1, 'Fervex');
insert into prescription (id, name) values (2, 'Brufen');
insert into prescription (id, name) values (3, 'Probiotik');

--Prvo pravim entrijeve
insert into med_file_en(id,description,diagnosis_id) values(1,'opis bolesti 1',1);
insert into med_file_en(id,description,diagnosis_id)  values(2,'opis bolesti 2',2);

--sada povezujem sa medical file
insert into medicalfile_entries(medical_file_id,entries_id) values(2,1);
insert into medicalfile_entries(medical_file_id,entries_id) values(2,2);

SELECT setval('med_en_id_seq', 2, true);
ALTER SEQUENCE procedure_id_seq RESTART WITH 3;

ALTER SEQUENCE diag_id_seq RESTART WITH 10;
ALTER SEQUENCE pres_id_seq RESTART WITH 10;

insert into procedure (id,date,type,patient_id) values(1,'04.09.2020 15:24','obican pregled',2);
insert into procedure (id,date,type,patient_id) values(2,'03.07.2018 15:24','kontrola',2);


insert into examination(id,doctor_id) values(2,8);
insert into examination(id,doctor_id) values(1,12);
--insert into examination(id,doctor_id) values(2,13);

--Adding ratings
insert into rate(id,value) values(1,5);
insert into rate(id,value) values(2,6);
insert into rate(id,value) values(3,7);
insert into rate(id,value) values(4,8);

insert into clinic_ratings(clinic_id,rate_id) values(1,1);
insert into clinic_ratings(clinic_id,rate_id) values(1,2);
insert into clinic_ratings(clinic_id,rate_id) values(1,3);
insert into clinic_ratings(clinic_id,rate_id) values(2,4);

insert into doctor_ratings(doctor_id,rate_id) values(8,1);
insert into doctor_ratings(doctor_id,rate_id) values(8,4);
insert into doctor_ratings(doctor_id,rate_id) values(8,2);
insert into doctor_ratings(doctor_id,rate_id) values(12,2);
insert into doctor_ratings(doctor_id,rate_id) values(13,3);