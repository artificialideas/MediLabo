#!/bin/bash
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P ${SA_PASSWORD} -i /docker-entrypoint-initdb.d/init-mssql.sql