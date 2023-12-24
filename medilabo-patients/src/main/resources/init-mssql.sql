-- init-mssql.sql
USE master;
GO

-- Create database
CREATE DATABASE ${MSSQL_DB};
GO

USE ${MSSQL_DB};
GO

-- Retry mechanism with delay
:retry
WAITFOR DELAY '00:00:10'; -- Wait for 10 seconds
IF (DB_ID('${MSSQL_DB}') IS NULL)
    GOTO retry;

-- Create a dedicated user for medilabo-patients using environment variables
DECLARE @appUserLogin NVARCHAR(100) = '$(SPRING_DATASOURCE_USERNAME)';
DECLARE @appUserPassword NVARCHAR(100) = '$(SPRING_DATASOURCE_PASSWORD)';

CREATE LOGIN @appUserLogin WITH PASSWORD = @appUserPassword;
CREATE USER @appUserLogin FOR LOGIN @appUserLogin;

-- Grant necessary permissions
GRANT CONNECT SQL TO @appUserLogin;
GRANT SELECT, INSERT, UPDATE, DELETE ON SCHEMA::dbo TO @appUserLogin;

BEGIN
    CREATE TABLE IF NOT EXISTS patients (
        id_patient              UNIQUEIDENTIFIER DEFAULT NEWID() NOT NULL,
        first_name              VARCHAR(20) NOT NULL,
        last_name               VARCHAR(50) NOT NULL,
        birthdate               DATE NOT NULL,
        gender                  VARCHAR(1) NOT NULL,
        phone_number            VARCHAR(12) NOT NULL,
        address                 VARCHAR(255) NOT NULL,
        CONSTRAINT pk_patient PRIMARY KEY (id_patient)
    );

    -- Patients
    INSERT [dbo].[patients] ([id_patient], [first_name], [last_name], [birthdate], [gender], [address], [phone_number]) VALUES ('85c4901e-5ee7-43d9-a126-8a45835ad91f', 'Test', 'TestNone', '1966-12-31', 'F', '1 Brookside St', '100-222-3333');
    INSERT [dbo].[patients] ([id_patient], [first_name], [last_name], [birthdate], [gender], [address], [phone_number]) VALUES ('5f71a9a7-8411-4af1-bb5e-b101a9c0fd72', 'Test', 'TestBorderline', '1945-06-24', 'M', '2 High St', '200-333-4444');
    INSERT [dbo].[patients] ([id_patient], [first_name], [last_name], [birthdate], [gender], [address], [phone_number]) VALUES ('83fc61fd-7143-44a8-9445-e0329e50a1eb', 'Test', 'TestInDanger', '2004-06-18', 'M', '3 Club Road', '300-444-5555');
    INSERT [dbo].[patients] ([id_patient], [first_name], [last_name], [birthdate], [gender], [address], [phone_number]) VALUES ('fc75ee91-3e8e-4de9-b4e2-f39556cd1dd2', 'Test', 'TestEarlyOnset', '2002-06-2', 'M', '4 Valley Dr', '400-555-6666');
END
GO