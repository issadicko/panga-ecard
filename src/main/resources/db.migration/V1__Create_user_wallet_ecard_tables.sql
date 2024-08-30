-- V1__Create_user_wallet_ecard_tables.sql

-- Create User table
CREATE TABLE "user" (
                        uuid UUID PRIMARY KEY,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        firstname VARCHAR(255) NOT NULL,
                        lastname VARCHAR(255) NOT NULL
);

-- Create indexes for User table
CREATE INDEX idx_user_username ON "user" (username);
CREATE INDEX idx_user_email ON "user" (email);
CREATE INDEX idx_user_lastname_firstname ON "user" (lastname, firstname);

-- Create Wallet table
CREATE TABLE wallet (
                        uuid UUID PRIMARY KEY,
                        balance NUMERIC(19, 4) NOT NULL,
                        owner_uuid UUID NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        CONSTRAINT fk_wallet_user FOREIGN KEY (owner_uuid) REFERENCES "user" (uuid)
);

-- Create indexes for Wallet table
CREATE INDEX idx_wallet_owner_uuid ON wallet (owner_uuid);
CREATE INDEX idx_wallet_created_at ON wallet (created_at);

-- Create Ecard table
CREATE TABLE ecard (
                       uuid UUID PRIMARY KEY,
                       issued_at TIMESTAMP NOT NULL,
                       valid_until TIMESTAMP NOT NULL,
                       balance NUMERIC(19, 4) NOT NULL
);

-- Create indexes for Ecard table
CREATE INDEX idx_ecard_issued_at ON ecard (issued_at);
CREATE INDEX idx_ecard_valid_until ON ecard (valid_until);
CREATE INDEX idx_ecard_balance ON ecard (balance);