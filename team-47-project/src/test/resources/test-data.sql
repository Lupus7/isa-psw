insert into authority (name) values ('ROLE_PATIENT');
insert into authority (name) values ('ROLE_DOCTOR');
insert into medicalfile values(1);
insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'adresa1','Liverpool','test1',true,'John','Doe','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','England','064521520','test',1);
insert into patient values (1, 1);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','doctor2',true,'Zivko','Zivkovic','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','doctor2',12);
insert into clinic(id,name,address,description) values (1,'klinika1','dr.andre','lepa bas onako');
insert into medical_staff (id,on_vacation,clinic_id,holiday_begin,holiday_end) values (12,false,1,null,null);
insert into doctor (id,specialization,shift) values (12,'Stomatologist',1);
insert into clinic_doctor values(1,12);

insert into users (is_accepted, address, city, email, enabled, first_name, last_name, last_password_reset_date, password, state, telephone, unique_num, id)
            values(true,'test','test','doctor3',true,'Nikola','Nikolic','2019-11-18 20:56:47.814768','$2a$10$2ArNPfLsmoNZ3x9QeUKIQuawAL/7MJQjtq/8OJUZL8s45iXWDtcJG','test','test','doctor3',13);
insert into medical_staff (id,on_vacation,clinic_id,holiday_begin,holiday_end) values (13,false,2,null,null);
insert into doctor (id,specialization,shift) values (13,'Plastic surgeon',2);

insert into user_authority (user_id, authority_id) values (1,1);
insert into user_authority (user_id, authority_id) values (12,2);
insert into room (id,name,number,clinic_id,roomtype) values (1, 'room1','1',1,'Operation');
insert into clinic_rooms values(1,1);
insert into procedure (id,date,type,patient_id, room) values(1,'04.09.2020','Examination', 1, 1);
insert into examinationtype (id,name,specialization,price,clinic_id) values (1,'Control','Stomatologist',5,1);
insert into examination(id,doctor_id, examinationtype_id,rated_clinic,rated_doctor) values(1,12,1,true,false);

insert into next_procedure (id,type,clinic,date,doctor_id,examinationtype_id,patient_id,arranged,pickedtime) values (1,'Examination',1,'05.03.2020',12,1,1,true,6);
insert into next_procedure (id,type,clinic,date,doctor_id,examinationtype_id,arranged,pickedtime) values (2,'Examination',1,'05.03.2020',12,1,false,6);

insert into roomarrange(id,room,date,time,taken,next_examination,clinic) values (1,1,'05.03.2020',15,true,1,1);