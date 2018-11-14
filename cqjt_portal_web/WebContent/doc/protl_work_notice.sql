CREATE TABLE protl_work_notice
(
	workId          VARCHAR2(50) NOT NULL,
	workTitle       VARCHAR2(200) NOT NULL,
	workContent     VARCHAR2(4000) NOT NULL,
	workTop         DATE,
	workDel         NUMBER(8,2) DEFAULT 0 NOT NULL,
	workCreateDate  DATE NOT NULL,
	workEditDate    DATE,
	workSendUserId  VARCHAR2(50) NOT NULL,
	workRecvDeptId  VARCHAR2(500) NOT NULL,
	workRecvUserId  VARCHAR2(500)
)
;


ALTER TABLE protl_work_notice ADD CONSTRAINT PK_protl_word_notice 
	PRIMARY KEY (workId)
;

