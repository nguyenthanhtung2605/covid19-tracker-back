USE heroku_35a414b7900b3fd;
-- unhashed password of $2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i is abc123ABC
-- #Password: abc123ABC
-- #Hashed: $2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i

insert into auth_user (auth_user_id,first_name,last_name,user_email,user_nickname, user_password, is_verified, is_enabled) values (1,'Tung','Nguyen','admin@gmail.com','tungnguyen','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i', TRUE, TRUE);
insert into auth_user (auth_user_id,first_name,last_name,user_email,user_nickname, user_password, is_verified, is_enabled) values (2,'Vy','Nguyen','vynguyen@gmail.com','vynguyen','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i', TRUE, TRUE);
insert into auth_user (auth_user_id,first_name,last_name,user_email,user_nickname, user_password, is_verified, is_enabled) values (3,'Benson','Chan','bensonchan@hotmail.com','bensonchan','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i', TRUE, TRUE);
insert into auth_user (auth_user_id,first_name,last_name,user_email,user_nickname, user_password, is_verified, is_enabled) values (4,'Nam','Bui','nambui@yahoo.com','nambui','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i', TRUE, TRUE);
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','1');
insert into auth_user_role (auth_user_id, auth_role_id) values ('2','2');
insert into auth_user_role (auth_user_id, auth_role_id) values ('3','3');
insert into auth_user_role (auth_user_id, auth_role_id) values ('4','1');
insert into auth_user_role (auth_user_id, auth_role_id) values ('4','2');
insert into auth_user_role (auth_user_id, auth_role_id) values ('4','3');