In Hexagonal architecture, input ports (primary Adaptors) are interfaces that are implemented in the domain layer and used by clients of the domain layer

In Hexagonal architecture, output ports (Secondary Adaptors) are interfaces that are implemented in the infrastructure layer (data access, message modules) and used by the domain layer to reach the infrastructure layers

Note:
- OrderApplicationService - Will be used by client of the application

- RestaurantApprovalResponseMessageListener - message listeners for restaurant approvals
  - DomainEvent Listeners are special type of application services and they are triggered by domain events, not by the clients


### Kafka
#### First run zookeeper 
>docker-compose -f common.yml -f zookeper.yml up

#### Check health of zookeeper by rouk command [Are you ok?]
>echo ruok | nc localhost 2181
  - It should return `imok` text if it is healthy [I am OK]

#### Run kafka cluster
>docker-compose -f common.yml -f kafka_cluster.yml up

#### Create Kafka Topics
> docker-compose -f common.yml -f init_kafka.yml up

#### Open Kafka Manager UI
> http://localhost:9000
- Add cluster manually
  - cluster name: food-ordering-system-cluster
  - cluster zookeeper hosts: zookeeper:2181
  - save. Go to cluster view

### In Kubernetes, we will replace docker-compose with CP Helm Charts to run kafka cluster

### Kafka Modules
- kafka-config-data - is used to configure to connect to Kafka cluster and to configure producer and consumer
- kafka-model - Create classes with AVRO schema types
  - Click on maven clean install to generate avro java classes
  - If you see compile errors, just reload maven project so Intellij knows they exist

#### Why do we use a callback in KafkaProducerImpl - Send() method?
Because the send methods on Kafka producer is a non-blocking asynchronous call, so it will not return results immediately.
Instead, it requires a callback methods to be called later asynchronously.



