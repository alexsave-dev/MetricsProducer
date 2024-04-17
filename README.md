# MetricsProducer
This is the demo application.


## Getting started
Install the docker service on your machine.

From the command prompt/console go to the directory metrics_producer in project root and then run the command:
docker-compose up -d

Two containers for postgres and kafka will be up and running as a result.
Kafka UI can be launched from http://localhost:3040

Run the service MetricProducerApplication

## API
POST localhost:8080/metrics - sends the metric to Kafka topic.

The example of the body request:
```json
{
  "processorUsagePercent": 20.11,
  "memoryUsagePercent": 56.99,
  "diskUsagePercent": 71.33
}
```

## Properties
kafka.topic.name=metrics-topic - contains the name of Kafka topic

