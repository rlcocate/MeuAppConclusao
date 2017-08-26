CREATE TABLE Login (
    id      INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    user    VARCHAR(9)  NULL,
    fblogin VARCHAR(50) NULL, -- e-mail / telefone
    pass    VARCHAR(15) NULL
);