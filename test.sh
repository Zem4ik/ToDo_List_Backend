#!/usr/bin/env bash

docker stop test
docker rm test
./gradlew docker
docker run --name test --network="host" -t -d ru.zem4ik.todo/spring-backend --server.port=8081