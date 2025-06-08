package spectra.recruitment.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spectra.recruitment.task.dto.ConsultChatInfoDto;
import spectra.recruitment.task.dto.ConsultChatRoomDto;
import spectra.recruitment.task.entity.ConsultChatRoom;
import spectra.recruitment.task.values.ConsultType;

import java.util.List;

@Repository
public interface ConsultChatRoomRepository extends JpaRepository<ConsultChatRoom, Long> {
    @Query("select new spectra.recruitment.task.dto.ConsultChatRoomDto(" +
                "cr.createdBy," +
                "cr.id," +
                "c.consultType," +
                "cr.consultType," +
                "cr.useYn," +
                "( select c.content from ConsultChat c where c.chatRoomId = cr.id order by c.createdDate desc limit 1 )," +
                "( select cr2.id from ConsultChatRoom cr2 where cr.id = cr2.refRoomId order by cr.createdDate desc limit 1)," +
                "str(formatdatetime(cr.createdDate, 'yyyy-MM-dd'))" +
            ") " +
            "from ConsultChatRoom cr " +
            "join Customer c " +
            "on cr.createdBy = str(c.id) " +
            "where cr.consultType = :roomType " +
            "order by cr.createdDate desc")
    public List<ConsultChatRoomDto> getChatRooms(ConsultType roomType);

    @Query("select new spectra.recruitment.task.dto.ConsultChatInfoDto(" +
                "cr.id," +
                "( select cr2.id from ConsultChatRoom cr2 where cr.id = cr2.refRoomId order by cr.createdDate desc limit 1)," +
                "c.consultType," +
                "cr.useYn" +
            ") " +
            "from ConsultChatRoom cr " +
            "join Customer c " +
            "on cr.createdBy = str(c.id) " +
            "where cr.id = :roomId " +
            "order by cr.createdDate desc")
    public ConsultChatInfoDto getChatRoom(Long roomId);
}
