# Services for email subscriptions


## Description

The main frameworks:

* Spring Boot - to be able to run console/web application easily in the container.
* Spring Security - to provide authentication for connection between the public application and the private service.
Now I use basic authentication, but switching to OAuth 2.0 is also possible.
* Spring Data - used to communicate with the embedded H2 database.
The database can be easily switched to AWS Aurora database to provide the required SLA and data durability.
* Spring Kafka - to integrate with Apache Kafka.
* Apache Maven - to build the project, divide the project into modules. Use a Maven plugin to build docker images
* Docker compose - to set up all the containers, configure ports that need to be open

## Docker setup

In order to deploy Confluent Kafka in the machine, this project starts 3 Docker containers (ZooKeeper, Kafka, Kafka-Topics-UI).

* Build the main pom.xml
`mvn clean package -DskipTests`

### 2. Build the docker Images

#### kafka-docker-producer
```
cd ../kafka-docker-producer
mvn clean package docker:build -DskipTests
```

#### kafka-docker-consumer
```
cd ../kafka-docker-consumer
mvn clean package docker:build -DskipTests
```

#### kafka-docker-public-service
```
cd ../kafka-docker-public-service
mvn clean package docker:build -DskipTests
```

#### List the Docker images and check the new images by executing:
```
cd ..
docker images

REPOSITORY                                       TAG                 IMAGE ID            CREATED             SIZE
docker.example.com/kafka-docker-public-service   0.0.1-SNAPSHOT      c5ee40333323        19 seconds ago      140MB
docker.example.com/kafka-docker-public-service   latest              c5ee40333323        19 seconds ago      140MB
docker.example.com/kafka-docker-producer         0.0.1-SNAPSHOT      13b479eba783        17 minutes ago      155MB
docker.example.com/kafka-docker-producer         latest              13b479eba783        17 minutes ago      155MB
docker.example.com/kafka-docker-consumer         0.0.1-SNAPSHOT      4564d0549f54        32 minutes ago      127MB
docker.example.com/kafka-docker-consumer         latest              4564d0549f54        32 minutes ago      127MB
```

### 3. Create the containers
We are using Docker-Compose to start the containers. Go to the root folder where 'docker-compose.yml' is located and run the below command:
```
docker-compose up -d
```
Or to stop or restart:
```
docker-compose stop
docker-compose restart
```

[Optional] You can either open a separate terminal and follow the logs while systems are initializing:
```
docker-compose logs -f
```
[Optional] Or check the starting status:
```
docker-compose ps
```
### 4. Check the results

Access the swagger UI of the public app <http://localhost:8090/swagger-ui.html>.

Access <http://localhost:8085/> to see the topics and received data graphically.

### Removing the Docker images created:
```
docker images
docker rmi docker.example.com/kafka-docker-consumer:latest
[...]
```
