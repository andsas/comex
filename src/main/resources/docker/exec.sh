#!/bin/bash

# ....................................................................................................
# Executions

export SERVER_PORT=8000
export DATABASE_HOST=localhost
export DATABASE_USER=comex
export DATABASE_PASSWORD=comex

docker compose up -d comex-service-db
while ! docker exec comex-service-db mysql --user=root --password=root -e "status" &> /dev/null; do sleep 1; echo -n . ; done; echo

mvn -f ../../../../ clean package && cp ../../../../target/comex.jar .
docker compose up -d comex-service --build
rm -rf comex.jar

# ....................................................................................................
# End Script