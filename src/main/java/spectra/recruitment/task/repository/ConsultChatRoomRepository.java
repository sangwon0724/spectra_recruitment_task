package spectra.recruitment.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spectra.recruitment.task.entity.ConsultChatRoom;

@Repository
public interface ConsultChatRoomRepository extends JpaRepository<ConsultChatRoom, Long> {
}
