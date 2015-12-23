# reactive-kafka-example

https://softwaremill.com/reactive-kafka/
https://github.com/softwaremill/reactive-kafka

# run

- download kafka
- start zookeeper : bin/zookeeper-server-start.sh config/zookeeper.properties
- start kafka : bin/zookeeper-server-start.sh config/zookeeper.properties
- create topic : bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
- build application : mvn clean package & java -jar target/reactive-kafka-example-1.0-SNAPSHOT-allinone.jar
- send some messages : bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
