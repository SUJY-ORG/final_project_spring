USE ssafyhome;
DROP TABLE IF EXISTS registered_sales;
DROP TABLE IF EXISTS ai_judge_sales;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    service_id VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    preference VARCHAR(255)
);

CREATE TABLE registered_sales(
	registered_sales_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    apt_name varchar(255) NOT NULL,
    price varchar(255) NOT NULL,
    lat DOUBLE NOT NULL,
    lng DOUBLE NOT NULL,
    address varchar(255),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE ai_judge_sales (
	AI_judge_sales_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    sales_id BIGINT,
    judge_cnt BIGINT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO users (service_id, password, email)
VALUES ('admin', '1234', 'admin@ssafy.com');

INSERT INTO registered_sales (user_id, apt_name, price, lat, lng, address)
SELECT
	1 as user_id,
    hi.apt_nm as apt_name,
    hd.deal_amount as price,
    hi.latitude as lat,
    hi.longitude as lng,
    CONCAT(d.sido_name, ' ', d.gugun_name, ' ', d.dong_name) as address
FROM
    dongcodes d, houseinfos hi, housedeals hd
WHERE
    d.dong_code = CONCAT(hi.sgg_cd, hi.umd_cd)
    AND hi.apt_seq = hd.apt_seq
LIMIT 100;
