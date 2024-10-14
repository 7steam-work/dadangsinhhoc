package site.dadangsinhhoc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.HoModel;

import java.util.List;

@Repository
public interface HoRepository extends JpaRepository<HoModel, Long> {
    List<HoModel> findAll(Specification<HoModel> spec);

    boolean existsByNameLatinh(String nameLatinh);

    @Query("SELECT h FROM HoModel h WHERE (:keyword IS NULL OR h.name LIKE %:keyword% OR h.nameLatinh LIKE %:keyword%) AND (:loai IS NULL OR h.loai = :loai)")
    Page<HoModel> searchByNameOrNameLatinh(@Param("keyword") String keyword, @Param("loai") Boolean loai, Pageable pageable);
//    @Query("SELECT h FROM HoModel h WHERE (:keyword IS NULL OR h.name LIKE %:keyword% OR h.nameLatinh LIKE %:keyword%) AND (:loai IS NULL OR h.loai = :loai)")
//    List<HoModel> searchByNameOrNameLatinh(@Param("keyword") String keyword, @Param("loai") Boolean loai);
}
