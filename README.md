# epayLater-test
epayLaterInterviewAssignment

# DataBase Queries
//create DataBase
CREATE DATABASE `epaylater` /*!40100 DEFAULT CHARACTER SET latin1 */;

//Create Tables;

CREATE TABLE `credit_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(45) NOT NULL,
  `credit_limit_original` int(11) NOT NULL,
  `credit_limit_remaining` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;


CREATE TABLE `token_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(45) NOT NULL,
  `token` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `token_UNIQUE` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `transaction_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


//Insert Dummy Data

INSERT INTO `epaylater`.`credit_data` (`id`, `phone`, `credit_limit_original`, `credit_limit_remaining`) VALUES ('1', '7976585123', '2000', '2000');


# Commands to build project
gradle build

# Command to make fat jar for deployment
gradle shadow jar

//Jar is prepared at location build/libs/testApp.jar

# Command To Start Server
java -jar <jar location> server <config.yml location>

# to run in IDE
main class : in.epaylater.testApp.server.TestAppServer
program Arguments : server <config.yml location>
