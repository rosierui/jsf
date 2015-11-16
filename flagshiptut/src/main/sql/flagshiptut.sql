

-- ----------------- Warning :: Warning :: Warning -----------------------------
--
--          ALL DATA WILL BE LOST AFTER RUNNING THIS SCRIPT
--
--          BACKUP ANY DATA BEFORE ATTEMPT TO RUN THIS SCRIPT
--
-- ----------------- Warning :: Warning :: Warning -----------------------------

--------------------------------------------------------------------------------
-- The following SQL statements have been executed on MySQL 5.5.46

DROP TABLE IF EXISTS contact_us_category;
DROP TABLE IF EXISTS email_log; -- using email server's capbility to look up sent mails
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS course_type;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS email;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS calendar;
DROP TABLE IF EXISTS setup; -- semester or quarter
DROP TABLE IF EXISTS system_info;
DROP TABLE IF EXISTS generic_page;
DROP TABLE IF EXISTS menu;

DROP TABLE IF EXISTS dconfig_system CASCADE;
DROP TABLE IF EXISTS dconfig_key CASCADE;
DROP TABLE IF EXISTS dconfig_datatype CASCADE;
DROP TABLE IF EXISTS dconfig_attribute CASCADE;

--------------------------------------------------------------------------------
--
DROP USER 'flagshiptut'@'localhost';
DROP USER 'flagshiptut'@'%';
DROP DATABASE flagshiptut;


CREATE DATABASE flagshiptut;

CREATE USER 'flagshiptut'@'localhost' IDENTIFIED BY 'QjzwFd!5'; 
GRANT ALL PRIVILEGES ON flagshiptut.* TO 'flagshiptut'@'localhost' WITH GRANT OPTION; 

CREATE USER 'flagshiptut'@'%' IDENTIFIED BY 'QjzwFd!5'; 
GRANT ALL PRIVILEGES ON flagshiptut.* TO 'flagshiptut'@'%' WITH GRANT OPTION;

USE flagshiptut;

--------------------------------------------------------------------------------
-- Create table setup

CREATE TABLE setup (
    id                      SMALLINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    alias                   VARCHAR(30) NOT NULL,
    name                    VARCHAR(50) NOT NULL,
    school_start_date       DATE, -- inclusive
    school_end_date         DATE, -- inclusive
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);

CREATE TABLE system_info (
    id                      SMALLINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    system_info_date        DATE NOT NULL, -- the message creation date
    message                 VARCHAR(1024), -- message to be dispalyed on screen
    severity                CHAR(1) NOT NULL, -- 'I': info, 'W': warning, 'M': major, 'C': critical. Note: 'I' message can be acknowledged by user
    msg_kick_in_time        DATETIME, -- system info (message) kick in time
    msg_kick_out_time       DATETIME, -- system info (message) kick out time
    functional_locking      BOOLEAN, -- True: lock all functions for all users except super user
    locking_kick_in_time    DATETIME, -- system info kick in time
    active                  BOOLEAN, -- true: this message is active, false: this message is inactive. Note: only one message can be active
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);

CREATE INDEX system_info_idx1 ON system_info (active DESC, system_info_date);

--------------------------------------------------------------------------------
-- Create table role

CREATE TABLE role (
    id                      SMALLINT PRIMARY KEY NOT NULL,
    alias                   VARCHAR(15),
    name                    VARCHAR(50) NOT NULL,
    privileges              VARCHAR(2000), -- object permissions in xml
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);

--------------------------------------------------------------------------------
-- Create table user

CREATE TABLE user (
    id                      INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    first_name              VARCHAR(18) NOT NULL,
    last_name               VARCHAR(18) NOT NULL,
    login_id                VARCHAR(50), -- can use email
    password                VARCHAR(80),
    email                   VARCHAR(50),
    phone                   VARCHAR(20),
    active                  BOOLEAN DEFAULT TRUE, -- true: active, false: inactive; control individual user. If account is inactive, this user is inactive even its own active flag = true    eligible          BOOLEAN DEFAULT FALSE, -- true: eligible for register courses; false: no eligible for registration
    hint                    VARCHAR(50),
    answer                  VARCHAR(50),
    login_attempt           SMALLINT, -- keep the login attept count, if 5 times attempted within 5 minutes, lock this account
    last_attempt_ts         DATETIME, -- savea the first timestamp for a repeat login attempt
    last_login_ip           VARCHAR(50),
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP,
    UNIQUE (login_id),
    UNIQUE (email)
);

CREATE INDEX user_idx1 ON user (first_name, last_name);
CREATE INDEX user_idx2 ON user (last_name, first_name);

--CREATE UNIQUE INDEX teacher_idx1 ON teacher (school_id, user_id);
CREATE UNIQUE INDEX teacher_idx1 ON teacher (user_id);

--------------------------------------------------------------------------------
-- Create table user_role

CREATE TABLE    user_role (
    id                      INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id                 INTEGER NOT NULL REFERENCES user(id),
    role_id                 SMALLINT REFERENCES role(id),
    privileges              VARCHAR(2000), -- object permissions in xml
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);

--------------------------------------------------------------------------------
-- Create table tut_group

CREATE TABLE tutor_group (
    id                      SMALLINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    alias                   VARCHAR(15),
    name                    VARCHAR(50) NOT NULL
);

CREATE TABLE group_type (
    id                      SMALLINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    alias                   VARCHAR(15) UNIQUE NOT NULL,
    name                    VARCHAR(50) NOT NULL
);

CREATE TABLE semester (
    id                      SMALLINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    alias                   VARCHAR(15) UNIQUE NOT NULL,
    name                    VARCHAR(50) NOT NULL
);

CREATE TABLE week (
    id                      SMALLINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    week                    VARCHAR(15) NOT NULL,
    UNIQUE (week)
);

CREATE TABLE announcement (
    week                    VARCHAR(15) PRIMARY KEY NOT NULL,
);

CREATE TABLE group_post (
    week                    VARCHAR(15) PRIMARY KEY NOT NULL,
);


--------------------------------------------------------------------------------
-- Create table evaluation_performance

CREATE TABLE evaluation_performance (
    id                      INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id                 INTEGER REFERENCES `user`(user_id),
    semester                VARCHAR(15) REFERENCES semester(alias),
    week                    VARCHAR(15) REFERENCES week(week),
    attendance              SMALLINT,
    participation           SMALLINT,
    performance             SMALLINT,
    toatl                   SMALLINT,
    note                    VARCHAR(255),
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);

--------------------------------------------------------------------------------
-- Create table evaluation_objective

CREATE TABLE evaluation_objective (
    id                      INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id                 INTEGER REFERENCES `user`(user_id),
    semester                VARCHAR(15) REFERENCES semester(alias),
    week                    VARCHAR(15) REFERENCES week(week),
    -- Part I Interpretive Listening Mode
    eval_part1_1            BOOLEAN, -- I can understand the general idea chronologically
    eval_part1_2            BOOLEAN, -- I can understand key words / phrases in the materials that I am listening or reading
    eval_part1_3            BOOLEAN, -- I can understand the main idea of the text
    eval_part1_comments     VARCHAR(255), -- Other comments
    -- Part I Presentational Mode
    eval_part2_1            BOOLEAN, -- I can present / write my opinion in a correct order
    eval_part2_2            BOOLEAN, -- I can apply the words and phrases to present or to write I have learned or just learned
    eval_part2_3            BOOLEAN, -- I can use connected series of sentenses to present my opinion in presentation or writing
    eval_part2_comments     VARCHAR(255), -- Other comments
    -- Part III Interpretive Communication Mode
    eval_part3_1            BOOLEAN, -- I can apply what I have learned to communicate with tutor
    eval_part3_2            BOOLEAN, -- I can express myself logically
    eval_part3_3            BOOLEAN, -- I can interact with tutor with correct forms
    eval_part3_comments     VARCHAR(255), -- Other comments

    student_evaluation      BOOLEAN, -- true student evaluation, false tutor evaluation
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);

-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- Create course_type table
-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
CREATE TABLE    course_type (
    id                      SMALLINT PRIMARY KEY NOT NULL,
    name                    VARCHAR(30) NOT NULL,
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);


CREATE UNIQUE INDEX user_role_idx1 ON user_role (user_id, role_id);
CREATE UNIQUE INDEX user_role_idx2 ON user_role (role_id, user_id);
--------------------------------------------------------------------------------
-- Create table course

CREATE TABLE    course  (
    id                      INTEGER  PRIMARY KEY DEFAULT nextval('course_seq') NOT NULL,
    school_id               SMALLINT NOT NULL REFERENCES setup(id),
    alias                   VARCHAR(30), -- maps to course_id, it's better no to have space
    name                    VARCHAR(50),
    cn_name                 VARCHAR(50), -- Chinese name
    location                VARCHAR(50),
    teacher_id              INTEGER REFERENCES teacher(id),
    tid                     VARCHAR(30),
    taid                    VARCHAR(30),
    capacity                INTEGER,
    active                  BOOLEAN, -- true: active, false: inactive
    start_date              DATE,
    end_date                DATE,
    begin_time              TIMESTAMP,
    end_time                TIMESTAMP,
    course_type             SMALLINT NULL REFERENCES course_type(id),
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);

--ALTER TABLE course DROP CONSTRAINT course_alias_key;
CREATE        INDEX course_idx1 ON course (school_id, active);
CREATE UNIQUE INDEX course_idx2 ON course (school_id, alias);

--------------------------------------------------------------------------------
-- Create table course_reg

CREATE TABLE course_reg (
    id                      INTEGER PRIMARY KEY DEFAULT nextval('course_reg_seq') NOT NULL,
    student_id              INTEGER REFERENCES user(id),
    course_id               INTEGER REFERENCES course(id),
    reg_date                TIMESTAMP,
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);

CREATE UNIQUE INDEX course_reg_idx1 ON course_reg (student_id, course_id);

--------------------------------------------------------------------------------
-- Create table calendar

CREATE TABLE calendar (
    id                      INTEGER PRIMARY KEY DEFAULT nextval('calendar_seq') NOT NULL,
    school_id               SMALLINT NOT NULL REFERENCES setup(id),
    calendar_date           DATE,
    subject                 VARCHAR(1000),
    no_school_day           BOOLEAN DEFAULT FALSE,
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);


-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- Generic page
-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--------------------------------------------
-- Create table generic_page

CREATE TABLE generic_page (
    id                      SMALLINT PRIMARY KEY DEFAULT nextval('generic_page_seq') NOT NULL,
    page_key                VARCHAR(30) NOT NULL, -- sample keys: homepage, administration, about.us, contact.us
    publish_date            DATE  NOT NULL,
    subject                 VARCHAR(255),
    show_subject            BOOLEAN, -- true: show subject at the center, false: do not show the subject
    content                 TEXT,
    active                  BOOLEAN, -- true: this page is active, false: this page is inactive. Note: only one generic page for one key can be active
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);

CREATE INDEX generic_page_idx1 ON generic_page (page_key, active DESC);

--------------------------------------------------------------------------------
-- Create table contact_us_category

CREATE TABLE contact_us_category (
    id                      SMALLINT PRIMARY KEY DEFAULT nextval('contact_us_category_seq') NOT NULL,
    category                VARCHAR(30),
    email                   VARCHAR(250) NOT NULL,
    ordinal                 SMALLINT,
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);
CREATE INDEX category_idx1 ON contact_us_category (ordinal);

--------------------------------------------------------------------------------
-- Create table menu

CREATE TABLE menu (
    id                      INTEGER PRIMARY KEY DEFAULT nextval('menu_seq') NOT NULL,
    menu_group              VARCHAR(50) NOT NULL, -- latestLinks, account, etc.
    menu_item               VARCHAR(50) NOT NULL, -- menu description
    parent_menu             VARCHAR(50) NOT NULL,
    menu_location           CHAR(1) NOT NULL, -- 'L' - left, 'T' - top, 'R' - right
    target_class            VARCHAR(200) NOT NULL, -- target page class name or target page id
    panel_name              VARCHAR(30), -- subpage id   
    ordinal                 SMALLINT,
    access_role             VARCHAR(200), -- comma separated role alias list, if null the menu can be accessed by anyone 
    active                  BOOLEAN DEFAULT true, -- true: active, false: inactive
    update_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time             TIMESTAMP
);
CREATE INDEX menu_idx1 ON menu (menu_group, parent_menu, ordinal);

--------------------------------------------------------------------------------
--                      dml.sql
--------------------------------------------------------------------------------
START TRANSACTION;
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'News 学校最新动态', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'news', 1, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'Newsletter 学校简讯', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'newsletter', 2, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'Calendar 本学期校历', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'calendar', 3, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'Word List 生字表', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'wordlist', 4, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'Lost and Found', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'lostNFound', 5, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'School Location 校址', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'schoolLocation', 6, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'Class Schedule 课表', '', 'T', 'org.moonwave.ischool.wicket.page.open.CourseLandscapeView', 'classSchedule', 7, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'Classrooms 教室', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'classrooms', 8, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'Clean Classroom 清洁', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'cleanClassroom', 9, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'Donation 捐款', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'donation', 10, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'VIP Card 优惠卡', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'vipCard', 11, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'MCC Form', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'mccForm', 12, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('latestLinks', 'FAQ', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'faq', 13, null);

INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('gardenList', 'Student Essays 学生作文', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'studentEssays', 1, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('gardenList', 'Teacher Articles 教师文章', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'teacherArticles', 2, null);

INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('courses', 'Course List 课程表', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'course', 1, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('courses', 'Admissions 招生', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'admissions', 2, null);

INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('resources', 'Dictionary 中文字词典', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'dictionary', 1, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('resources', 'Chinese Online 在线学中文', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'chineseOnline', 2, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('resources', 'Chirography 中文书法，笔划', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'chirography', 3, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('resources', 'Library 华星书屋', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'library', 4, null);

INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('accounts', 'Create a new Account 新用户', '', 'T', 'org.moonwave.ischool.wicket.page.account.NewAccount', '', 1, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('accounts', 'Forgot Password 忘记密码', '', 'T', 'org.moonwave.ischool.wicket.page.account.ForgotPassword', '', 2, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('accounts', 'Unsubscribe', '', 'T', 'org.moonwave.ischool.wicket.page.account.Unsubscribe', null, 3, null);

INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('aboutus', 'About School 学校', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'aboutSchool', 1, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('aboutus', 'Board of Directors 董事会', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'schoolBoard', 2, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('aboutus', 'Administration 校委会', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'administrationTeam', 3, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('aboutus', 'Student Council 学生会', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'studentCouncil', 4, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('aboutus', 'PTA 家长教师协会', '', 'T', 'org.moonwave.ischool.wicket.page.GenericPublicPage', 'pta', 5, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('aboutus', 'Contact Us 联系我们', '', 'T', 'org.moonwave.ischool.wicket.page.open.ContactUs', 'contactUs', 6, null);

INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('account', 'My Account', '', 'L', 'org.moonwave.ischool.wicket.page.account.MyAccount', null, 1, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('account', 'My Service', '', 'L', 'org.moonwave.ischool.wicket.page.admin.volunteerservice.VolunteerSetupHolder', null, 2, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('account', 'My Receipt', '', 'L', 'org.moonwave.ischool.wicket.page.account.receipt.MyReceipt', null, 3, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('account', 'My Vote', '', 'L', 'org.moonwave.ischool.wicket.page.account.vote.MyVote', null, 4, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('account', 'Parent Duty', '', 'L', 'org.moonwave.ischool.wicket.page.account.duty.ParentDuty', null, 5, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('account', 'Enrollment', '', 'L', 'org.moonwave.ischool.wicket.page.enroll.Enrollment', null, 6, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('account', 'Change Password', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'changePassword', 7, null);

INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('userAdmin', 'Active Users', '', 'L', 'org.moonwave.ischool.wicket.page.admin.user.UserAdmin', null, 1, 'cashier,vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('userAdmin', 'All Users', '', 'L', 'org.moonwave.ischool.wicket.page.admin.user.UserAdmin4AllUsers', null, 2, 'cashier,vp,vpr,superuser,principal');

INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('schoolConfig', 'School Setup', '', 'L', 'org.moonwave.ischool.wicket.page.admin.school.SchoolSetup', null, 1, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('schoolConfig', 'Copy Courses', '', 'L', 'org.moonwave.ischool.wicket.page.admin.course.CopyCourses', null, 2, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('schoolConfig', 'Teacher Setup', '', 'L', 'org.moonwave.ischool.wicket.page.admin.teacher.TeacherSetup', null, 3, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('schoolConfig', 'Course Setup', '', 'L', 'org.moonwave.ischool.wicket.page.admin.course.CourseSetup', null, 4, 'vp,vpr,superuser,principal');

INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Rich Text Email', '', 'L', 'org.moonwave.ischool.wicket.page.admin.email.Email', 'richEmail', 1, 'teacher,teacherLead,board,vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Plain Text Email', '', 'L', 'org.moonwave.ischool.wicket.page.admin.email.Email', 'plainEmail', 2, 'teacher,teacherLead,board,vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'My Classes', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'myClasses', 3, 'teacher');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'GenericPageSetup', '', 'L', 'org.moonwave.ischool.wicket.page.tool.genericpage.GenericPageSetup', 'genericPageSetup', 4, 'staff,vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'News Setup', '', 'L', 'org.moonwave.ischool.wicket.page.tool.news.NewsSetup', 'newsSetup', 5, 'staff,vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Vote Setup', '', 'L', 'org.moonwave.ischool.wicket.page.tool.vote.VoteSetup', 'voteSetup', 6, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'CalendarSetup', '', 'L', 'org.moonwave.ischool.wicket.page.tool.calendar.CalendarSetup', 'calendarSetup', 7, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Lost and Found Setup', '', 'L', 'org.moonwave.ischool.wicket.page.tool.lostnfound.LostNFoundSetup', null, 8, 'staff,vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Volunteer Service', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'volunteerService', 9, 'staff,vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Course Summary', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'courseSummary', 10, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Payment Summary', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'paymentSummary', 11, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Payment Search', '', 'L', 'org.moonwave.ischool.wicket.page.PaymentSearch', 'paymentSearch', 12, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Paid Students', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'paidStudent', 13, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'New Students', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'newStudent', 14, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Dropped Students', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'droppedStudent', 15, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'All Students', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'student', 16, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Class Rosters', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'classRosters', 17, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Class Rosters w/ Payment Info', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'classRostersPayment', 18, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Clear Wait Pool', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'clearWaitPool', 19, 'vp,vpr,superuser,principal');
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('tools', 'Balance Transfer', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'balanceTransfer', 20, 'vp,vpr,superuser,principal');

INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('miscLink', 'System Message', '', 'L', 'org.moonwave.ischool.wicket.page.admin.systeminfo.SystemInfoSetup', null, 1, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('miscLink', 'User Role', '', 'L', 'org.moonwave.ischool.wicket.page.admin.userrole.UserRoleConfig', null, 2, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('miscLink', 'SQL Query', '', 'L', 'org.moonwave.ischool.wicket.page.admin.sql.SqlQuery', null, 3, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('miscLink', 'SQL Command', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'sqlCommand', 4, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('miscLink', 'Groovy', '', 'L', 'org.moonwave.ischool.wicket.page.admin.groovy.GroovyConsole', null, 5, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('miscLink', 'JBoss JMX', '', 'L', 'org.moonwave.ischool.wicket.page.admin.jboss.JbossJmx', null, 6, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('miscLink', 'Log Viewer', '', 'L', 'org.moonwave.ischool.wicket.page.admin.jboss.LogViewer', null, 7, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('miscLink', 'SQL Query', '', 'L', 'org.moonwave.ischool.wicket.page.admin.userrole.UserRoleConfig', null, 8, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('miscLink', 'Launch DConfig Editor', '', 'L', '', null, 9, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('miscLink', 'Reload DConfig Data', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'reloadDConfigData', 10, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role) VALUES ('miscLink', 'Reload Ischool Cache', '', 'L', 'org.moonwave.ischool.wicket.page.GenericPage', 'reloadIschoolCache', 11, null);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role, active) VALUES ('miscLink', 'Data Import', '', 'L', 'org.moonwave.ischool.wicket.page.admin.imports.DataImport', null, 12, null, false);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role, active) VALUES ('miscLink', 'File Upload', '', 'L', 'org.apache.wicket.examples.upload.FileUploadEx', null, 13, null, false);
INSERT INTO menu (menu_group, menu_item, parent_menu, menu_location, target_class, panel_name, ordinal, access_role, active) VALUES ('miscLink', 'Test', '', 'L', 'org.apache.wicket.examples.captcha.Test', null, 14, null, true);

COMMIT;

START TRANSACTION;
INSERT INTO role (id, alias, name) VALUES (1, 'student', 'Student');
INSERT INTO role (id, alias, name) VALUES (5, 'teacher', 'School Teacher');
INSERT INTO role (id, alias, name) VALUES (10, 'staff', 'School Staff');
INSERT INTO role (id, alias, name) VALUES (15, 'board', 'Board Member');
INSERT INTO role (id, alias, name) VALUES (18, 'teacherLead', 'Teacher Lead');
INSERT INTO role (id, alias, name) VALUES (20, 'director', 'Board Director');
INSERT INTO role (id, alias, name) VALUES (25, 'chairman', 'Board Chairman');
INSERT INTO role (id, alias, name) VALUES (30, 'vp', 'School Vice Principal');
INSERT INTO role (id, alias, name) VALUES (31, 'vpr', 'Vice Principal, Registration');
INSERT INTO role (id, alias, name) VALUES (35, 'principal', 'School Principal');
INSERT INTO role (id, alias, name) VALUES (40, 'president', 'School President');
INSERT INTO role (id, alias, name) VALUES (45, 'cashier', 'Cashier');
INSERT INTO role (id, alias, name) VALUES (979, 'accountMember', 'Account Member');
INSERT INTO role (id, alias, name) VALUES (989, 'accountHolder', 'Account Holder');
INSERT INTO role (id, alias, name) VALUES (999, 'superuser', 'Super User');
COMMIT;

START TRANSACTION;
INSERT INTO user (user_id, password, first_name, last_name, account_holder) VALUES ('admin', 'A0A7CAFDF64B1970759719646DFDB69A', 'Admin', '', true); -- add super user
INSERT INTO user_role (user_id, role_id) VALUES ((select id from user where user_id = 'admin'), 999); -- superuser
COMMIT;

--hope: '8C728E685DDDE9F7FBBC452155E29639'
--hope1^: 'A0A7CAFDF64B1970759719646DFDB69A'
--------------------------------------------------------------------------------
--                      dconfig-1.2.sql
--------------------------------------------------------------------------------

-- ============================================================================
-- GNU Lesser General Public License
-- ============================================================================
--
-- DConfig - Free Dynamic Configuration Toolkit
-- Copyright (C) 2006 - 2008 Jonathan Luo
--
-- This library is free software; you can redistribute it and/or
-- modify it under the terms of the GNU Lesser General Public
-- License as published by the Free Software Foundation; either
-- version 2.1 of the License, or (at your option) any later version.
--
-- This library is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
-- Lesser General Public License for more details.
--
-- You should have received a copy of the GNU Lesser General Public
-- License along with this library; if not, write to the Free Software
-- Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
--
--

--------------------------------------------------------------------------------
-- The scripts below has been executed on PostgresSQL v.7.4.13, v.8.1.5
--------------------------------------------------------------------------------
-- Sign on as a user with create table, sequence, etc. privileges
-- For example, postgres

-- uncomment the folloeing if you want to drop existing tables, sequences
drop table if exists dconfig_attribute;
drop table if exists dconfig_system;
drop table if exists dconfig_key;
drop table if exists dconfig_datatype;
drop SEQUENCE if exists dconfig_key_seq;
drop SEQUENCE if exists dconfig_attribute_seq;

--------------------------------------------------------------------------------
-- 1. Create table dconfig_datatype (Required)

CREATE TABLE dconfig_datatype (
    alias VARCHAR(10) NOT NULL,
    data_type_name VARCHAR(20) NOT NULL,
    PRIMARY KEY  (alias)
);

--------------------------------------------------------------------------------
-- 2. Create table dconfig_key (Required)

CREATE TABLE     dconfig_key      (
    id            INTEGER PRIMARY KEY DEFAULT nextval('dconfig_key_seq') NOT NULL,
    key_name      VARCHAR(500) NOT NULL,
    inherited     CHAR(1) DEFAULT 'N',
    date_created  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE  ( key_name )
);

--------------------------------------------------------------------------------
-- 3. Create table dconfig_attribute (Required)

CREATE TABLE dconfig_attribute (
    id              INTEGER PRIMARY KEY DEFAULT nextval('dconfig_attribute_seq') NOT NULL,
    key_id          INTEGER NOT NULL REFERENCES dconfig_key(id),
    attribute_name  VARCHAR(100) NOT NULL,
    data_type_alias VARCHAR(10) DEFAULT NULL REFERENCES dconfig_datatype(alias),
    reference       CHAR(1) DEFAULT 'N',
    attribute_value VARCHAR(1000) DEFAULT NULL,
    comments        VARCHAR(1000) DEFAULT NULL,
    date_created    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_modified   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (key_id,attribute_name)
);

--------------------------------------------------------------------------------
-- 4. Create table dconfig_system (Required)
CREATE TABLE     dconfig_system      (
    system_name VARCHAR(100) NOT NULL,
    data_type_alias VARCHAR(10) DEFAULT NULL REFERENCES dconfig_datatype(alias),
    system_value VARCHAR(500),
    comments VARCHAR(500) NULL,
    date_created  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY  (system_name)
);

--------------------------------------------------------------------------------
-- 5. Create data type data (Required)

START TRANSACTION;
insert into dconfig_datatype (alias, data_type_name) values ('bin', 'binary');
insert into dconfig_datatype (alias, data_type_name) values ('bool', 'Boolean');
insert into dconfig_datatype (alias, data_type_name) values ('boolar', 'Boolean Array');
insert into dconfig_datatype (alias, data_type_name) values ('int', 'Integer');
insert into dconfig_datatype (alias, data_type_name) values ('intar', 'Integer Array');
insert into dconfig_datatype (alias, data_type_name) values ('long', 'Long');
insert into dconfig_datatype (alias, data_type_name) values ('longar', 'Long Array');
insert into dconfig_datatype (alias, data_type_name) values ('str', 'String');
insert into dconfig_datatype (alias, data_type_name) values ('strar', 'String Array');
insert into dconfig_datatype (alias, data_type_name) values ('float', 'Float');
insert into dconfig_datatype (alias, data_type_name) values ('floatar', 'Float Array');
insert into dconfig_datatype (alias, data_type_name) values ('double', 'Double');
insert into dconfig_datatype (alias, data_type_name) values ('doublear', 'Double Array');
insert into dconfig_datatype (alias, data_type_name) values ('dt', 'Datetime');
insert into dconfig_datatype (alias, data_type_name) values ('dtar', 'Datetime array');
COMMIT;

-- 6. Create system data (Required)
insert into dconfig_system (system_name, data_type_alias, system_value, comments) values ('db.schema.version', 'str', '1.2', 'current db schema version');
insert into dconfig_system (system_name, data_type_alias, system_value, comments) values ('db.datachange.timestamp', 'dt', '2007-01-01 00:00:00', 'a new time stamp should be updated if any data changes (insert, update, delete) occurred in dconfig_key, dconfig_attribute tables');

--------------------------------------------------------------------------------
-- 7. Create a user account 'dcfg' (Optional)
-- You can use existing user account, replace dcfg with the actual user account.
-- and GRANT proper permissions to this account
--
-- CREATE USER dcfg WITH LOGIN PASSWORD 'dconfig';
-- Grant SELECT, INSERT, UPDATE, DELETE, EXECUTE ON TABLE dconfig_key to dcfg;
-- Grant SELECT, INSERT, UPDATE, DELETE ON TABLE dconfig_attribute to dcfg;
-- Grant SELECT, INSERT, UPDATE, DELETE ON TABLE dconfig_datatype to dcfg;
-- Grant EXECUTE sequence dconfig_attribute_seq to dcfg;

-- Note:

--------------------------------------------------------------------------------
--                      dconfig-1.3.sql
--------------------------------------------------------------------------------

-- Update db.schema.version value
update dconfig_system set system_value = '1.3' where system_name = 'db.schema.version';

--------------------------------------------------------------------------------
-- Add new action types to dconfig_datatype
insert into dconfig_datatype (alias, data_type_name) values ('op', 'Operation');
insert into dconfig_datatype (alias, data_type_name) values ('opgrp', 'Operation Group');



--------------------------------------------------------------------------------
--                      dconfig-1.3-ischool.sql
--------------------------------------------------------------------------------
START TRANSACTION;
insert into dconfig_key (key_name) values ('config.setup');
insert into dconfig_key (key_name) values ('config.application');
insert into dconfig_key (key_name) values ('config.ischool');
insert into dconfig_key (key_name) values ('config.ischool.app');
insert into dconfig_key (key_name) values ('config.ischool.regRules');
COMMIT;

START TRANSACTION;
insert into dconfig_attribute (key_id, attribute_name, data_type_alias, attribute_value, comments)
    values ((select id from dconfig_key where key_name = 'config.setup'), 'tableBgColor', 'str',
    '#664848', 'Table background color');
insert into dconfig_attribute (key_id, attribute_name, data_type_alias, attribute_value, comments)
    values ((select id from dconfig_key where key_name = 'config.setup'), 'columnCaptionBgColor', 'str',
    '#23c1d5', 'Table column caption background color');
insert into dconfig_attribute (key_id, attribute_name, data_type_alias, attribute_value, comments)
    values ((select id from dconfig_key where key_name = 'config.setup'), 'oddRowBgColor', 'str',
    '#f0f7f7', 'Table odd row background color');
insert into dconfig_attribute (key_id, attribute_name, data_type_alias, attribute_value, comments)
    values ((select id from dconfig_key where key_name = 'config.setup'), 'evenRowBgColor', 'str',
    '#e3ebc2', 'Table even row background color, or use #f7f7f0');

insert into dconfig_attribute (key_id, attribute_name, data_type_alias, attribute_value, comments)
    values ((select id from dconfig_key where key_name = 'config.setup'), 'rowsPerPage', 'int',
    '12', 'Displays 12 rows per page');

insert into dconfig_attribute (key_id, attribute_name, data_type_alias, attribute_value, comments)
    values ((select id from dconfig_key where key_name = 'config.application'), 'deploymentMode', 'strar',
'development&^#;qa&^#;production', '');
insert into dconfig_attribute (key_id, attribute_name, data_type_alias, attribute_value, comments)
    values ((select id from dconfig_key where key_name = 'config.application'), 'deployment', 'str',
'development', 'develop / qa / production');
COMMIT;
