package com.arunreddypadala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

  public static void main(String[] args) {

    SpringApplication.run(NotificationServiceApplication.class, args);

  }

  @KafkaListener(topics = "notificationTopic")
  public void handleNotification(OrderPlacedEvent orderPlacedEvent) {

    //Send an email notification logic to be added here
    log.info("Received notification for order - {} ", orderPlacedEvent.getOrderNumber());

  }




}
