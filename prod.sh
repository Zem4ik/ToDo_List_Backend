#!/usr/bin/env bash

docker stop prod
docker rm prod
./gradlew docker
docker run -p 80:8080 --name prod -t -d ru.zem4ik.todo/spring-backend