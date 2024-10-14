package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.BannerModel;

@Repository
public interface BannerRepository extends JpaRepository<BannerModel, Long> {
}
