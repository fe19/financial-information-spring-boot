CREATE TABLE COIN (
    ID     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME   VARCHAR(10) NOT NULL DEFAULT 'BTC',
    AMOUNT NUMBER NOT NULL DEFAULT 0,
    CURRENCY VARCHAR(3) NOT NULL DEFAULT 'USD',
    DATE DATE
);
