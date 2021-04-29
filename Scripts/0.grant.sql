select user(),database();

grant all
   on shopping.*
   to 'user_shopping'@'localhost'
identified by 'rootroot';



grant all
   on shoppingmall_lth.*
   to 'user_shoppingmall_lth'@'localhost'
identified by 'rootroot';