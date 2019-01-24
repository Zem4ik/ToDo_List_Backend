#!/usr/bin/env bash

docker stop prod
docker rm prod
./gradlew docker
docker run --name prod --network="host" -t -d ru.zem4ik.todo/spring-backend --server.port=8483