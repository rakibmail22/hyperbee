INSERT INTO hyperTest.role (id, type) VALUES (1, 'ADMIN');
INSERT INTO hyperTest.role (id, type) VALUES (2, 'USER');

INSERT INTO hyperTest.user (id, display_status, email, first_name, last_name, password, username)
VALUES (1, 'ACTIVE', 'admin@gmail.com', 'Admin', 'Shaheb', '21232f297a57a5a743894ae4a801fc3', 'admin');

INSERT INTO hyperTest.user_role (user_id, role_id) VALUES (1, 1);

INSERT INTO hyperTest.hive(id, creator_id, description, image_path, name)
VALUES (1, 1, 'General Hive For All user', 'AllHivecover.png', 'All Hive');

INSERT INTO hyperTest.user_hive (hive_id,user_id) VALUES (1, 1);
