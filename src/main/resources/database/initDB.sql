CREATE TABLE IF NOT EXISTS clients
(
    id    SERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    email VARCHAR(254) NOT NULL ,
    phone VARCHAR(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS ads (
                         id SERIAL PRIMARY KEY,
                         client_id INT NOT NULL,
                         description TEXT NOT NULL,
                         rooms_count INT NOT NULL,
                         house_number INT NOT NULL,
                         floor INT NOT NULL,
                         floors_count INT NOT NULL,
                         creation_year INT NOT NULL,
                         price INT NOT NULL,
                         FOREIGN KEY(client_id) REFERENCES clients(id)
);
