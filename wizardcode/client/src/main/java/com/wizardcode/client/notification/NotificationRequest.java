package com.wizardcode.client.notification;

public record NotificationRequest
        (
         Integer toCustomerId,
         String toCustomerName,
         String message
        )
    {
}
