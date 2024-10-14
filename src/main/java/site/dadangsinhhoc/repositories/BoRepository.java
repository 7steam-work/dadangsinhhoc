package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.BoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface BoRepository extends JpaRepository<BoModel, Long> {
    List<BoModel> findAll(Specification<BoModel> spec);
}
