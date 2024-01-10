CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(20) PRIMARY KEY,
    password VARCHAR(200),
    email VARCHAR(100),
    locked tinyint,
    disabled tinyint
);

INSERT INTO users(username,password,email,locked,disabled) VALUES ('admin','$2y$10$CzAVGCOsfzcIngs/09xvZOtoprJqqpUhrI6143xoz.cF32MNVHIwa',
'ricardo2roa@gmail.com',0,0);
INSERT INTO users(username,password,email,locked,disabled) VALUES ('customer','$2y$10$CzAVGCOsfzcIngs/09xvZOtoprJqqpUhrI6143xoz.cF32MNVHIwa',
'ricardo2roa@gmail.com',0,0);

CREATE TABLE IF NOT EXISTS user_role(
    username VARCHAR(20),
    role  VARCHAR(20),
    date_created DATETIME,
    PRIMARY KEY (username, role)
);

INSERT INTO user_role(username,role,date_created) VALUES ('admin','ADMIN',NOW());
INSERT INTO user_role(username,role,date_created) VALUES ('customer','CUSTOMER',NOW());
INSERT INTO user_role(username,role,date_created) VALUES ('customer','CHIEF',NOW());
