package site.dadangsinhhoc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.LopModel;
import site.dadangsinhhoc.models.MessageModel;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Long> {
}
