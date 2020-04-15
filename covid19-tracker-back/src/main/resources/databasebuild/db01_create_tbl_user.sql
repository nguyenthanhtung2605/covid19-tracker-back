USE heroku_35a414b7900b3fd;
DROP TABLE IF EXISTS auth_user;
CREATE TABLE auth_user (
  auth_user_id  bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  user_email varchar(255) NOT NULL,
  user_nickname varchar(255) NOT NULL,
  user_password varchar(255) NOT NULL,
  is_verified boolean NOT NULL,
  is_enabled boolean NOT NULL,
  PRIMARY KEY (auth_user_id)
);