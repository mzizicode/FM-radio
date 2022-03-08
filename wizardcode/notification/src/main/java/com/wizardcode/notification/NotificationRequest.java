package com.wizardcode.notification;

public record NotificationRequest
        (
         Integer toCustomerId,
         String toCustomerName,
         String message
        )
    {
}
