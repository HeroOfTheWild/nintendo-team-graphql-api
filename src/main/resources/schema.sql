CREATE SCHEMA IF NOT EXISTS NINTENDO;

-- Name Details
CREATE TABLE NINTENDO.NAME (
    NINTENDO_ID varchar(7) PRIMARY KEY NOT NULL,
    FIRST_NM varchar(30),
    MIDDLE_NM varchar(30),
    LAST_NM varchar(30) NOT NULL,
    MODIFIED timestamp NOT NULL
);

CREATE UNIQUE INDEX XPK_NAME ON NINTENDO.NAME(NINTENDO_ID);

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM, MODIFIED)
VALUES ('nin0001', 'Mario', 'Jumpman', 'Nintendo', '1985-07-05 23:54:00');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM, MODIFIED)
VALUES ('nin0002', 'Luigi', 'Hypeman', 'Nintendo', '1985-07-05 23:54:00');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM, MODIFIED)
VALUES ('nin0003', 'Peach', null, 'Toadstool', '1985-07-05 23:54:00');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM, MODIFIED)
VALUES ('nin0004', 'Olivia', 'Morgan', 'Giles', '2019-05-05 23:54:00');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM, MODIFIED)
VALUES ('nin0005', 'Michael', 'Steven', 'Barefield', '2014-05-05 23:54:00');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM, MODIFIED)
VALUES ('nin0006', 'Kevin', 'Gil', 'Hernandez', '2017-09-10 23:54:00');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM, MODIFIED)
VALUES ('nin9999', 'Shigeru', null, 'Miyamoto', '1985-07-05 23:54:00');

-- Teammates Details
CREATE TABLE NINTENDO.TEAM (
    TEAM_ID varchar(10) PRIMARY KEY NOT NULL,
    TEAM_NM varchar(30) NOT NULL,
    MANAGER_ID varchar(7) NOT NULL DEFAULT 'nin9999'
);

CREATE UNIQUE INDEX XPX_TEAM ON NINTENDO.TEAM(TEAM_ID);
CREATE INDEX TEAM_IDX1 ON NINTENDO.TEAM(MANAGER_ID);
CREATE INDEX TEAM_IDX2 ON NINTENDO.TEAM(TEAM_NM);

INSERT INTO NINTENDO.TEAM
(TEAM_ID, TEAM_NM, MANAGER_ID)
VALUES ('nintendo01','Super Mario','nin9999');

INSERT INTO NINTENDO.TEAM
(TEAM_ID, TEAM_NM, MANAGER_ID)
VALUES ('nintendo02','Wonkinator','nin9999');

-- Teammates Details
CREATE TABLE NINTENDO.TEAMMATE (
    NINTENDO_ID varchar(7) NOT NULL,
    TEAM_ID varchar(10) NOT NULL,
    PRIMARY_TEAM varchar(1) NOT NULL DEFAULT 'Y',
    CONSTRAINT XPX_TEAM PRIMARY KEY (NINTENDO_ID, TEAM_ID)
);

CREATE UNIQUE INDEX XPX_TEAMMATE ON NINTENDO.TEAMMATE(NINTENDO_ID, TEAM_ID);
CREATE INDEX TEAMMATE_IDX1 ON NINTENDO.TEAMMATE(NINTENDO_ID);
CREATE INDEX TEAMMATE_IDX2 ON NINTENDO.TEAMMATE(TEAM_ID);

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0001','nintendo01');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0002','nintendo01');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0003','nintendo01');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0004','nintendo02');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0005','nintendo02');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0006','nintendo02');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin9999','nintendo01');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID, PRIMARY_TEAM)
VALUES ('nin9999','nintendo02','N');