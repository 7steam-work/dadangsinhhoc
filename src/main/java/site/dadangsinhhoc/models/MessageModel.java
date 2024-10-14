package site.dadangsinhhoc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "message")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "rPH", nullable = false)
    private Boolean rPH;

    @Column(name = "content", nullable = false, length = 360)
    private String content;

    @Column(name = "status", nullable = false)
    private Boolean status = false;

    @Column(name = "note")
    private String note;

}
