package portal.news.configurations.rabbit.consumer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.*;
import portal.news.services.implementation.EmailService;

import javax.mail.MessagingException;

@Component
@EnableRabbit
public class RabbitMQConsumer {

    private final static String QUEUE_NAME = "news_queue";

    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setPort(15672);  // Default RabbitMQ port
            factory.setUsername("user");  // Replace with your RabbitMQ username
            factory.setPassword("password");  // Replace with your RabbitMQ password

            try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    String[] parts = message.split("\\|");
                    String userEmail = parts[0];
                    String notificationText = parts[1];

                    EmailService emailService = new EmailService();
                    try {
                        emailService.sendEmail(userEmail, "New News Notification", notificationText);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                };

                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
