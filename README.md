
### ping_pong_rabbitmq

* simple example of Rabbit MQ
* a message is sent from ping to pong (or vice-versa) until a max count is reached
* a message is a JSON serialization of a `Ball` object 
* uses Spring Boot and Spring RabbitMQ

### Docker Rabbit MQ server

* in Docker terminal:
    * `docker pull rabbitmq`
    * `docker run -d -p 5672:5672 -p 15672:15672  --name rabbitmq rabbitmq`

### To Run

* edit ~/src/main/resources/application.properties
    * set `spring.rabbitmq.host` to `$DOCKER_HOST` from Docker terminal
* in terminal 1, `./run_ping.sh`
* in terminal 2, `./run_pong.sh`
* in terminal 3, `./run_client.sh`
