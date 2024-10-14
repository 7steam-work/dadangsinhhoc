package site.dadangsinhhoc.repositories;

import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByEmail(String email);
    UserModel findByName(String userName);
    UserModel findByPhone(String phone);
    UserModel findById(Long id);
    List<UserModel> findByRole(String role);

    boolean existsByEmail(String email);
    boolean existsByName(String userName);
    boolean existsByPhone(String phone);
}