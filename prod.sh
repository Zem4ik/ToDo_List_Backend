#!/usr/bin/env bash

docker stop prod
docker rm prod
./gradlew docker
docker run -v /etc/letsencrypt/live/zem4ik.ru:/etc/letsencrypt/live/zem4ik.ru -e "SPRING_PROFILES_ACTIVE=prod" --name prod --network="host" -t -d ru.zem4ik.todo/spring-backend --server.port=443
