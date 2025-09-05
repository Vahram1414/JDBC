-- liquibase formatted sql

-- changeset proselyte:label
CREATE TABLE label (
                       id   BIGINT AUTO_INCREMENT NOT NULL,
                       name VARCHAR(50) NOT NULL,
                       CONSTRAINT PK_LABEL PRIMARY KEY (id)
);
-- rollback DROP TABLE label;

-- changeset proselyte:writer
CREATE TABLE writer (
                        id BIGINT AUTO_INCREMENT NOT NULL,
                        firstName VARCHAR(50) NOT NULL,
                        lastName VARCHAR(50) NOT NULL,
                        PRIMARY KEY (id)
);
-- rollback DROP TABLE writer;

-- changeset proselyte:posts
CREATE TABLE posts (
                       id BIGINT AUTO_INCREMENT NOT NULL,
                       content VARCHAR(50) NOT NULL,
                       created VARCHAR(50) NOT NULL,
                       updated VARCHAR(50) NOT NULL,
                       writer_id BIGINT NOT NULL,
                       PRIMARY KEY (id),
                       CONSTRAINT fk_posts_writer FOREIGN KEY (writer_id) REFERENCES writer(id)
);
-- rollback DROP TABLE posts;


-- changeset proselyte:post_labels
CREATE TABLE post_labels (
                             post_id  BIGINT NOT NULL,
                             label_id BIGINT NOT NULL,
                             UNIQUE (post_id, label_id),
                             CONSTRAINT fk_postlabels_post FOREIGN KEY (post_id) REFERENCES posts(id),
                             CONSTRAINT fk_postlabels_label FOREIGN KEY (label_id) REFERENCES label(id)
);
-- rollback DROP TABLE post_labels;
