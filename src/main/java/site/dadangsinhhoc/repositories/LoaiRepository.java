package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.LoaiModel;

import java.util.List;

@Repository
public interface LoaiRepository extends JpaRepository<LoaiModel, Long> {
    List<LoaiModel> findAll(Specification<LoaiModel> spec);
}
