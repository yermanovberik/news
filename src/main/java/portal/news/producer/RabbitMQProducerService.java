package portal.news.producer;

public interface RabbitMQProducerService {

    void sendMessage(String message, String routingKey);
}
