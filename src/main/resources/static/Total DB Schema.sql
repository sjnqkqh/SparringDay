DROP TABLE IF EXISTS token;
DROP TABLE IF EXISTS user_boxing_info;
DROP TABLE IF EXISTS sparring_review;
DROP TABLE IF EXISTS sparring_match;
DROP TABLE IF EXISTS sparring_request;
DROP TABLE IF EXISTS user_boxing_info;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    login_id           VARCHAR(150)                         NOT NULL,
    encrypted_password VARCHAR(200)                         NOT NULL,
    height             INT,
    weight             INT,
    is_deleted         TINYINT(1) DEFAULT 0                 NOT NULL,
    deleted_at         DATETIME                             NULL,
    created_at         DATETIME   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at         DATETIME   DEFAULT CURRENT_TIMESTAMP NOT NULL
) COMMENT '일반 사용자 계정 정보 테이블';

CREATE TABLE user_boxing_info
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id            BIGINT UNSIGNED                                             NOT NULL,
    year_of_experience INT UNSIGNED DEFAULT 0                                      NOT NULL,
    numberOfSparring   INT          DEFAULT 0                                      NOT NULL,
    style              ENUM ('OUT_BOXER', 'IN_FIGHTER', 'SLUGGER', 'IN_AND_OUT')   NOT NULL,
    front_hand         ENUM ('SOUTHPAW', 'ORTHODOX')                               NOT NULL,
    sparring_purpose   ENUM ('HOBBY', 'PREP_LOCAL_COMPETITIONS', 'PREP_PRO_MATCH') NOT NULL,
    sparring_intensity ENUM ('FULL', 'MEDIUM', 'LOW', 'METHOD')                    NOT NULL,
    is_deleted         TINYINT(1)   DEFAULT 0                                      NOT NULL,
    deleted_at         DATETIME                                                    NULL,
    created_at         DATETIME     DEFAULT CURRENT_TIMESTAMP                      NOT NULL,
    updated_at         DATETIME     DEFAULT CURRENT_TIMESTAMP                      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
) COMMENT '유저 추가 정보(복싱)';

CREATE TABLE sparring_request
(
    id                BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    requester_id      BIGINT UNSIGNED                      NOT NULL,
    target_user_id    BIGINT UNSIGNED                      NOT NULL,
    location          VARCHAR(200)                         NOT NULL,
    sparring_datetime DATETIME                             NOT NULL,
    is_accepted       TINYINT(1) DEFAULT 0                 NOT NULL,
    accepted_at       DATETIME,
    is_deleted        TINYINT(1) DEFAULT 0                 NOT NULL,
    deleted_at        DATETIME                             NULL,
    created_at        DATETIME   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at        DATETIME   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (requester_id) REFERENCES user (id),
    FOREIGN KEY (target_user_id) REFERENCES user (id)
) COMMENT '스파링 신청 정보';

CREATE TABLE sparring_match
(
    id                BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    requester_id      BIGINT UNSIGNED                      NOT NULL,
    target_user_id    BIGINT UNSIGNED                      NOT NULL,
    location          VARCHAR(200)                         NOT NULL,
    sparring_datetime DATETIME                             NOT NULL,
    is_finished       TINYINT(1) DEFAULT 0                 NOT NULL,
    is_deleted        TINYINT(1) DEFAULT 0                 NOT NULL,
    deleted_at        DATETIME                             NULL,
    created_at        DATETIME   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at        DATETIME   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (requester_id) REFERENCES user (id),
    FOREIGN KEY (target_user_id) REFERENCES user (id)
) COMMENT '스파링 매치 정보';

CREATE TABLE sparring_review
(
    id               BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    match_id         BIGINT UNSIGNED                      NOT NULL COMMENT '스파링 매칭 ID',
    reviewer_id      BIGINT UNSIGNED                      NOT NULL COMMENT '리뷰 작성자 ID',
    review_target_id BIGINT UNSIGNED                      NOT NULL COMMENT '리뷰 대상 ID',
    sparring_manner  ENUM ('HIGH', 'MEDIUM', 'LOW')       NOT NULL,
    time_manner      ENUM ('HIGH', 'MEDIUM', 'LOW')       NOT NULL,
    skill_level      ENUM ('HIGH', 'MEDIUM', 'LOW')       NOT NULL,
    review_text      TEXT,
    is_deleted       TINYINT(1) DEFAULT 0                 NOT NULL,
    deleted_at       DATETIME                             NULL,
    created_at       DATETIME   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at       DATETIME   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (match_id) REFERENCES sparring_match (id),
    FOREIGN KEY (reviewer_id) REFERENCES user (id),
    FOREIGN KEY (review_target_id) REFERENCES user (id)
) COMMENT '스파링 후기';


CREATE TABLE token
(
    id      BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    token   VARCHAR(255) UNIQUE,
    revoked BOOLEAN DEFAULT FALSE,
    expired BOOLEAN DEFAULT FALSE,
    user_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
) COMMENT '일반 사용자 전용 토큰 테이블';

