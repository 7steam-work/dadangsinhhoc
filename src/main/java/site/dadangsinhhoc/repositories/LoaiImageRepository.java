package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.LoaiImageModel;

import java.util.List;

@Repository
public interface LoaiImageRepository extends JpaRepository<LoaiImageModel, Long> {
    List<LoaiImageModel> findAll(Specification<LoaiImageModel> spec);
}
