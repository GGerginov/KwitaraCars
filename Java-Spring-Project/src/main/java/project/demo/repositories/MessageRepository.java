package project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.demo.domain.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,String> {


}
