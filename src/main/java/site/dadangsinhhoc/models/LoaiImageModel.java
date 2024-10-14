package site.dadangsinhhoc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loai_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoaiImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idLoai")
    private LoaiModel loai;

    @Column(name = "fileName")
    private String fileName;
}
