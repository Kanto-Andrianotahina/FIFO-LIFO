-------------------- store -----------------------------
insert into store values ('STR0001','Magasin A');
insert into store values ('STR0002','Magasin B');


-------------------- unity -----------------------------
insert into unity values ('UTY0001','Kg');
insert into unity values ('UTY0002','g');


-------------------- category -----------------------------
insert into category values ('CAT0001','Riz');


-------------------- article -----------------------------
insert into article values ('ART0001','Riz Blanc', 'CAT0001','UTY0001');


-------------------- article price -----------------------------
insert into articleprice values (default,'ART0001','STR0001',6000,'2022-01-01');


-------------------- type movement -----------------------------
insert into typemovement values ('TMT0001','IN');
insert into typemovement values ('TMT0002','OUT');


-------------------- conversion -----------------------------
insert into  conversion values ('CVN0001','Kg___g','UTY0001','UTY0002',1000);
insert into  conversion values ('CVN0002','G___Kg','UTY0002','UTY0001',0.001);

--  --------- Mehtod type-----------------
INSERT INTO methodtype VALUES('MTP0001','FIFO');
INSERT INTO methodtype VALUES('MTP0002','LIFO');

--  --------- Article mehodd -----------------
INSERT INTO methodarticle VALUES (default,'ART0001','STR0001','MTP0001', '2023-02-02');
INSERT INTO methodarticle VALUES (default,'FAR0001','STR0001','MTP0002', '2023-02-02');


