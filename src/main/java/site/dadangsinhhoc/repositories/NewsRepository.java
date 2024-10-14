package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.NewsModel;

@Repository
public interface NewsRepository extends JpaRepository<NewsModel, Long> {
}
