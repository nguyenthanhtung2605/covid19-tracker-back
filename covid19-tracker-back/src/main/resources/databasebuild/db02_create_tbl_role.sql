USE heroku_35a414b7900b3fd;
DROP TABLE IF EXISTS auth_role;
CREATE TABLE auth_role (
  auth_role_id smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  role_name varchar(255) DEFAULT NULL,
  role_desc varchar(255) DEFAULT NULL,
  PRIMARY KEY (auth_role_id)
);

INSERT INTO auth_role VALUES (1,'SUPER_USER','This user has ultimate rights for everything');
INSERT INTO auth_role VALUES (2,'ADMIN_USER','This user has admin rights for administrative work');
INSERT INTO auth_role VALUES (3,'SITE_USER','This user has access to site, after login - normal user');