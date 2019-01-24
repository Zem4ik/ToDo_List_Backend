#!/usr/bin/env bash

docker stop test
docker rm test
./gradlew docker
docker run -e "SPRING_PROFILES_ACTIVE=dev" --name test --network="host" -t -d ru.zem4ik.todo/spring-backend --server.port=8081