package site.dadangsinhhoc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ho")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoModel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idBo")
    private BoModel bo;

    @Column(name = "name")
    private String name;

    @Column(name = "nameLatinh", nullable = false)
    private String nameLatinh;

    @Column(name = "loai", nullable = false)
    private Boolean loai;

    // @Column(name = "idBo", nullable = false)
    // private Integer idBo;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "updatedBy")
    private String updatedBy;

}
