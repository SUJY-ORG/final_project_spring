USE ssafyhome;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    service_id VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    preference VARCHAR(255)
);

INSERT INTO users (service_id, password)
VALUES ('admin', '1234')