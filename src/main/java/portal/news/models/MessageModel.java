package portal.news.models;

import lombok.Data;

@Data
public class MessageModel {
    private String message;
    private String routingKey;
}
