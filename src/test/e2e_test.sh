#! /bin/bash

# Register a user with all info except profilePicture
curl -X POST -H "Content-Type: application/json" \
  --data '{"firstname": "Florian", "lastname": "Geckeler","password": "1234",
  "email": "flo@gec.de", "sports": ["KITESURFING", "WINGFOILING"], "userGroup": "SCHOOL"}' \
  http://localhost:8080/api/auth/register

# Login User
token=$(curl -X POST -H "Content-Type: application/json" \
  --data '{"password": "1234", "email": "flo@gec.de"}' \
  http://localhost:8080/api/auth/login | jq -r '.token')

# Update Users name
curl -X PUT -H "Authorization: Bearer $token" \
  -H "Content-Type: application/json" \
  --data '{"firstname": "Duderino"}' \
  http://localhost:8080/api/users/XXX