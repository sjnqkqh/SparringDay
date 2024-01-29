DROP TABLE IF EXISTS token;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    login_id           VARCHAR(150)                        NOT NULL,
    encrypted_password VARCHAR(200)                        NOT NULL,
    user_type          ENUM ('TRAINER', 'COACH', 'MASTER') NOT NULL,
    is_deleted         TINYINT(1) DEFAULT 0                NOT NULL,
    deleted_at         DATETIME                            NULL
) COMMENT '일반 사용자 계정 정보 테이블';

CREATE TABLE token
(
    id      BIGINT UNSIGNED AUTO_INCREMENT,
    token   VARCHAR(255) UNIQUE,
    revoked BOOLEAN DEFAULT FALSE,
    expired BOOLEAN DEFAULT FALSE,
    user_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
) COMMENT '일반 사용자 전용 토큰 테이블';

