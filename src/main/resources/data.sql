-----USED ONLY TEST ENVIRONMENT---------------

-- PRODUCT DOMAIN -----------------
insert into product (`code`, `name`) values ('AOA','대출금리 한도조회 중계'); --1
insert into product (`code`, `name`) values ('IRIS','사설인증 중계'); --2
insert into product (`code`, `name`) values ('KAKAOBIZ','카카오비즈메시지'); --3

insert into product_role (`product_id`, `authority`,`description`) values ('1','MANAGER','이용기관 AOA 관리자'); --1 AOA 관리자
insert into product_role (`product_id`, `authority`,`description`) values ('1','USER','이용기관 AOA 사용자'); --2 AOA 사용자
insert into product_role (`product_id`, `authority`,`description`) values ('2','MANAGER','일반 사용자'); --3 IRIS 관리자
insert into product_role (`product_id`, `authority`,`description`) values ('2','USER','일반 사용자'); --4 IRIS 사용자

-- MEMBER DOMAIN -----------------
insert into member (`nickname`, `email`,`password`,`account_non_locked`,`enabled`, `login_fail_count`) values ( '홍길동', 'gildong@test.com','test1234',true,true,0);
insert into member (`nickname`, `email`,`password`,`account_non_locked`,`enabled`, `login_fail_count`) values ( '일반사용자', 'user@test.com','user1234!',true,true,0);
insert into member (`nickname`, `email`,`password`,`account_non_locked`,`enabled`, `login_fail_count`) values ( '관리자', 'admmin@test.com','admin1234!',true,true,0);
insert into member (`nickname`, `email`,`password`,`account_non_locked`,`enabled`, `login_fail_count`) values ('API사용자', 'api@test.com','api1234!',true,true,0);

insert into member_role (`member_id`,`role_id`) values ('2','1'); --USER / AOA 관리자
insert into member_role (`member_id`,`role_id`) values ('2','2'); --USER / AOA 사용자
insert into member_role (`member_id`,`role_id`) values ('3','1'); --ADMIN / AOA 관리자
insert into member_role (`member_id`,`role_id`) values ('3','3'); --ADMIN / IRIS 관리자

insert into member_product (`member_id`,`product_id`) values ('2','1'); --USER / AOA
insert into member_product (`member_id`,`product_id`) values ('3','1'); --ADMIN / AOA
insert into member_product (`member_id`,`product_id`) values ('3','2'); --ADMIN / IRIS



