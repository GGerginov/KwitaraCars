package project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.demo.domain.entities.User;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.lang.annotation.Native;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getByUsernameAndPassword(String username,String password);

    User getByUsername(String username);

    User getById(String id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE User u SET u.username = :username where u.id = :id")
    void updateUserUsername(@Param("username") String username, @Param("id") String id);
}
