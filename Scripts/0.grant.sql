select user(),database();

grant all
   on shopping.*
   to 'user_shopping'@'localhost'
identified by 'rootroot';