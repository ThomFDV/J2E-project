# J2E-Project

Welcome !

This repo host a project we had to do at school.

This project aims to make us learn how to build an API using Java Spring in a real world context.

## Run the project

To run the project, first clone it or pull the changes.

Then at the root of the project you can run :
```BASH
docker-compose up --build
```
Then you will be able to navigate to http://localhost:4201 to se the frontend of the app !

## Run tests

Ensure that your are at the root of the project.

To run the frontend unit tests :
```BASH
docker-compose exec client ng test --watch=false
```

To run the frontend e2e tests :
```BASH
docker-compose exec client ng e2e --port 4202
```

Thank You !