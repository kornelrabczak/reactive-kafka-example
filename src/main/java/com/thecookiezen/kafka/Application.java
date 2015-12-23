package com.thecookiezen.kafka;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import com.softwaremill.react.kafka.ConsumerProperties;
import com.softwaremill.react.kafka.PropertiesBuilder;
import com.softwaremill.react.kafka.ReactiveKafka;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import org.reactivestreams.Publisher;

public class Application {

    private static final String zooKeeperHost = "localhost:2181";
    private static final String brokerList = "localhost:9092";
    private static final ActorSystem system = ActorSystem.create("ReactiveKafka");

    public static void main(String[] args) {
        ConsumerProperties<String> cp =
                new PropertiesBuilder.Consumer(brokerList, zooKeeperHost, "test", "groupId", new StringDecoder(null))
                        .build();

        ReactiveKafka kafka = new ReactiveKafka();
        Publisher<MessageAndMetadata<byte[], String>> publisher = kafka.consume(cp, system);
        ActorMaterializer materializer = ActorMaterializer.create(system);
        Source.from(publisher).map(msg -> msg.message()).to(Sink.foreach(System.out::println)).run(materializer);
    }
}
