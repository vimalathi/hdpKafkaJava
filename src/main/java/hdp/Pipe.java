package hdp;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.StreamsBuilder;

import java.util.Properties;

public class Pipe {
    public static void main(String args[]) throws Exception{
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "hdp-kafka-stream");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        final SteamsBuilder builder = new StreamsBuider();
        KStream<String, String> source = builder.stream("confluentKafkaTopic");
        source.to("confluentKafkaTopicOutput");
        //builder.stream("streams-plaintext-input").to("streams-pipe-output");

        final Topology topology = builder.build();
        System.out.println(topology.describe());

    }
}
