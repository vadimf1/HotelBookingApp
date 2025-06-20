package intexsoft.practice.notification_service.config;

import intexsoft.practice.dto.notification.AccountLoginNotification;
import intexsoft.practice.dto.notification.BookingCreatedNotification;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, AccountLoginNotification> accountLoginConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "intexsoft.practice.dto.notification");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "intexsoft.practice.dto.notification.AccountLoginNotification");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(AccountLoginNotification.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AccountLoginNotification> accountLoginKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AccountLoginNotification> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(accountLoginConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, BookingCreatedNotification> bookingConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "intexsoft.practice.dto.notification");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "intexsoft.practice.dto.notification.BookingCreatedNotification");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(BookingCreatedNotification.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookingCreatedNotification> bookingKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BookingCreatedNotification> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(bookingConsumerFactory());
        return factory;
    }
}

