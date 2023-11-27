package portal.news.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "notificationText")
    private String notificationText;

    @Column(name = "user_email")
    private String userEmail;


    @Column(name = "notificationCreatedDate")
    private LocalDateTime notificationCreatedDate;

}
