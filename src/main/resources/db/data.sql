INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
 VALUES ('spring-security-oauth2-read-client', 'resource-server-rest-api',
 /*spring-security-oauth2-read-client-password1234*/'$2a$04$WGq2P9egiOYoOFemBRfsiO9qTcyJtNRnPKNBl5tokP7IP.eZn93km',
 'read', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
 VALUES ('spring-security-oauth2-read-write-client', 'resource-server-rest-api',
 /*spring-security-oauth2-read-write-client-password1234*/'$2a$04$soeOR.QFmClXeFIrhJVLWOQxfHjsJLSpWrU1iGxcMGdu.a5hvfY4W',
 'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

INSERT INTO AUTHORITY(ID, authority, enabled) VALUES (1, 'CREATE', TRUE);
INSERT INTO USERS(ID, USERNAME, PASSWD, NON_EXPIRED, NON_LOCKED, password_non_EXPIRED, ENABLED)
  VALUES (1,'admin', /*admin1234*/'$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha', TRUE, TRUE, TRUE, TRUE);
INSERT INTO USERS_AUTHORITY(USERS_ID, AUTHORITY_ID) VALUES (1, 1);


insert into ref_info (id, discriminator, name) values (1, 1, "Public");
insert into ref_info (id, discriminator, name) values (2, 1, "Confidential");
insert into ref_info (id, discriminator, name) values (3, 2, "Read");
insert into ref_info (id, discriminator, name) values (4, 2, "Write");
insert into ref_info (id, discriminator, name) values (5, 3, "Password");
insert into ref_info (id, discriminator, name) values (6, 3, "Authorization Code");
insert into ref_info (id, discriminator, name) values (7, 3, "Refresh Token");
insert into ref_info (id, discriminator, name) values (8, 3, "Implicit");

commit;