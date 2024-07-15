CREATE TABLE topics
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(100) NOT NULL,
    description   VARCHAR(100) NOT NULL,
    status        VARCHAR(10)  NOT NULL,
    creation_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    author        VARCHAR(100) NOT NULL,
    course        VARCHAR(200) NOT NULL
);