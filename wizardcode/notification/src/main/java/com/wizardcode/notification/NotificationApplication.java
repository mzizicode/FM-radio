package com.wizardcode.notification;

import com.wizardcode.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.wizardcode.notification",
                "com.wizardcode.amqp"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class,args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer rabbitMQMessageProducer,
//                                        NotificationConfig notificationConfig){
//
//        return args -> {
//            rabbitMQMessageProducer.publish(new Person ( "peter" ,25),
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey());
//        };
//
//    }
//
//    record Person ( String name ,int age){}
}
