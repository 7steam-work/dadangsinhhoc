package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.LopModel;
import site.dadangsinhhoc.models.NganhModel;

import java.util.List;

@Repository
public interface LopRepository extends JpaRepository<LopModel, Long> {
    List<LopModel> findAll(Specification<LopModel> spec);
}
