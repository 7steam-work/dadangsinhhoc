package site.dadangsinhhoc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "bo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoModel extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idLop")
    private LopModel lop;

    @Column(name = "name")
    private String name;

    @Column(name = "nameLatinh", nullable = false)
    private String nameLatinh;

    @Column(name = "loai", nullable = false)
    private Boolean loai;

    // @Column(name = "idLop", nullable = false)
    // private Integer idLop;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "updatedBy")
    private String updatedBy;
    }
