--DROP TABLE IF EXISTS inventory;
--
--CREATE TABLE inventory (
--  id INT AUTO_INCREMENT  PRIMARY KEY,
--  brand VARCHAR(250) NOT NULL,
--  model VARCHAR(250) NOT NULL,
--  price INTEGER(250) DEFAULT NULL
--);
--
INSERT INTO inventory (brand, model, price) VALUES
  ('Acer', 'DX-800', '1500'),
  ('Dell', 'XS-500', '1000'),
  ('Apple', 'McBook', '4000'),
  ('Lenovo', '8800KZ', '2300');


INSERT INTO user_details(email,first_Name,last_Name,password) VALUES
  ('admin@admin.com','admin','admin','admin'),
  ('john@gmail.com','john','doe','johndoe'),
  ('sham@yahoo.com','sham','tis','shamtis');
