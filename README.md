# casspring
A dead simple REST API using spring boot. 

## Setup
Run postgres instance in Docker
```bash
docker run --name postgres-spring -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine
```

Your docker container port will be exposed to port `5432`. To verify, `docker port postgres-spring`

Go inside the postgres container
```bash
$ docker exec -it $(docker ps -aqf "name=postgres-spring") bash
```

Create database inside the Postgres container
```bash
bash-5.0# psql -U postgres
postgres=# CREATE DATABASE casspring; 
```

### How to run
1. Package with maven
   ```bash
   $ mvn package
   ```

1. Run the jar file
    ```bash
    $ java -jar target/casspring-0.0.1-SNAPSHOT.jar
    ```
1. Application runs at port 8080

### API Endpoints

1. GET
    ```bash
    $ curl -XGET localhost:8080/api/v1/person
      [{"id":"43ce8819-9ac2-4575-9903-ffc0872cd1f2","name":"Ms. Bar"},{"id":"72dde85b-ca22-4966-9aa8-9c9fa6e17ca3","name":"James Bond 123"}]
    ```

1. GET one item
    ```bash
    $ curl -XGET localhost:8080/api/v1/person/43ce8819-9ac2-4575-9903-ffc0872cd1f2
        {"id":"43ce8819-9ac2-4575-9903-ffc0872cd1f2","name":"Ms. Bar"}
    ```

1. POST
    ```bash
    $ curl --location --request POST 'localhost:8080/api/v1/person' --header 'Content-Type: application/json' --data-raw '{ "name": "James Bond 007" }'
    ```
   
1. DELETE
    ```bash
    $ curl --location --request DELETE 'localhost:8080/api/v1/person/466767ca-151b-4d9d-a749-659904d02415'
    ```
   
1. PUT
    ```bash
    $ curl --location --request PUT 'localhost:8080/api/v1/person/72dde85b-ca22-4966-9aa8-9c9fa6e17ca3' --header 'Content-Type: application/json' --data-raw '{ "name": "James Bond 456" }'
    ```
