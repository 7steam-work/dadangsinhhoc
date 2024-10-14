package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.IntroduceModel;

@Repository
public interface IntroduceRepository extends JpaRepository<IntroduceModel, Long> {
}
