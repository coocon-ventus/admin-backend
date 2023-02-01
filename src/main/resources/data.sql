insert into member (`user_id`,`name`, `email`,`password`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`enabled`) values ('gildong1443', '홍길동', 'gildong@test.com','test1234',true,true,true,true);
insert into member (`user_id`,`name`, `email`,`password`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`enabled`) values ('user', '일반사용자', 'user@test.com','user1234!',true,true,true,true);
insert into member (`user_id`,`name`, `email`,`password`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`enabled`) values ('admin', '관리자', 'admmin@test.com','admin1234!',true,true,true,true);
insert into member (`user_id`,`name`, `email`,`password`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`enabled`) values ('api', 'API사용자', 'api@test.com','api1234!',true,true,true,true);

insert into role (`authority`,`description`) values ('USER','일반 사용자');
insert into role (`authority`,`description`) values ('ADMIN','관리자');
insert into role (`authority`,`description`) values ('API','API플랫폼 사용자');

insert into member_role (`member_id`,`role_id`) values ('2','1'); --일반사용자
insert into member_role (`member_id`,`role_id`) values ('3','2'); --관리자
insert into member_role (`member_id`,`role_id`) values ('4','3'); --API 사용자