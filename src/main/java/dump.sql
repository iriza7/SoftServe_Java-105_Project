DROP DATABASE IF EXISTS `test`;
CREATE DATABASE  IF NOT EXISTS `test`;
 use `test`;

-- 
-- Table structure for table `posts`
-- 

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
    `POSTID` INT (10) NOT NULL AUTO_INCREMENT, 
    `TITLE` VARCHAR (100) NULL , 
    `DESCRIPTION` VARCHAR (1000) NULL , 
    `DATE` VARCHAR (60) NULL , 
    `IMGSRC` VARCHAR (60) NULL ,
    PRIMARY KEY (POSTID)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `posts`
-- 
LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES ('2', 'Merkel condemns US over EU insult', 'U insultPolice in Kiev, 6 FebMasked protesters march through Kiev, 6 FebruaryPrevious image | Pause | Next image3 / 4Germanys Angela Merkel says a US officials apparent insult of the EUs work in Ukraine in a leaked recording is "totally unacceptable". ', '24-02-2014 11:43:36', NULL), ('3', 'Earliest UK human footprints found', 'Scientists discover the earliest evidence of human footprints outside of Africa on the Norfolk coast in Eastern England. ', '2014-02-21 15:18:46', NULL), ('4', 'Sochi prepares for opening ceremony', 'The costliest Winter Olympics in history officially open in Russia on Friday with a lavish ceremony in Sochi.', '2014-02-21 15:18:48', NULL), ('5', 'Environment chief faces floods anger', 'There is anger directed at Environment Agency chairman Lord Smith ahead of his visit to the flood-hit Somerset Levels.', '2014-02-21 15:18:52', NULL), ('6', 'Cameron: Seven months to save UK', 'David Cameron makes an emotional appeal to Scottish voters to "save the most extraordinary country in history" in Septembers independence vote.', '2014-02-21 15:18:59', NULL), ('7', 'Violent clashes at Rio fares protest', 'Hundreds of Brazilian demonstrators clash with riot police in Rio de Janeiro, in a protest against a rise in public transport fares. ', '2014-02-21 15:19:03', NULL), ('8', 'Mass grave found in Michoacan state', 'Mexico police find four severed heads and a mass grave in the troubled western state of Michoacan, where vigilantes are fighting a notorious drug cartel. ', '24-02-2014 11:44:25', NULL), ('9', 'Pacific castaway health worsens', 'The castaway who says he survived more than a year adrift in the Pacific has been readmitted to hospital for health checks. ', '24-02-2014 11:44:19', NULL), ('10', 'Anderson film opens Berlin festival', 'Wes Andersons latest movie The Grand Budapest Hotel opens the Berlin Film Festival to rave reviews.', '24-02-2014 11:44:12', NULL), ('11', 'US TV legend Jay Leno bows out', 'Veteran television host Jay Leno tapes his final episode of The Tonight Show after 22 years, with help from a few celebrity guests. ', '24-02-2014 11:44:06', NULL), ('12', 'US TV legend Jay Leno bows out', 'Veteran television host Jay Leno tapes his final episode of The Tonight Show after 22 years, with help from a few celebrity guests. ', '24-02-2014 11:43:47', NULL), ('13', 'National Gallery buys first US work', 'The National Gallery makes its first ever acquisition of a painting by an American artist - a 1912 work by George Bellows. ', '24-02-2014 11:43:22', NULL), ('14', '\'No target\' in UK animal tests plan', 'The UK government launches its plan to replace, refine and reduce animals in research, but campaigners are disappointed. ', '24-02-2014 11:43:29', NULL), ('15', 'Salmon born with \'magnetic map\'', 'Scientists believe that Pacific salmon sense changes in intensity and angle of the Earths magnetic field to find their way in the ocean.', '24-02-2014 11:43:33', NULL), ('16', 'US military funds vanishing tech', 'The US military is funding a project to develop electronics that can self-destruct like the secret messages in the Mission Impossible TV show. ', '24-02-2014 11:43:42', NULL);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;


-- 
-- Table structure for table `lines`
-- 

DROP TABLE IF EXISTS `lines`;
CREATE TABLE `lines` (
    `LINEID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `LINENAME` VARCHAR (100) NOT NULL ,
    PRIMARY KEY (LINEID)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `lines`
-- 
LOCK TABLES `lines` WRITE;
/*!40000 ALTER TABLE `lines` DISABLE KEYS */;
INSERT INTO `lines` VALUES ('1', 'L\'viv - Stryy'), ('2', 'Stryy - L\'viv'), ('3', 'Pisochne - Sknyliv'), ('4', 'L\'viv - Stryy1'), ('5', 'Stryy - L\'viv1'), ('6', 'Pisochne - Sknyliv1'), ('7', 'L\'viv - Stryy2'), ('8', 'Stryy - L\'viv2'), ('9', 'Pisochne - Sknyliv2'), ('10', 'L\'viv - Stryy3'), ('11', 'Stryy - L\'viv3'), ('12', 'Pisochne - Sknyliv3'), ('13', 'L\'viv - Stryy3'), ('14', 'L\'viv - Stryy3'), ('15', 'L\'viv - Stryy3'), ('16', 'Stryy - L\'viv3'), ('17', 'Stryy - L\'viv3'), ('18', 'Stryy - L\'viv3'), ('19', 'Pisochne - Sknyliv3'), ('20', 'Pisochne - Sknyliv3'), ('21', 'Pisochne - Sknyliv3');
/*!40000 ALTER TABLE `lines` ENABLE KEYS */;
UNLOCK TABLES;



-- 
-- Table structure for table `routes`
-- 

DROP TABLE IF EXISTS `routes`;
CREATE TABLE `routes` (
    `ROUTEID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `LINEID` INT (10) unsigned NOT NULL , 
    `ROUTECODE` VARCHAR (20) NOT NULL , 
    `ROUTENAME` VARCHAR (53) NOT NULL ,
    PRIMARY KEY (ROUTEID),
    CONSTRAINT `ROUTES_ibfk_1`
    FOREIGN KEY (`LINEID`)
    REFERENCES `lines` (`LINEID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `routes`
-- 
LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES ('1', '1', '1000000000001', 'Sknyliv-Stryy'), ('2', '2', '1000000000002', 'Sknyliv1-Stryy'), ('3', '2', '1000000000003', 'Sknyliv2-Stryy'), ('4', '1', '1000000000004', ''), ('5', '2', '1000000000005', ''), ('6', '2', '100000000006', ''), ('7', '1', '1000000000007', ''), ('8', '2', '1000000000008', ''), ('9', '2', '1000000000009', ''), ('10', '1', '1000000000010', ''), ('11', '2', '1000000000011', ''), ('12', '2', '1000000000012', ''), ('13', '1', '1000000000013', ''), ('14', '2', '1000000000014', ''), ('15', '2', '1000000000015', ''), ('16', '1', '1000000000016', ''), ('17', '2', '1000000000017', ''), ('18', '2', '1000000000018', ''), ('19', '1', '1000000000019', ''), ('20', '2', '1000000000020', ''), ('21', '2', '1000000000021', ''), ('22', '1', '1000000000022', ''), ('23', '2', '1000000000023', ''), ('24', '2', '1000000000024', ''), ('25', '1', '1000000000025', ''), ('26', '2', '1000000000026', ''), ('27', '2', '1000000000027', ''), ('28', '1', '1000000000028', ''), ('29', '2', '1000000000029', ''), ('30', '2', '1000000000030', ''), ('31', '1', '1000000000031', ''), ('32', '2', '1000000000032', ''), ('33', '2', '1000000000033', ''), ('34', '1', '1000000000034', ''), ('35', '2', '1000000000035', ''), ('36', '2', '1000000000036', ''), ('37', '1', '1000000000037', ''), ('38', '2', '1000000000038', ''), ('39', '2', '1000000000039', ''), ('40', '1', '1000000000040', ''), ('41', '2', '1000000000041', ''), ('42', '2', '1000000000042', ''), ('43', '1', '1000000000043', ''), ('44', '2', '1000000000044', ''), ('45', '2', '1000000000045', '');
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `stations`
-- 

DROP TABLE IF EXISTS `stations`;
CREATE TABLE `stations` (
    `STATIONID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `STATIONCODE` VARCHAR (20) NOT NULL , 
    `STATIONNAME` VARCHAR (100) NOT NULL ,
    PRIMARY KEY (STATIONID)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `stations`
-- 
LOCK TABLES `stations` WRITE;
/*!40000 ALTER TABLE `stations` DISABLE KEYS */;
INSERT INTO `stations` VALUES ('1', '0000000000001', 'Stryy'), ('2', '0000000000002', 'Ugers\'ko'), ('3', '0000000000003', 'Pyatnychany'), ('4', '0000000000004', 'Bil\'che-Vol.'), ('5', '0000000000005', 'Pisochne'), ('6', '0000000000006', 'Myckolayiv-Dnist.'), ('7', '0000000000007', 'Zadorognia'), ('8', '0000000000008', 'Cherkasy-Lv.'), ('9', '0000000000009', 'Dmytriya'), ('10', '0000000000010', 'Shchirets\'-1'), ('11', '0000000000011', 'Shchirets\'-2'), ('12', '0000000000012', 'Semenivka'), ('13', '0000000000013', 'Z.P. Pustomyty'), ('14', '0000000000014', 'Glynna-Navaria'), ('15', '0000000000015', 'Oboroshin'), ('16', '0000000000016', 'Sknyliv'), ('17', '0000000000017', 'L\'viv');
/*!40000 ALTER TABLE `stations` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `stationsonlines`
-- 

DROP TABLE IF EXISTS `stationsonlines`;
CREATE TABLE `stationsonlines` (
    `STATIONONLINEID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `LINEID` INT (10) unsigned NOT NULL , 
    `STATIONID` INT (10) unsigned NOT NULL , 
    `STATIONORDERNUM` INT (10) unsigned NOT NULL ,
    PRIMARY KEY (STATIONONLINEID),
    CONSTRAINT `STATIONSONLINES_ibfk_1`
    FOREIGN KEY (`LINEID`)
    REFERENCES `lines` (`LINEID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `STATIONSONLINES_ibfk_2`
    FOREIGN KEY (`STATIONID`)
    REFERENCES `stations` (`STATIONID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `stationsonlines`
-- 
LOCK TABLES `stationsonlines` WRITE;
/*!40000 ALTER TABLE `stationsonlines` DISABLE KEYS */;
INSERT INTO `stationsonlines` VALUES ('1', '1', '17', '1'), ('2', '1', '16', '2'), ('3', '1', '15', '3'), ('4', '1', '14', '4'), ('5', '1', '13', '5'), ('6', '1', '12', '6'), ('7', '1', '11', '7'), ('8', '1', '10', '8'), ('9', '1', '9', '9'), ('10', '1', '8', '10'), ('11', '1', '7', '11'), ('12', '1', '6', '12'), ('13', '1', '5', '13'), ('14', '1', '4', '14'), ('15', '1', '3', '15'), ('16', '1', '2', '16'), ('17', '1', '1', '17'), ('18', '2', '1', '1'), ('19', '2', '2', '2'), ('20', '2', '3', '3'), ('21', '2', '4', '4'), ('22', '2', '5', '5'), ('23', '2', '6', '6'), ('24', '2', '7', '7'), ('25', '2', '8', '8'), ('26', '2', '9', '9'), ('27', '2', '10', '10'), ('28', '2', '11', '11'), ('29', '2', '12', '12'), ('30', '2', '13', '13'), ('31', '2', '14', '14'), ('32', '2', '15', '15'), ('33', '2', '16', '16'), ('34', '2', '17', '17'), ('38', '3', '5', '1'), ('39', '3', '16', '2'), ('40', '4', '5', '1'), ('41', '4', '16', '2'), ('42', '5', '5', '1'), ('43', '5', '16', '2'), ('44', '6', '5', '1'), ('45', '6', '16', '2'), ('46', '7', '5', '1'), ('47', '7', '16', '2'), ('48', '8', '5', '1'), ('49', '8', '16', '2'), ('50', '9', '5', '1'), ('51', '9', '16', '2'), ('52', '10', '5', '1'), ('53', '10', '16', '2'), ('54', '11', '5', '1'), ('55', '11', '16', '2'), ('56', '12', '5', '1'), ('57', '12', '16', '2'), ('58', '13', '5', '1'), ('59', '13', '16', '2'), ('60', '14', '5', '1'), ('61', '14', '16', '2'), ('62', '15', '5', '1'), ('63', '15', '16', '2'), ('64', '16', '5', '1'), ('65', '16', '16', '2'), ('66', '17', '5', '1'), ('67', '17', '16', '2'), ('68', '18', '5', '1'), ('69', '18', '16', '2'), ('70', '19', '5', '1'), ('71', '19', '16', '2'), ('72', '20', '5', '1'), ('73', '20', '16', '2'), ('74', '21', '5', '1'), ('75', '21', '16', '2');
/*!40000 ALTER TABLE `stationsonlines` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `stops`
-- 

DROP TABLE IF EXISTS `stops`;
CREATE TABLE `stops` (
    `STOPID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `ROUTEID` INT (10) unsigned NOT NULL , 
    `STATIONONLINEID` INT (10) unsigned NOT NULL , 
    `ARRIVAL` TIME NOT NULL , 
    `DEPARTURE` TIME NOT NULL ,
    PRIMARY KEY (STOPID),
    CONSTRAINT `STOPS_ibfk_1`
    FOREIGN KEY (`ROUTEID`)
    REFERENCES `routes` (`ROUTEID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `STOPS_ibfk_2`
    FOREIGN KEY (`STATIONONLINEID`)
    REFERENCES `stationsonlines` (`STATIONONLINEID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `stops`
-- 
LOCK TABLES `stops` WRITE;
/*!40000 ALTER TABLE `stops` DISABLE KEYS */;
INSERT INTO `stops` VALUES ('1', '1', '1', '00:00:00', '00:00:00'), ('2', '1', '2', '00:09:00', '00:10:00'), ('3', '1', '3', '00:12:00', '00:13:00'), ('4', '1', '4', '00:24:00', '00:25:00'), ('5', '1', '7', '00:37:00', '00:38:00'), ('6', '1', '12', '01:01:00', '01:02:00'), ('7', '1', '13', '01:09:00', '01:10:00'), ('8', '1', '14', '01:17:00', '01:18:00'), ('9', '1', '17', '02:33:00', '00:00:00'), ('10', '2', '18', '00:00:00', '00:00:00'), ('11', '2', '21', '00:17:00', '00:18:00'), ('12', '2', '22', '00:25:00', '00:26:00'), ('13', '2', '23', '00:33:00', '00:34:00'), ('14', '2', '28', '01:00:00', '01:02:00'), ('15', '2', '31', '01:15:00', '01:16:00'), ('16', '2', '32', '01:23:00', '01:24:00'), ('17', '2', '33', '01:30:00', '01:31:00'), ('18', '2', '34', '01:41:00', '00:00:00'), ('19', '3', '38', '00:00:00', '00:00:00'), ('20', '3', '39', '01:10:00', '00:00:00');
/*!40000 ALTER TABLE `stops` ENABLE KEYS */;
UNLOCK TABLES;



-- 
-- Table structure for table `transports`
-- 

DROP TABLE IF EXISTS `transports`;
CREATE TABLE `transports` (
    `TRANSPORTID` INT (10) NOT NULL AUTO_INCREMENT, 
    `ROUTEID` INT (10) unsigned NOT NULL , 
    `TRANSPORTCODE` VARCHAR (20) NOT NULL , 
    `STARTTIME` TIME NOT NULL , 
    `SEATCLASS1` INT (10) NOT NULL , 
    `SEATCLASS2` INT (10) NOT NULL , 
    `SEATCLASS3` INT (10) NOT NULL , 
    `GENPRICE` DECIMAL (7, 2) NOT NULL ,
    PRIMARY KEY (TRANSPORTID),
    CONSTRAINT `ROUTES_ROUTEID_fk`
    FOREIGN KEY (`ROUTEID`)
    REFERENCES `routes` (`ROUTEID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `transports`
-- 
LOCK TABLES `transports` WRITE;
/*!40000 ALTER TABLE `transports` DISABLE KEYS */;
INSERT INTO `transports` VALUES ('1', '1', 'T000000001', '12:00:00', '11', '150', '110', '25.00'), ('2', '1', 'T000000002', '16:00:00', '13', '120', '130', '35.00'), ('3', '2', 'T000000003', '12:00:00', '12', '130', '120', '45.00'), ('4', '2', 'T000000004', '16:00:00', '14', '110', '160', '55.00'), ('5', '3', 'T000000044', '16:00:00', '15', '140', '150', '25.00'), ('6', '1', 'T000000011', '12:00:00', '16', '155', '140', '35.00'), ('7', '1', 'T000000012', '16:00:00', '17', '145', '130', '45.00'), ('8', '2', 'T000000013', '12:00:00', '18', '160', '170', '55.00'), ('9', '2', 'T000000014', '16:00:00', '19', '170', '120', '35.00'), ('10', '3', 'T000000015', '16:00:00', '110', '50', '160', '22.00'), ('11', '1', 'T000000021', '12:00:00', '120', '50', '150', '25.00'), ('12', '1', 'T000000023', '16:00:00', '130', '50', '170', '33.00'), ('13', '2', 'T000000022', '12:00:00', '140', '50', '140', '44.00'), ('14', '2', 'T000000025', '16:00:00', '150', '50', '150', '25.00'), ('15', '3', 'T000000024', '16:00:00', '160', '50', '130', '27.00');
/*!40000 ALTER TABLE `transports` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `trips`
-- 

DROP TABLE IF EXISTS `trips`;
CREATE TABLE `trips` (
    `TRIPID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `TRANSPORTID` INT (10) NOT NULL , 
    `REMSEATCLASS1` INT (10) NOT NULL , 
    `REMSEATCLASS2` INT (10) NOT NULL , 
    `REMSEATCLASS3` INT (10) NOT NULL , 
    `STARTDATE` DATE NOT NULL ,
    PRIMARY KEY (TRIPID),
    CONSTRAINT `transports_transportid_fk`
    FOREIGN KEY (`TRANSPORTID`)
    REFERENCES `transports` (`TRANSPORTID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `trips`
-- 
LOCK TABLES `trips` WRITE;
/*!40000 ALTER TABLE `trips` DISABLE KEYS */;
INSERT INTO `trips` VALUES ('1', '1', '10', '50', '150', '2013-02-20'), ('2', '1', '10', '50', '150', '2013-02-21'), ('3', '1', '10', '50', '150', '2013-02-22'), ('4', '1', '10', '50', '150', '2013-02-23'), ('5', '2', '10', '50', '150', '2013-02-20'), ('6', '2', '10', '50', '150', '2013-02-21'), ('7', '2', '10', '50', '150', '2013-02-22'), ('8', '2', '10', '50', '150', '2013-02-23'), ('9', '3', '10', '50', '150', '2013-02-20'), ('10', '3', '10', '50', '150', '2013-02-21'), ('11', '3', '10', '50', '150', '2013-02-22'), ('12', '3', '10', '50', '150', '2013-02-23'), ('13', '4', '10', '50', '150', '2013-02-20'), ('14', '4', '10', '50', '150', '2013-02-21'), ('15', '4', '10', '50', '150', '2013-02-22'), ('16', '4', '10', '50', '150', '2013-02-23');
/*!40000 ALTER TABLE `trips` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `users`
-- 

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `USERID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `USERNAME` VARCHAR (100) NOT NULL , 
    `FIRSTNAME` VARCHAR (100) NOT NULL , 
    `LASTNAME` VARCHAR (100) NOT NULL , 
    `EMAIL` VARCHAR (100) NOT NULL , 
    `PASSWD` VARCHAR (100) NOT NULL , 
    `REGDATE` TIMESTAMP NOT NULL , 
    `ROLE` enum('REGUSER','MANAGER','ADMIN') NOT NULL ,
    PRIMARY KEY (USERID),
    UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `users`
-- 
LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES 
('1', 'tranzero', 'Oleg', 'Lesniak', 'test@mail.first', '1144', '2014-03-02 00:25:26.0', 'MANAGER'),
 ('2', 'garasym', 'Yaroslav', 'Garasym', 'test@mail.second', '2555', '2014-03-02 00:25:26.0', 'MANAGER'), 
('3', 'mykhaylo.partyka', 'Mykhaylo', 'Partyka', 'test@mail.third', '3544', '2014-03-02 00:25:26.0', 'ADMIN'),
 ('4', 'iryna', 'Iryna', 'Bautista', 'iri@gmail.com', '7733', '2014-03-02 00:25:26.0', 'ADMIN'),
 ('5', 'nastya', 'Anastasya', 'Pankiy', 'nastya@mail.first', '5533', '2014-03-02 00:25:26.0', 'REGUSER'), 
('6', 'yuriy', 'Yuriy', 'Logazyak', 'test6@gmail', '13777', '2014-03-02 00:25:26.0', 'MANAGER'), 
('7', 'taras', 'Taras', 'Savitskyy', 'taras7@mail.third', '1133', '2014-03-02 00:25:26.0', 'ADMIN'), 
('8', 'roman', 'Roman', 'Parashchak', 'test8@mail.first', '2244', '2014-03-02 00:25:26.0', 'ADMIN'), 
('9', 'petro', 'Petro', 'Matyash', 'test9@mail.second', '8899', '2014-03-02 00:25:26.0', 'MANAGER'), 
('10', 'nazar', 'Nazar', 'Vrublevskiy', 'nazar1@mail.third', '8877', '2014-03-02 00:25:26.0', 'REGUSER'),
 ('11', 'mykhailo', 'Mykhaylo', 'Kozar', 'myk@mail.third', '4699', '2014-03-02 00:25:26.0', 'REGUSER'), 
('12', 'denys', 'Denys', 'Nyckolskyy', 'test12@mail.second', '1277', '2014-03-02 00:25:26.0', 'REGUSER'), 
('13', 'lyubomyr', 'Lyubomyr', 'Pentsko', 'test13@mail.second', '17888', '2014-03-02 00:25:26.0', 'REGUSER'), 
('14', 'test1', 'Test1', 'Test1', 'test01@mail.second', '1144', '2014-03-02 00:25:26.0', 'ADMIN'), 
('15', 'test2', 'Test2', 'Test2', 'test02@mail.second', '1144', '2014-03-02 00:25:26.0', 'REGUSER');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;


-- 
-- Table structure for table `orders`
-- 

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
    `ORDERID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `USERID` INT (10) unsigned NOT NULL , 
    `ORDERDATE` TIMESTAMP NOT NULL ,
    PRIMARY KEY (ORDERID),
    CONSTRAINT `ORDERID_ibfk_1`
    FOREIGN KEY (`USERID`)
    REFERENCES `users` (`USERID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `orders`
-- 
LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('1', '1', '2014-02-10 19:24:50.0'), ('2', '1', '2014-02-10 19:24:52.0'), ('3', '1', '2014-02-10 21:24:50.0'), ('4', '3', '2014-02-10 23:24:50.0'), ('5', '2', '2014-02-10 17:24:50.0');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;


-- 
-- Table structure for table `responses`
-- 

DROP TABLE IF EXISTS `responses`;
CREATE TABLE `responses` (
    `RESPONSEID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `USERID` INT (10) unsigned NOT NULL , 
    `TRIPID` INT (10) unsigned NOT NULL , 
    `COMMENT` VARCHAR (200) NOT NULL , 
    `DATE` DATE NULL , 
    `checked` BIT NOT NULL ,
    PRIMARY KEY (RESPONSEID),
    CONSTRAINT `fk1_`
    FOREIGN KEY (`USERID`)
    REFERENCES `users` (`USERID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk2_`
    FOREIGN KEY (`TRIPID`)
    REFERENCES `trips` (`TRIPID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `responses`
-- 
LOCK TABLES `responses` WRITE;
/*!40000 ALTER TABLE `responses` DISABLE KEYS */;
INSERT INTO `responses` VALUES ('1', '1', '2', 'good', '2014-02-04', true),
 ('2', '1', '6', 'wonderful', '2014-02-11', true), 
('3', '10', '11', 'good', '2014-02-04', true),
 ('4', '8', '13', 'wonderful', '2014-02-18', true),
 ('5', '4', '2', 'wonderful', '2014-02-05', true), 
('6', '7', '9', 'This was a wonderful trip', '2014-02-11', true),
 ('7', '9', '9', 'wonderful', '2014-02-05', true), 
('8', '4', '4', 'This was a wonderful trip', '2014-02-05', true);
/*!40000 ALTER TABLE `responses` ENABLE KEYS */;
UNLOCK TABLES;

-- 
-- Table structure for table `tickets`
-- 

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets` (
    `TICKETID` INT (10) unsigned NOT NULL AUTO_INCREMENT, 
    `TICKETNAME` VARCHAR (100) NOT NULL , 
    `ORDERID` INT (10) unsigned NOT NULL , 
    `TRIPID` INT (10) unsigned NOT NULL , 
    `CUSTOMERINFO` VARCHAR (100) NOT NULL , 
    `SEATTYPE` INT (10) unsigned NOT NULL ,
    PRIMARY KEY (TICKETID),
    CONSTRAINT `fk1`
    FOREIGN KEY (`ORDERID`)
    REFERENCES `orders` (`ORDERID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk2`
    FOREIGN KEY (`TRIPID`)
    REFERENCES `trips` (`TRIPID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `tickets`
-- 
LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES ('1', 'Bogdan', '1', '1', 'Info', '1');

/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;