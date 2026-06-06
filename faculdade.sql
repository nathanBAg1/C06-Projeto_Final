DROP DATABASE IF EXISTS faculdade;
CREATE DATABASE faculdade;
USE faculdade;

-- CRIAR NO MINIMO 5 TABELAS E RELACIONAMENTOS COESES ENTRE ELAS --
CREATE TABLE supervisor(
    cpf BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(30)
);

CREATE TABLE sala(
    numero INT PRIMARY KEY,
    vagas INT,
    cpf_supervisor BIGINT,

    CONSTRAINT fk_sala_supervisor
    FOREIGN KEY (cpf_supervisor)
    REFERENCES supervisor(cpf)
);

CREATE TABLE vestibulando(
    cpf BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(30),
    numero_sala INT,

    CONSTRAINT fk_vestibulando_sala
    FOREIGN KEY (numero_sala)
    REFERENCES sala(numero)
);

CREATE TABLE vestibular(
    id INT AUTO_INCREMENT PRIMARY KEY,
    localizacao VARCHAR(100) NOT NULL,
    data_realizacao DATE NOT NULL
);

CREATE TABLE materia(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_vestibular INT,
    materia VARCHAR(15) NOT NULL,

    CONSTRAINT fk_materia_vestibular
    FOREIGN KEY (id_vestibular)
    REFERENCES vestibular(id)
);

CREATE TABLE vestibulando_presta_vestibular(
    id_vestibular INT,
    cpf_vestibulando BIGINT,
    numero_inscricao INT,

    PRIMARY KEY(id_vestibular, cpf_vestibulando),

    CONSTRAINT fk_vpv_vestibular
    FOREIGN KEY (id_vestibular)
    REFERENCES vestibular(id),

    CONSTRAINT fk_vpv_vestibulando
    FOREIGN KEY (cpf_vestibulando)
    REFERENCES vestibulando(cpf)
);

CREATE TABLE curso(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE curso_oferece_vestibular(
    id_vestibular INT,
    id_curso INT,
    vagas INT NOT NULL,

    PRIMARY KEY(id_vestibular, id_curso),

    CONSTRAINT fk_cov_vestibular
    FOREIGN KEY (id_vestibular)
    REFERENCES vestibular(id),

    CONSTRAINT fk_cov_curso
    FOREIGN KEY (id_curso)
    REFERENCES curso(id)
);

CREATE TABLE corretor(
    cpf BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(30)
);

-- INSERIR NO MINIMO 5REGISTROS PARA CADA TABELA --
-- SUPERVISOR--
INSERT INTO supervisor VALUES
(11111111111,'Carlos Silva','carlos@gmail.com'),
(22222222222,'Ana Souza','ana@gmail.com'),
(33333333333,'Marcos Lima','marcos@gmail.com'),
(44444444444,'Fernanda Alves','fernanda@gmail.com'),
(55555555555,'Joao Rocha','joao@gmail.com');
-- SALA --
INSERT INTO sala VALUES
(101,20,11111111111),
(102,10,22222222222),
(103,10,33333333333),
(104,10,44444444444),
(105,10,55555555555);
-- VESTIBULANDO --
INSERT INTO vestibulando VALUES
(99911111111,'Vestibulando 01','vest01@gmail.com',101),
(99922222222,'Vestibulando 02','vest02@gmail.com',102),
(99933333333,'Vestibulando 03','vest03@gmail.com',103),
(99944444444,'Vestibulando 04','vest04@gmail.com',104),
(99955555555,'Vestibulando 05','vest05@gmail.com',105),
(99966666666,'Vestibulando 06','vest06@gmail.com',101),
(99977777777,'Vestibulando 07','vest07@gmail.com',101),
(99988888888,'Vestibulando 08','vest08@gmail.com',101),
(99999999999,'Vestibulando 09','vest09@gmail.com',101),
(99910101010,'Vestibulando 10','vest10@gmail.com',101),
(99911111112,'Vestibulando 11','vest11@gmail.com',101),
(99912121212,'Vestibulando 12','vest12@gmail.com',101),
(99913131313,'Vestibulando 13','vest13@gmail.com',101),
(99914141414,'Vestibulando 14','vest14@gmail.com',101),
(99915151515,'Vestibulando 15','vest15@gmail.com',102),
(99916161616,'Vestibulando 16','vest16@gmail.com',102),
(99917171717,'Vestibulando 17','vest17@gmail.com',102),
(99918181818,'Vestibulando 18','vest18@gmail.com',102),
(99919191919,'Vestibulando 19','vest19@gmail.com',102),
(99920202020,'Vestibulando 20','vest20@gmail.com',102),
(99921212121,'Vestibulando 21','vest21@gmail.com',102),
(99922222223,'Vestibulando 22','vest22@gmail.com',102),
(99923232323,'Vestibulando 23','vest23@gmail.com',102),
(99924242424,'Vestibulando 24','vest24@gmail.com',103),
(99925252525,'Vestibulando 25','vest25@gmail.com',103),
(99926262626,'Vestibulando 26','vest26@gmail.com',103),
(99927272727,'Vestibulando 27','vest27@gmail.com',103),
(99928282828,'Vestibulando 28','vest28@gmail.com',103),
(99929292929,'Vestibulando 29','vest29@gmail.com',103),
(99930303030,'Vestibulando 30','vest30@gmail.com',103),
(99931313131,'Vestibulando 31','vest31@gmail.com',103),
(99932323232,'Vestibulando 32','vest32@gmail.com',103),
(99933333334,'Vestibulando 33','vest33@gmail.com',104),
(99934343434,'Vestibulando 34','vest34@gmail.com',104),
(99935353535,'Vestibulando 35','vest35@gmail.com',104),
(99936363636,'Vestibulando 36','vest36@gmail.com',104),
(99937373737,'Vestibulando 37','vest37@gmail.com',104),
(99938383838,'Vestibulando 38','vest38@gmail.com',104),
(99939393939,'Vestibulando 39','vest39@gmail.com',104),
(99940404040,'Vestibulando 40','vest40@gmail.com',104),
(99941414141,'Vestibulando 41','vest41@gmail.com',104),
(99942424242,'Vestibulando 42','vest42@gmail.com',105),
(99943434343,'Vestibulando 43','vest43@gmail.com',105),
(99944444445,'Vestibulando 44','vest44@gmail.com',105),
(99945454545,'Vestibulando 45','vest45@gmail.com',105),
(99946464646,'Vestibulando 46','vest46@gmail.com',105),
(99947474747,'Vestibulando 47','vest47@gmail.com',105),
(99948484848,'Vestibulando 48','vest48@gmail.com',105),
(99949494949,'Vestibulando 49','vest49@gmail.com',105),
(99950505050,'Vestibulando 50','vest50@gmail.com',105);
-- VESTIBULAR --
INSERT INTO vestibular(localizacao,data_realizacao) VALUES
('Sao Paulo','2026-06-10'),
('Rio de Janeiro','2026-07-15'),
('Curitiba','2026-08-20'),
('Belo Horizonte','2026-09-12'),
('Porto Alegre','2026-10-05');
-- MATERIA --
INSERT INTO materia(id_vestibular,materia) VALUES
(1,'Matematica'),
(2,'Fisica'),
(3,'Quimica'),
(4,'Historia'),
(5,'Biologia');
-- VESTIBULANDO_PRESTA_VESTIBULAR --
INSERT INTO vestibulando_presta_vestibular VALUES
(1,99911111111,1001),
(1,99922222222,1002),
(1,99933333333,1003),
(1,99944444444,1004),
(1,99955555555,1005),
(1,99966666666,1006),
(1,99977777777,1007),
(1,99988888888,1008),
(1,99999999999,1009),
(1,99910101010,1010),
(1,99911111112,1011),
(1,99912121212,1012),
(1,99913131313,1013),
(1,99914141414,1014),
(1,99915151515,1015),
(1,99916161616,1016),
(1,99917171717,1017),
(1,99918181818,1018),
(1,99919191919,1019),
(1,99920202020,1020),
(1,99921212121,1021),
(1,99922222223,1022),
(1,99923232323,1023),
(1,99924242424,1024),
(1,99925252525,1025),
(1,99926262626,1026),
(1,99927272727,1027),
(1,99928282828,1028),
(1,99929292929,1029),
(1,99930303030,1030),
(1,99931313131,1031),
(1,99932323232,1032),
(1,99933333334,1033),
(1,99934343434,1034),
(1,99935353535,1035),
(1,99936363636,1036),
(1,99937373737,1037),
(1,99938383838,1038),
(1,99939393939,1039),
(1,99940404040,1040),
(1,99941414141,1041),
(1,99942424242,1042),
(1,99943434343,1043),
(1,99944444445,1044),
(1,99945454545,1045),
(1,99946464646,1046),
(1,99947474747,1047),
(1,99948484848,1048),
(1,99949494949,1049),
(1,99950505050,1050);
-- CURSO --
INSERT INTO curso(nome) VALUES
('Engenharia'),
('Medicina'),
('Direito'),
('Computacao'),
('Arquitetura');
-- CURSO_OFERECE_VESTIBULAR --
INSERT INTO curso_oferece_vestibular VALUES
(1,1,50),
(2,2,40),
(3,3,60),
(4,4,45),
(5,5,35);
-- CORRETOR --
INSERT INTO corretor VALUES
(88811111111,'Ricardo Gomes','ricardo@gmail.com'),
(88822222222,'Patricia Lima','patricia@gmail.com'),
(88833333333,'Anderson Silva','anderson@gmail.com'),
(88844444444,'Vanessa Costa','vanessa@gmail.com'),
(88855555555,'Roberta Souza','roberta@gmail.com');

-- CRIAR 2 USARUIS ARBITRARIOS--
CREATE USER 'Elton'@'%' IDENTIFIED BY '00o00';
CREATE USER 'Elias'@'%' IDENTIFIED BY '00i00';

-- CRIAR1 ROLE E GARANTIR NO MINIMO 2 PRIVILEGIOD E INSERIR USARRIOS NELA POR PADRAO --
CREATE ROLE 'Insert_select';
GRANT INSERT,SELECT ON faculdade.* TO 'Insert_select';
GRANT  'Insert_select' TO 'Elton'@'%';
GRANT  'Insert_select' TO 'Elias'@'%';
SET DEFAULT ROLE  'Insert_select'  TO 'Elton'@'%';
SET DEFAULT ROLE  'Insert_select'  TO 'Elias'@'%';


-- FUNCAO RETORNA A VAGA DA SALA SELECIONADA--
DELIMITER $$
DROP FUNCTION IF EXISTS retornavagas $$
CREATE FUNCTION retornavagas (id_sala INT ) 
RETURNS INT 
DETERMINISTIC
BEGIN

    DECLARE vagasTotais INT;
    SELECT vagas
    INTO vagasTotais
    FROM sala WHERE numero = id_sala ;
    
    RETURN vagasTotais;
    
END$$
DELIMITER ;

-- FUNCAO QUE CALCULA O NUMERO DE VESTIBULANDO ATUAIS NA SALA SELECIONADA --
DELIMITER $$
DROP FUNCTION IF EXISTS contavestibulandos $$
CREATE FUNCTION contavestibulandos ( id_sala INT) 
RETURNS INT 
DETERMINISTIC
BEGIN

    DECLARE numerovestibulandos INT;

    SELECT COUNT(cpf)
    INTO numerovestibulandos
    FROM vestibulando  WHERE vestibulando. numero_sala = id_sala;

    RETURN numerovestibulandos;

END$$
DELIMITER ;

-- TRIGGER QUE USA AS FUNCOES ANTERIORES PARA CONTROLAR O NUMERO DE VESTIBULANDOS EM SALA DE ACORDO COM AS VAGAS POR SALA--
DELIMITER $$
CREATE TRIGGER controlavagas
BEFORE INSERT ON vestibulando
FOR EACH ROW
BEGIN

    DECLARE vagasTotais INT;
    DECLARE vestibulandos INT;

    SET vagasTotais = retornavagas(NEW.numero_sala);
    SET vestibulandos = contavestibulandos(NEW.numero_sala);

    IF vestibulandos >= vagasTotais THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Sem vagas disponiveis na sala ';
    END IF;

END$$
DELIMITER ;

ALTER TABLE vestibulando_presta_vestibular ADD COLUMN nota   INT NULL;

SELECT * FROM vestibulando;
