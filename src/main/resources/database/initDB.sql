CREATE TABLE IF NOT EXISTS users
(
    id    BIGSERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    email VARCHAR(254) NOT NULL ,
    phone VARCHAR(50)  NOT NULL
);
select * from users;
select * from ads;

alter table users add column is_active boolean;
drop table users;
drop table ads;



CREATE TABLE IF NOT EXISTS ads (
                         id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         description TEXT NOT NULL,
                         rooms_count INT NOT NULL,
                         house_number INT NOT NULL,
                         floor INT NOT NULL,
                         floors_count INT NOT NULL,
                         creation_year INT NOT NULL,
                         price INT NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users(id)
);
