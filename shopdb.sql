CREATE TABLE shopitem(
	itemid INTEGER NOT NULL UNIQUE,
	name TEXT NOT NULL,
	description TEXT NOT NULL,
	price REAL NOT NULL,
	imglink TEXT NOT NULL,
	PRIMARY KEY (itemid)
);

CREATE TABLE cartitem(
	cartid INTEGER NOT NULL, 
	itemid INTEGER NOT NULL,
	itemquantity INTEGER NOT NULL,
	PRIMARY KEY (cartid, itemid),
	FOREIGN KEY (itemid) REFERENCES shopitems (itemid)
);

CREATE TABLE cart(
	userid INTEGER NOT NULL, 
	cartid INTEGER NOT NULL UNIQUE,
	PRIMARY KEY (cartid),
	FOREIGN KEY (cartid) REFERENCES cartitem (cartid)
);

CREATE TABLE users(
	email TEXT NOT NULL,
	passwd VARCHAR(30) NOT NULL,
	phone INTEGER NOT NULL,
	currentcart INTEGER,
	usertoken integer not null,
	PRIMARY KEY (email)
	FOREIGN KEY (currentcart) REFERENCES cart (cartid)
);

INSERT INTO shopitem VALUES (1, "Кольцо рысь", "данный товар подходит любителям необычных украшений", 1000, "7e4c2db50aa6074482cb719028f917d4.jpg");
INSERT INTO shopitem VALUES (2, "Оранжевый слон", "Оранжевый слон вам на стол", 2000, "pla-3d-printed-elephant.jpg");
INSERT INTO shopitem VALUES (3, "Кабан", "Хорошо смотрится как украшение на стене", 3000, "d78174243c745c4f09e79568143bae33.jpg");
INSERT INTO shopitem VALUES (4, "Котенек", "Ета кошка лиюет вескас", 3999.99, "kitten-cats-animals-3d-model-max-obj-3ds-fbx-stl.jpg");