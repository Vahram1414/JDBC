CREATE TABLE label
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT PK_WRITER PRIMARY KEY (id)
);
CREATE TABLE writer
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE posts
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    content VARCHAR(50) NOT NULL,
    created VARCHAR(50) NOT NULL,
    updated VARCHAR(50) NOT NULL,
    writer_id BIGINT NOT NULL
    ALTER TABLE posts ADD FOREIGN KEY (writer_id) REFERENCES writer(id)
);
CREATE TABLE post_labels (

post_id BIGINT NOT NULL,
label_id BIGINT NOT NULL,
UNIQUE (post_id, label_id)
);