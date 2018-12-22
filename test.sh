#!/usr/bin/env bash

docker stop test
docker rm test
./gradlew docker
docker run -p 8081:8080 --name test -t -d ru.zem4ik.todo/spring-backend