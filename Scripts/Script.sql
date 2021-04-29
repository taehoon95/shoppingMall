-- shoppingMall_LTH
DROP SCHEMA IF EXISTS shoppingMall_LTH;

-- shoppingMall_LTH
CREATE SCHEMA shoppingMall_LTH;

-- 판매내역
CREATE TABLE shoppingMall_LTH.sale (
	orderno    INT         NOT NULL COMMENT '주문번호', -- 주문번호
	date       DATE        NULL     COMMENT '주문날짜', -- 주문날짜
	cusno      INT         NULL     COMMENT '회원번호', -- 회원번호
	procode    VARCHAR(40) NULL     COMMENT '제품코드', -- 제품코드
	saleamount INT         NULL     COMMENT '판매수량' -- 판매수량
)
COMMENT '판매내역';

-- 판매내역
ALTER TABLE shoppingMall_LTH.sale
	ADD CONSTRAINT PK_sale -- 판매내역 기본키
		PRIMARY KEY (
			orderno -- 주문번호
		);

ALTER TABLE shoppingMall_LTH.sale
	MODIFY COLUMN orderno INT NOT NULL AUTO_INCREMENT COMMENT '주문번호';

-- 제품정보
CREATE TABLE shoppingMall_LTH.product (
	procode      VARCHAR(40)  NOT NULL COMMENT '제품코드', -- 제품코드
	proname      VARCHAR(200) NULL     COMMENT '제품명', -- 제품명
	proprice     INT          NULL     COMMENT '제품단가', -- 제품단가
	stock        INT          NULL     COMMENT '재고', -- 재고
	prodPic      VARCHAR(50)  NULL     COMMENT '상품사진', -- 상품사진
	categorycode VARCHAR(40)  NULL     COMMENT '카테고리코드' -- 카테고리코드
)
COMMENT '제품정보';

-- 제품정보
ALTER TABLE shoppingMall_LTH.product
	ADD CONSTRAINT PK_product -- 제품정보 기본키
		PRIMARY KEY (
			procode -- 제품코드
		);

-- 회원
CREATE TABLE shoppingMall_LTH.customer (
	cusno   INT         NOT NULL COMMENT '회원번호', -- 회원번호
	passno  CHAR(41)    NULL     COMMENT '비밀번호', -- 비밀번호
	cusname VARCHAR(20) NULL     COMMENT '회원이름', -- 회원이름
	birth   DATE        NULL     COMMENT '생년월일', -- 생년월일
	callno  VARCHAR(13) NULL     COMMENT '휴대전화번호', -- 휴대전화번호
	sex     INT         NULL     COMMENT '성별' -- 성별
)
COMMENT '회원';

-- 회원
ALTER TABLE shoppingMall_LTH.customer
	ADD CONSTRAINT PK_customer -- 회원 기본키
		PRIMARY KEY (
			cusno -- 회원번호
		);

-- 카테고리
CREATE TABLE shoppingMall_LTH.category (
	categorycode VARCHAR(40) NOT NULL COMMENT '카테고리코드', -- 카테고리코드
	categoryname VARCHAR(50) NOT NULL COMMENT '카테고리이름' -- 카테고리이름
)
COMMENT '카테고리';

-- 카테고리
ALTER TABLE shoppingMall_LTH.category
	ADD CONSTRAINT PK_category -- 카테고리 기본키
		PRIMARY KEY (
			categorycode -- 카테고리코드
		);

-- 판매내역
ALTER TABLE shoppingMall_LTH.sale
	ADD CONSTRAINT FK_customer_TO_sale -- 회원 -> 판매내역
		FOREIGN KEY (
			cusno -- 회원번호
		)
		REFERENCES shoppingMall_LTH.customer ( -- 회원
			cusno -- 회원번호
		);

-- 판매내역
ALTER TABLE shoppingMall_LTH.sale
	ADD CONSTRAINT FK_product_TO_sale -- 제품정보 -> 판매내역
		FOREIGN KEY (
			procode -- 제품코드
		)
		REFERENCES shoppingMall_LTH.product ( -- 제품정보
			procode -- 제품코드
		);

-- 제품정보
ALTER TABLE shoppingMall_LTH.product
	ADD CONSTRAINT FK_category_TO_product -- 카테고리 -> 제품정보
		FOREIGN KEY (
			categorycode -- 카테고리코드
		)
		REFERENCES shoppingMall_LTH.category ( -- 카테고리
			categorycode -- 카테고리코드
		);