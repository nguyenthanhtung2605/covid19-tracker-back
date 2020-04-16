USE heroku_35a414b7900b3fd;
DROP TABLE IF EXISTS auth_user_role;
CREATE TABLE auth_user_role (
  auth_user_id bigint(19) unsigned NOT NULL,
  auth_role_id smallint(5) unsigned NOT NULL,
  PRIMARY KEY (auth_user_id, auth_role_id),
  KEY FK_auth_user_role (auth_role_id),
  KEY FK_auth_user (auth_user_id),
  CONSTRAINT FK_auth_user FOREIGN KEY (auth_user_id) REFERENCES auth_user (auth_user_id),
  CONSTRAINT FK_auth_user_role FOREIGN KEY (auth_role_id) REFERENCES auth_role (auth_role_id)
);