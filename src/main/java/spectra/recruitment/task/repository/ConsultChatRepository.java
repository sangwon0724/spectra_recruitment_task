package spectra.recruitment.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spectra.recruitment.task.entity.ConsultChat;

import java.util.List;

@Repository
public interface ConsultChatRepository extends JpaRepository<ConsultChat, Long> {
    @Query("select c from ConsultChat c where c.chatRoomId = :roomId order by c.createdDate")
    public List<ConsultChat> getChats(Long roomId);
}
