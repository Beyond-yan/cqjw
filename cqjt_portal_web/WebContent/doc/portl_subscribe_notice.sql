CREATE TABLE portl_subscribe_notice
(
	subsId          VARCHAR2(200) NOT NULL,
	subsTitle       VARCHAR2(200) NOT NULL,
	subsContent     VARCHAR2(4000) NOT NULL,
	subsTop         DATE,
	subsDel         NUMBER(8,2) DEFAULT 0 NOT NULL,
	subsCreateDate  DATE NOT NULL,
	subsEditDate    DATE,
	subsSendUserId  VARCHAR2(50) NOT NULL,
	subsRecvDeptId  VARCHAR2(500) NOT NULL,
	subsRecvUserId  VARCHAR2(500)
)
;


ALTER TABLE portl_subscribe_notice ADD CONSTRAINT PK_portl_subscribe_notice 
	PRIMARY KEY (subsId)
;

