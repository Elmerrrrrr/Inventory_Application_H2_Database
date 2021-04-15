DROP TABLE IF EXISTS users;
CREATE TABLE user_details (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250),
    last_name VARCHAR(250),
    email VARCHAR(250),
    password VARCHAR(250) 
);

INSERT INTO user_details (email,first_name,last_name,password) VALUES
  ('admin@admin.com','admin','admin','admin'),
  ('john@gmail.com','john','doe','johndoe'),
  ('sham@yahoo.com','sham','tis','shamtis');