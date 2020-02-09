insert into authority (name) values ('ROLE_PATIENT');
insert into authority (name) values ('ROLE_DOCTOR');
insert into authority (name) values ('ROLE_NURSE');
insert into authority (name) values ('ROLE_CADMIN');
insert into authority (name) values ('ROLE_CCADMIN');
insert into authority (name) values ('ROLE_USER');



-- users
insert into medicalfile values(1);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'adresa1','Liverpool','test1',true,'John','Doe','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','England','064521520','test',1);
insert into patient values (1, 1);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','cadmin',true,'test','test','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','cadmin',14);
insert into clinic_admin (id,clinic) values (14, 1);


insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','doctor2',true,'Zivko','Zivkovic','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','doctor2',12);
insert into medical_staff (id,on_vacation,clinic_id,holiday_begin,holiday_end,shift) values (12,false,1,null,null,1);
insert into doctor (id,specialization) values (12,'Stomatologist');

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','doctor3',true,'Nikola','Nikolic','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','doctor3',13);
insert into medical_staff (id,on_vacation,clinic_id,holiday_begin,holiday_end,shift) values (13,false,1,null,null,2);
insert into doctor (id,specialization) values (13,'Plastic surgeon');

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','doctor5',true,'Jovan','Jovic','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','doctor312',16);
insert into medical_staff (id,on_vacation,clinic_id,holiday_begin,holiday_end,shift) values (14,false,1,null,null,2);
insert into doctor (id,specialization) values (13,'Plastic surgeon');

-- authority
insert into user_authority (user_id, authority_id) values (1,1);
insert into user_authority (user_id, authority_id) values (12,2);
insert into user_authority (user_id, authority_id) values (14,4);

-- user authority
insert into user_authority (user_id, authority_id) values (14,6);



-- clinic
insert into clinic(id,name,address,description) values (1,'klinika1','dr.andre','lepa bas onako');
insert into clinic(id,name,address,description) values (2,'klinika2','Novi Sad,Danila Kisa,40','lepa samo');

--clinic staff
insert into clinic_admins values(1,14);
insert into clinic_doctor values(1,12);
insert into clinic_doctor values(1,13);


--rooms
insert into room (id,name,number,clinic_id,roomtype) values (1, 'room1','1',1,'Operation');

--clinic rooms
insert into clinic_rooms values(1,1);

--procedure
insert into procedure (id,date,type,patient_id, room) values(1,'04.09.2020','Examination', 1, 1);
--examination types
insert into examinationtype (id,name,specialization,price,clinic_id) values (1,'Control','Stomatologist',5,1);
--examination
insert into examination(id,doctor_id, examinationtype_id,rated_clinic,rated_doctor) values(1,12,1,true,false);

--next procedure
insert into next_procedure (id,type,clinic,date,doctor_id,examinationtype_id,patient_id,arranged,pickedtime) values (1,'Examination',1,'05.03.2020',12,1,1,true,6);
insert into next_procedure (id,type,clinic,date,doctor_id,examinationtype_id,arranged,pickedtime,patient_id) values (2,'Examination',1,'05.03.2020',12,1,false,6,1);
insert into next_procedure (id,type,clinic,date,doctor_id,examinationtype_id,arranged,pickedtime,patient_id) values (3,'Examination',1,'05.03.2020',12,1,false,6,null);

insert into roomarrange(id,room,date,time,taken,next_examination,clinic) values (1,1,'05.03.2020',15,true,1,1);