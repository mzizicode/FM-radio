package com.wizardcode.customer;

import com.wizardcode.amqp.RabbitMQMessageProducer;
import com.wizardcode.client.fraud.FraudCheckResponse;
import com.wizardcode.client.fraud.FraudClient;
import com.wizardcode.client.notification.NotificationClient;
import com.wizardcode.client.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private  final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void regesterCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo check if email is valid
        //todo check if email is not taken
        customerRepository.saveAndFlush(customer);
        //todo to check if fraudster

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
        //todo to send notification
//
//        notificationClient.sendNotification(
//                new NotificationRequest(
//                        customer.getId(),
//                        customer.getEmail(),
//                        String.format("Hi %s, welcome to wizardcode...",
//                                customer.getFirstName())

       NotificationRequest notificationRequest = new NotificationRequest(
                       customer.getId(),
                         customer.getEmail(),
                            String.format("Hi %s, welcome to wizardcode...",
                              customer.getFirstName())
                );
        rabbitMQMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}

