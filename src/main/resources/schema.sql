CREATE SCHEMA IF NOT EXISTS NINTENDO;

-- Name Details
CREATE TABLE NINTENDO.NAME (
    NINTENDO_ID varchar(7) PRIMARY KEY NOT NULL,
    FIRST_NM varchar(30),
    MIDDLE_NM varchar(30),
    LAST_NM varchar(30) NOT NULL
);

CREATE UNIQUE INDEX XPK_NAME ON NINTENDO.NAME(NINTENDO_ID);

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0001', 'Mario', 'Jumpman', 'Nintendo');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0002', 'Luigi', 'Hypeman', 'Nintendo');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0003', 'Peach', null, 'Toadstool');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0004', 'Olivia', 'Morgan', 'Giles');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0005', 'Michael', 'Steven', 'Barefield');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0006', 'Kevin', 'Gil', 'Hernandez');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0007', 'Jonathan', 'Lee', 'Womack');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0008', 'Scott', null, 'Green');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0009', 'Zenovy', null, 'Shatzov');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0010', 'Jonathan', null, 'Robertson');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0011', 'Asif', null, 'Fasih');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0012', 'Tyler', null, 'Saxton');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0013', 'Teja', null, 'Reddy');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin0014', 'Siri', null, 'Manne');

INSERT INTO NINTENDO.NAME 
(NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM)
VALUES ('nin9999', 'Shigeru', null, 'Miyamoto');

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
VALUES ('nintendo02','Wonkinator','nin0010');

INSERT INTO NINTENDO.TEAM
(TEAM_ID, TEAM_NM, MANAGER_ID)
VALUES ('nintendo03','Super Research Experts','nin0011');

INSERT INTO NINTENDO.TEAM
(TEAM_ID, TEAM_NM, MANAGER_ID)
VALUES ('nintendo04','Sub Atomic Triforce','nin0011');

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
VALUES ('nin0007','nintendo03');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0008','nintendo03');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0009','nintendo03');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0010','nintendo02');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0011','nintendo03');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0012','nintendo04');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0013','nintendo04');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin0014','nintendo04');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID)
VALUES ('nin9999','nintendo01');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID, PRIMARY_TEAM)
VALUES ('nin9999','nintendo02','N');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID, PRIMARY_TEAM)
VALUES ('nin9999','nintendo03','N');

INSERT INTO NINTENDO.TEAMMATE
(NINTENDO_ID, TEAM_ID, PRIMARY_TEAM)
VALUES ('nin0011','nintendo04','N');