-----FOR TEST ENVIRONMENT---------------

-- PRODUCT DOMAIN -----------------
insert into product (`code`, `name`) values ('AOA','대출금리 한도조회 중계'); --1
insert into product (`code`, `name`) values ('IRIS','사설인증 중계'); --2
insert into product (`code`, `name`) values ('KAKAOBIZ','카카오비즈메시지'); --3

insert into product_role (`product_id`, `authority`,`description`) values ('1','MANAGER','이용기관 AOA 관리자'); --1 AOA 관리자
insert into product_role (`product_id`, `authority`,`description`) values ('1','ACCOUNT','이용기관 AOA 정산담당'); --2 AOA 정산 담당
insert into product_role (`product_id`, `authority`,`description`) values ('1','USER','이용기관 AOA 사용자'); --3 AOA 사용자
insert into product_role (`product_id`, `authority`,`description`) values ('2','MANAGER','일반 사용자'); --3 IRIS 관리자
insert into product_role (`product_id`, `authority`,`description`) values ('2','USER','일반 사용자'); --4 IRIS 사용자

insert into product_menu (`product_id`, `title`,`depth_no`,`parent_menu`,`url`,`description`,`order_no`,`type`) values ('1','서비스A',0,null,'/a/execute','',0,'group'); --1 A/서비스A
insert into product_menu (`product_id`, `title`,`depth_no`,`parent_menu`,`url`,`description`,`order_no`,`type`) values ('1','정산',1,1,'/a/execute','',0,'collapse'); --2 A/서비스A-정산
insert into product_menu (`product_id`, `title`,`depth_no`,`parent_menu`,`url`,`description`,`order_no`,`type`) values ('1','집계',2,2,'/a/execute/sum','',0,'item'); --3 A/서비스A-정산-집계
insert into product_menu (`product_id`, `title`,`depth_no`,`parent_menu`,`url`,`description`,`order_no`,`type`) values ('1','조회 ',2,2,'/a/execute/data','',3,'item'); --4 A/서비스A-정산-조회
insert into product_menu (`product_id`, `title`,`depth_no`,`parent_menu`,`url`,`description`,`order_no`,`type`) values ('1','수수료관리',2,2,'/a/execute/fee','',0,'item'); --5 A/서비스A-정산-수수료관리
insert into product_menu (`product_id`, `title`,`depth_no`,`parent_menu`,`url`,`description`,`order_no`,`type`) values ('1','거래내역',1,1,'/a/execute/fee','',0,'item'); --6 A/서비스A-거래내역

insert into product_menu (`product_id`, `title`,`depth_no`,`parent_menu`,`url`,`description`,`order_no`,`type`) values ('2','서비스B',0,null,'/b/execute','',0,'group'); --7 B/서비스B
insert into product_menu (`product_id`, `title`,`depth_no`,`parent_menu`,`url`,`description`,`order_no`,`type`) values ('2','관리',1,7,'/b/manage','',1,'collapse'); --8 B/서비스B-관리
insert into product_menu (`product_id`, `title`,`depth_no`,`parent_menu`,`url`,`description`,`order_no`,`type`) values ('2','공지등록',1,7,'/b/manage/alert','',1,'item'); --9 B/서비스B-공지등록
insert into product_menu (`product_id`, `title`,`depth_no`,`parent_menu`,`url`,`description`,`order_no`,`type`) values ('2','공지',1,7,'/b/manage/alert/list','',0,'item'); --10 B/서비스B-공지조

insert into menu_role (`menu_id`, `role_id`) values ('1','1'); --중/정산
insert into menu_role (`menu_id`, `role_id`) values ('2','1'); --소/집계
insert into menu_role (`menu_id`, `role_id`) values ('3','1'); --소/조회
insert into menu_role (`menu_id`, `role_id`) values ('4','1'); --소/수수료등록

insert into menu_role (`menu_id`, `role_id`) values ('1','2'); --중/정산
insert into menu_role (`menu_id`, `role_id`) values ('2','2'); --소/집계
insert into menu_role (`menu_id`, `role_id`) values ('3','2'); --소/조회

insert into menu_role (`menu_id`, `role_id`) values ('5','1'); --중/정산
insert into menu_role (`menu_id`, `role_id`) values ('6','1'); --소/집계
insert into menu_role (`menu_id`, `role_id`) values ('7','1'); --소/조회

-- COMPANY DOMAIN -----------------
insert into company (`name`, `code`,`type`) values ( '코알라', 'koala','U');

-- MEMBER DOMAIN -----------------
insert into member (`nickname`, `email`,`password`,`account_non_locked`,`enabled`, `login_fail_count`) values ( '홍길동', 'gildong@test.com','test1234',true,true,0);
insert into member (`nickname`, `email`,`password`,`account_non_locked`,`enabled`, `login_fail_count`,`company_id`) values ( '일반사용자', 'user@test.com','user1234!',true,true,0,1);
insert into member (`nickname`, `email`,`password`,`account_non_locked`,`enabled`, `login_fail_count`) values ( '관리자', 'admmin@test.com','admin1234!',true,true,0);
insert into member (`nickname`, `email`,`password`,`account_non_locked`,`enabled`, `login_fail_count`) values ('API사용자', 'api@test.com','api1234!',true,true,0);

insert into member_role (`member_id`,`role_id`) values ('2','1'); --USER / AOA 관리자
insert into member_role (`member_id`,`role_id`) values ('2','2'); --USER / AOA 정산 담당
insert into member_role (`member_id`,`role_id`) values ('3','1'); --ADMIN / AOA 관리자
insert into member_role (`member_id`,`role_id`) values ('3','3'); --ADMIN / IRIS 관리자

insert into member_product (`member_id`,`product_id`) values ('2','1'); --USER / AOA
insert into member_product (`member_id`,`product_id`) values ('3','1'); --ADMIN / AOA
insert into member_product (`member_id`,`product_id`) values ('3','2'); --ADMIN / IRIS

