-- 멤버
DROP TABLE IF EXISTS MEMBER RESTRICT;

-- 레시피
DROP TABLE IF EXISTS RECIPE RESTRICT;

-- 요리단계
DROP TABLE IF EXISTS R_PHOTO RESTRICT;

-- 댓글
DROP TABLE IF EXISTS REPLY RESTRICT;

-- 즐겨찾기
DROP TABLE IF EXISTS FAVORITE RESTRICT;

-- 좋아요
DROP TABLE IF EXISTS LIKE2 RESTRICT;

-- 레시피태그
DROP TABLE IF EXISTS FOOD RESTRICT;

-- 태그
DROP TABLE IF EXISTS TAG RESTRICT;

-- 멤버
CREATE TABLE MEMBER (
  MNO      INTEGER      NOT NULL, -- 회원 번호
  ID       VARCHAR(40)  NOT NULL, -- 아이디
  EMAIL    VARCHAR(40)  NOT NULL, -- 이메일
  PWD      VARCHAR(40)  NOT NULL, -- 비밀번호
  PHOTO    VARCHAR(255) NOT NULL, -- 프로필 사진
  S_DT     DATETIME     NOT NULL, -- 가입일
  L_DT     DATETIME     NOT NULL, -- 접속일
  GENDER   VARCHAR(40)  NULL,     -- 성별
  NICK     VARCHAR(40)  NULL,     -- 닉네임
  DESCT    VARChAR(80)  NULL,     -- 소개
  CATEGORY VARCHAR(40)  NULL      -- 새 컬럼
);

-- 멤버
ALTER TABLE MEMBER
  ADD CONSTRAINT PK_MEMBER -- 멤버 기본키
    PRIMARY KEY (
      MNO -- 회원 번호
    );

-- 멤버 유니크 인덱스
CREATE UNIQUE INDEX UIX_MEMBER
  ON MEMBER ( -- 멤버
    ID ASC,    -- 아이디
    EMAIL ASC  -- 이메일
  );

ALTER TABLE MEMBER
  MODIFY COLUMN MNO INTEGER NOT NULL AUTO_INCREMENT;

-- 레시피
CREATE TABLE RECIPE (
  BNO     INTEGER      NOT NULL, -- 레시피번호
  MNO     INTEGER      NOT NULL, -- 회원 번호
  TITLE   VARCHAR(40)  NOT NULL, -- 제목
  W_DT    DATETIME     NOT NULL, -- 작성일
  COUNT   INTEGER      NOT NULL, -- 조회수
  M_PHOTO VARCHAR(255) NOT NULL, -- 대표사진
  C_NARA  INTEGER      NOT NULL, -- 카테고리-나라
  C_SITU  VARCHAR(40)  NOT NULL, -- 카테고리-상황
  C_COOK  INTEGER      NOT NULL, -- 카테고리-조리
  C_FOOD  INTEGER      NOT NULL, -- 카테고리-재료
  TIME    INTEGER      NULL,     -- 조리시간
  LEVEL   INTEGER      NULL      -- 난이도
);

-- 레시피
ALTER TABLE RECIPE
  ADD CONSTRAINT PK_RECIPE -- 레시피 기본키
    PRIMARY KEY (
      BNO -- 레시피번호
    );

-- 레시피 인덱스
CREATE INDEX IX_RECIPE
  ON RECIPE( -- 레시피
    W_DT DESC -- 작성일
  );

ALTER TABLE RECIPE
  MODIFY COLUMN BNO INTEGER NOT NULL AUTO_INCREMENT;

-- 요리단계
CREATE TABLE R_PHOTO (
  PNO   INTEGER      NOT NULL, -- 요리단계번호
  BNO   INTEGER      NOT NULL, -- 레시피번호
  CONT  TEXT         NULL,     -- 내용
  P_URL VARCHAR(255) NULL      -- 미디어주소
);

-- 요리단계
ALTER TABLE R_PHOTO
  ADD CONSTRAINT PK_R_PHOTO -- 요리단계 기본키
    PRIMARY KEY (
      PNO -- 요리단계번호
    );

-- 요리단계 인덱스
CREATE INDEX IX_R_PHOTO
  ON R_PHOTO( -- 요리단계
  );

ALTER TABLE R_PHOTO
  MODIFY COLUMN PNO INTEGER NOT NULL AUTO_INCREMENT;

-- 댓글
CREATE TABLE REPLY (
  RNO  INTEGER NOT NULL, -- 댓글 번호
  BNO  INTEGER NOT NULL, -- 레시피번호
  MNO  INTEGER NOT NULL, -- 회원 번호
  R_CONT TEXT    NOT NULL, -- 댓글 내용
  r_W_DT DATETIME    NOT NULL  -- 작성일
);

-- 댓글
ALTER TABLE REPLY
  ADD CONSTRAINT PK_REPLY -- 댓글 기본키
    PRIMARY KEY (
      RNO -- 댓글 번호
    );

-- 댓글 인덱스
CREATE INDEX IX_REPLY
  ON REPLY( -- 댓글
    r_W_DT ASC -- 작성일
  );

ALTER TABLE REPLY
  MODIFY COLUMN RNO INTEGER NOT NULL AUTO_INCREMENT;

-- 즐겨찾기
CREATE TABLE FAVORITE (
  FNO    INTEGER NOT NULL, -- 즐겨찾기번호
  MNO    INTEGER NOT NULL, -- 회원 번호
  BNO    INTEGER NOT NULL, -- 레시피번호
  ADD_DT DATETIME    NOT NULL  -- 추가일
);

-- 즐겨찾기
ALTER TABLE FAVORITE
  ADD CONSTRAINT PK_FAVORITE -- 즐겨찾기 기본키
    PRIMARY KEY (
      FNO -- 즐겨찾기번호
    );

-- 즐겨찾기 인덱스
CREATE INDEX IX_FAVORITE
  ON FAVORITE( -- 즐겨찾기
    ADD_DT ASC -- 추가일
  );

ALTER TABLE FAVORITE
  MODIFY COLUMN FNO INTEGER NOT NULL AUTO_INCREMENT;

-- 좋아요
CREATE TABLE LIKE2 (
  MNO INTEGER NOT NULL, -- 회원 번호
  BNO INTEGER NOT NULL  -- 레시피번호
);

-- 레시피태그
CREATE TABLE FOOD (
  BNO INTEGER NOT NULL, -- 레시피번호
  TNO INTEGER NOT NULL  -- 태그번호
);

-- 레시피태그
ALTER TABLE FOOD
  ADD CONSTRAINT PK_FOOD -- 레시피태그 기본키
    PRIMARY KEY (
      BNO, -- 레시피번호
      TNO  -- 태그번호
    );

-- 태그
CREATE TABLE TAG (
  TNO   INTEGER     NOT NULL, -- 태그번호
  TNAME VARCHAR(40) NOT NULL  -- 태그명
);

-- 태그
ALTER TABLE TAG
  ADD CONSTRAINT PK_TAG -- 태그 기본키
    PRIMARY KEY (
      TNO -- 태그번호
    );

ALTER TABLE TAG
  MODIFY COLUMN TNO INTEGER NOT NULL AUTO_INCREMENT;

-- 레시피
ALTER TABLE RECIPE
  ADD CONSTRAINT FK_MEMBER_TO_RECIPE -- 멤버 -> 레시피
    FOREIGN KEY (
      MNO -- 회원 번호
    )
    REFERENCES MEMBER ( -- 멤버
      MNO -- 회원 번호
    );

-- 요리단계
ALTER TABLE R_PHOTO
  ADD CONSTRAINT FK_RECIPE_TO_R_PHOTO -- 레시피 -> 요리단계
    FOREIGN KEY (
      BNO -- 레시피번호
    )
    REFERENCES RECIPE ( -- 레시피
      BNO -- 레시피번호
    );

-- 댓글
ALTER TABLE REPLY
  ADD CONSTRAINT FK_MEMBER_TO_REPLY -- 멤버 -> 댓글
    FOREIGN KEY (
      MNO -- 회원 번호
    )
    REFERENCES MEMBER ( -- 멤버
      MNO -- 회원 번호
    );

-- 댓글
ALTER TABLE REPLY
  ADD CONSTRAINT FK_RECIPE_TO_REPLY -- 레시피 -> 댓글
    FOREIGN KEY (
      BNO -- 레시피번호
    )
    REFERENCES RECIPE ( -- 레시피
      BNO -- 레시피번호
    );

-- 즐겨찾기
ALTER TABLE FAVORITE
  ADD CONSTRAINT FK_MEMBER_TO_FAVORITE -- 멤버 -> 즐겨찾기
    FOREIGN KEY (
      MNO -- 회원 번호
    )
    REFERENCES MEMBER ( -- 멤버
      MNO -- 회원 번호
    );

-- 즐겨찾기
ALTER TABLE FAVORITE
  ADD CONSTRAINT FK_RECIPE_TO_FAVORITE -- 레시피 -> 즐겨찾기
    FOREIGN KEY (
      BNO -- 레시피번호
    )
    REFERENCES RECIPE ( -- 레시피
      BNO -- 레시피번호
    );

-- 좋아요
ALTER TABLE LIKE2
  ADD CONSTRAINT FK_MEMBER_TO_LIKE2 -- 멤버 -> 좋아요
    FOREIGN KEY (
      MNO -- 회원 번호
    )
    REFERENCES MEMBER ( -- 멤버
      MNO -- 회원 번호
    );

-- 좋아요
ALTER TABLE LIKE2
  ADD CONSTRAINT FK_RECIPE_TO_LIKE2 -- 레시피 -> 좋아요
    FOREIGN KEY (
      BNO -- 레시피번호
    )
    REFERENCES RECIPE ( -- 레시피
      BNO -- 레시피번호
    );

-- 레시피태그
ALTER TABLE FOOD
  ADD CONSTRAINT FK_TAG_TO_FOOD -- 태그 -> 레시피태그
    FOREIGN KEY (
      TNO -- 태그번호
    )
    REFERENCES TAG ( -- 태그
      TNO -- 태그번호
    );

-- 레시피태그
ALTER TABLE FOOD
  ADD CONSTRAINT FK_RECIPE_TO_FOOD -- 레시피 -> 레시피태그
    FOREIGN KEY (
      BNO -- 레시피번호
    )
    REFERENCES RECIPE ( -- 레시피
      BNO -- 레시피번호
    );

-- FOREIGN KEY 해제
ALTER TABLE favorite DROP FOREIGN KEY  FK_member_TO_favorite;
ALTER TABLE favorite DROP FOREIGN KEY  FK_recipe_TO_favorite;
ALTER TABLE like2 DROP FOREIGN KEY  FK_member_TO_like2;
ALTER TABLE like2 DROP FOREIGN KEY  FK_recipe_TO_like2;
ALTER TABLE reply DROP FOREIGN KEY  FK_member_TO_reply;
ALTER TABLE reply DROP FOREIGN KEY  FK_recipe_TO_reply;
ALTER TABLE recipe DROP FOREIGN KEY  FK_member_TO_recipe;
ALTER TABLE r_photo DROP FOREIGN KEY  FK_recipe_TO_r_photo;

-- FOREIGN KEY 재설정

ALTER TABLE favorite ADD FOREIGN KEY (mno) REFERENCES member (mno) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE reply ADD FOREIGN KEY (mno) REFERENCES member (mno) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE reply ADD FOREIGN KEY (bno) REFERENCES recipe (bno) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE like2 ADD FOREIGN KEY (bno) REFERENCES recipe (bno) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE like2 ADD FOREIGN KEY (mno) REFERENCES member (mno) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE r_photo ADD FOREIGN KEY (bno) REFERENCES recipe (bno) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE recipe ADD FOREIGN KEY (mno) REFERENCES member (mno) ON DELETE CASCADE ON UPDATE CASCADE;