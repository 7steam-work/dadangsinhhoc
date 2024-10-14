package site.dadangsinhhoc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nganh")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NganhModel extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nameLatinh;
    private Boolean loai;
    private Boolean status;
    private String createdBy;
    private String updatedBy;

}
