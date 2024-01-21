DROP TABLE IF EXISTS tb_user;
DROP TABLE IF EXISTS tb_user;

CREATE TABLE tb_user
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'Primary key column' PRIMARY KEY,
    login_id           VARCHAR(150)         NOT NULL comment 'Email for login',
    encrypted_password VARCHAR(200)         NOT NULL,
    is_deleted         TINYINT(1) DEFAULT 0 NOT NULL,
    deleted_at         DATETIME             NULL
) COMMENT '일반 사용자 계정 정보 테이블';

CREATE TABLE tb_token
(
    id      BIGINT UNSIGNED AUTO_INCREMENT,
    token   VARCHAR(255) UNIQUE,
    revoked BOOLEAN DEFAULT FALSE,
    expired BOOLEAN DEFAULT FALSE,
    user_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES tb_user (id)
) COMMENT '일반 사용자 전용 토큰 테이블';

