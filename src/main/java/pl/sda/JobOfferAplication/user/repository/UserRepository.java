package pl.sda.JobOfferAplication.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.sda.JobOfferAplication.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsUserEntitiesByLogin(String login);
}
