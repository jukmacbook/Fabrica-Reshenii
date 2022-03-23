INSERT INTO `quiz`.`users` (`userid`, `name`) VALUES ('1', 'John');
INSERT INTO `quiz`.`users` (`userid`, `name`) VALUES ('2', 'Mike');
INSERT INTO `quiz`.`users` (`userid`, `name`) VALUES ('3', 'Andrew');
INSERT INTO `quiz`.`users` (`userid`, `name`) VALUES ('4', 'Paul');


INSERT INTO `quiz`.`quizzes` (`quizid`, `description`, `name`, `start_date`, `stop_date`) VALUES ('1', 'Job', 'Job', '23.03.2022', '23.04.2022');
INSERT INTO `quiz`.`quizzes` (`quizid`, `description`, `name`, `start_date`, `stop_date`) VALUES ('2', 'Home', 'Home', '23.03.2022', '23.04.2022');
INSERT INTO `quiz`.`quizzes` (`quizid`, `description`, `name`, `start_date`, `stop_date`) VALUES ('3', 'Transport', 'Transport', '23.03.2022', '23.04.2022');
INSERT INTO `quiz`.`quizzes` (`quizid`, `description`, `name`, `start_date`, `stop_date`) VALUES ('4', 'Winter', 'Winter', '23.03.2022', '23.04.2022');


INSERT INTO `quiz`.`questions` (`questionid`, `question_text`, `question_type`, `quizID`) VALUES ('1', 'What... ?', 'INPUT', '1');
INSERT INTO `quiz`.`questions` (`questionid`, `question_text`, `question_type`, `quizID`) VALUES ('2', 'How... ?', 'INPUT', '1');
INSERT INTO `quiz`.`questions` (`questionid`, `question_text`, `question_type`, `quizID`) VALUES ('3', 'Please select...', 'SELECT', '2');
INSERT INTO `quiz`.`questions` (`questionid`, `question_text`, `question_type`, `quizID`) VALUES ('4', 'Please select...', 'CHECK_BOX', '2');
INSERT INTO `quiz`.`questions` (`questionid`, `question_text`, `question_type`, `quizID`) VALUES ('5', 'What... ?', 'INPUT', '3');
INSERT INTO `quiz`.`questions` (`questionid`, `question_text`, `question_type`, `quizID`) VALUES ('6', 'How... ?', 'INPUT', '3');
INSERT INTO `quiz`.`questions` (`questionid`, `question_text`, `question_type`, `quizID`) VALUES ('7', 'Please select...', 'SELECT', '4');
INSERT INTO `quiz`.`questions` (`questionid`, `question_text`, `question_type`, `quizID`) VALUES ('8', 'Please select...', 'CHECK_BOX', '4');


