create table store(
    id      varchar(7) primary key,
    store   varchar (30)
);

create table category(
    id          varchar(7) primary key ,
    category    varchar(30)
);

create  table unity(
    id      varchar(7) primary key ,
    unity   varchar (5)
);

create table article(
    id          varchar(7) primary key,
    article     varchar (30),
    category    varchar(7) references category ,
    unity       varchar(7) references unity
);


create table methodtype(
    id     varchar(7) not null primary key,
    method varchar(5)
);

create table methodarticle
(
    id      serial,
    article varchar(7) references article,
    store   varchar(7) references store,
    method  varchar(7) references methodtype,
    date    date
);


create table articleprice(
    id          serial,
    article     varchar(7) references article,
    store       varchar(7) references  store,
    price       double precision,
    date        date
);

create table typemovement(
    id      varchar(7) primary key,
    type    varchar(5)
);

create table movement(
    id          varchar(7) primary key,
    type        varchar(7) references typemovement,
    article     varchar(7) references  article,
    quantity    double precision,
    unity       varchar(7) references unity,
    store       varchar(7) references store,
    date        timestamp
);

create table stockarticle(
    id          serial,
    article     varchar(7) references article,
    store       varchar(7) references store,
    quantity    double precision,
    date        timestamp,
    unity       varchar(7) references  unity
);


create table  conversion(
    id              varchar(7) primary key ,
    conversion      varchar(30),
    unity1          varchar(7) references unity,
    unity2          varchar(7) references unity,
    value           double precision
);


create table validation(
    id          varchar(7) primary key,
    movement    varchar(7) references movement,
    date        timestamp
);


create table quantityleftart
(
    id           serial,
    movement     varchar(7) references movement,
    quantityleft double precision
);

create table amountmvt
(
    id   serial,
    movement varchar(7) references movement,
    quantity double precision,
    amount   double precision
);


-- ------------------------------------------ Sequence  -------------------------------------------------------
create sequence seqmovement
    START 1
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9999
    CACHE 1;

CREATE OR REPLACE FUNCTION getseqmovement()
    RETURNS integer AS $$
    DECLARE
        seq integer;
    BEGIN
        SELECT nextval('seqmovement') INTO seq;
        RETURN seq;
    END;
$$ LANGUAGE plpgsql;


create sequence seqvalidation
    START 1
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9999
    CACHE 1;

CREATE OR REPLACE FUNCTION getseqvalidation()
    RETURNS integer AS $$
    DECLARE
        seq integer;
    BEGIN
        SELECT nextval('seqvalidation') INTO seq;
        RETURN seq;
    END;
$$ LANGUAGE plpgsql;



-- ------------------------------------------ view  -------------------------------------------------------
CREATE VIEW view_movement_not_valid AS    /* pour avoir les listes des movements a valider*/
SELECT
    m.*
FROM
    movement m
LEFT JOIN
    validation v ON m.id = v.movement
WHERE
    v.id IS NULL;


/* pour faire une checking de validation */
CREATE VIEW view_validation_movement AS
SELECT
    v.id AS idValidation,
    v.movement AS idMovement,
    m.type,
    m.article,
    m.quantity,
    m.unity,
    m.store,
    m.date AS dateMovement,
    v.date AS dateValidation
FROM
    validation v
JOIN
    movement m ON v.movement = m.id;
drop view view_validation_movement;


/* pour faire les movements IN pour faire OUT */
CREATE VIEW view_movement_valid AS
SELECT
    vvm.idValidation,
    vvm.idMovement,
    vvm.type,
    vvm.article,
    vvm.unity,
    vvm.store,
    vvm.dateMovement,
    vvm.dateValidation,
    qla.quantityleft AS quantityLeft
FROM
    view_validation_movement vvm
JOIN
    quantityleftart qla ON vvm.idMovement = qla.movement
JOIN
    (SELECT
        MAX(quantityleftart.id) AS max_id,
        quantityleftart.movement
    FROM
        quantityleftart
    GROUP BY
        quantityleftart.movement) max_ids ON vvm.idMovement = max_ids.movement
JOIN
    quantityleftart ql ON max_ids.max_id = ql.id
WHERE
    vvm.type = 'TMT0001';

drop view view_movement_valid;