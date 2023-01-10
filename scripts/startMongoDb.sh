#!/bin/bash

docker run -d --name mongodb \
      -e MONGO_INITDB_ROOT_USERNAME=admin \
      -e MONGO_INITDB_ROOT_PASSWORD=testor \
      -p 27017:27017 \
      mongo
