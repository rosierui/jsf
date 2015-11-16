
START TRANSACTION;
INSERT INTO role (alias, name, create_time) values ('student', 'Student', CURRENT_TIMESTAMP);
INSERT INTO role (alias, name, create_time) values ('tutor', 'Tutor', CURRENT_TIMESTAMP);
INSERT INTO role (alias, name, create_time) values ('teacher', 'Teacher', CURRENT_TIMESTAMP);
INSERT INTO role (alias, name, create_time) values ('supervisor', 'Supervisor', CURRENT_TIMESTAMP);
INSERT INTO role (alias, name, create_time) values ('admin', 'Administrator', CURRENT_TIMESTAMP);
COMMIT;


START TRANSACTION;
INSERT INTO semester (alias, name) values ('2015F', 'Fall 2015');
INSERT INTO semester (alias, name) values ('2016S', 'Spring 2015');
INSERT INTO semester (alias, name) values ('2016F', 'Fall 2016');
COMMIT;

START TRANSACTION;
INSERT INTO week (week) values ('Week 1');
INSERT INTO week (week) values ('Week 2');
INSERT INTO week (week) values ('Week 3');
INSERT INTO week (week) values ('Week 4');
INSERT INTO week (week) values ('Week 5');
INSERT INTO week (week) values ('Week 6');
INSERT INTO week (week) values ('Week 7');
INSERT INTO week (week) values ('Week 8');
INSERT INTO week (week) values ('Week 9');
INSERT INTO week (week) values ('Week 10');
INSERT INTO week (week) values ('Week 11');
INSERT INTO week (week) values ('Week 12');
INSERT INTO week (week) values ('Week 13');
INSERT INTO week (week) values ('Week 14');
INSERT INTO week (week) values ('Week 15');
INSERT INTO week (week) values ('Week 16');
COMMIT;

START TRANSACTION;
INSERT INTO tutor_group (alias, name, ordinal) values ('Listening & Oral 1', 'Listening & Oral 2 Tutoring Group', 1);
INSERT INTO tutor_group (alias, name, ordinal) values ('Listening & Oral 2', 'Listening & Oral 2 Tutoring Group', 5);
INSERT INTO tutor_group (alias, name, ordinal) values ('Grammar', 'Grammar Tutoring Group', 10);
INSERT INTO tutor_group (alias, name, ordinal) values ('HSK', 'HSK Tutoring Group', 15);
INSERT INTO tutor_group (alias, name, ordinal) values ('Capstone Preparation', 'Capstone Preparation Tutoring Group', 20);
INSERT INTO tutor_group (alias, name, ordinal) values ('Pronunciation & Fluency', 'Pronunciation & Fluency Tutoring Group', 25);
INSERT INTO tutor_group (alias, name, ordinal) values ('Reading', 'Reading Tutoring Group', 30);
INSERT INTO tutor_group (alias, name, ordinal) values ('Reading & Oral', 'Reading & Oral Tutoring Group', 35);
INSERT INTO tutor_group (alias, name, ordinal) values ('Writing', 'Writing Tutoring Group', 40);
INSERT INTO tutor_group (alias, name, ordinal) values ('Practice', 'Practice Tutoring Group', 45);
COMMIT;


