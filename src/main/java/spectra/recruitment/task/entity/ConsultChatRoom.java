package spectra.recruitment.task.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import spectra.recruitment.task.values.ConsultType;
import spectra.recruitment.task.values.UserRole;

@Slf4j
@NoArgsConstructor
@Getter
@Table(name = "tb_consult_chat_room")
@Entity
@Comment("상담 > 채팅 방")
public class ConsultChatRoom extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consult_chat_room_id", columnDefinition = "BIGINT UNSIGNED")
    @Comment("PK")
    private Long id;

    @Column(length = 30, nullable = false)
    @Comment("유형")
    private ConsultType consultType;

    @Column(length = 30, nullable = false)
    @Comment("채팅방 생성자의 권한")
    private String role;

    @Column(name = "use_yn", length = 1, nullable = false)
    @ColumnDefault("'Y'")
    @Comment("사용 여부")
    private String useYn; //사용 여부

    @Column(name = "ref_room_id", columnDefinition = "BIGINT UNSIGNED")
    @Comment("참조한 채팅방 PK")
    private Long refRoomId;

    //@OneToMany(mappedBy = "chatRoom")
    //private List<ConsultChat> chatList = new ArrayList<>();

    /**
     * 고객에 의한 채팅방 생성
     * @return
     */
    public static ConsultChatRoom createByCustomer(){
        ConsultChatRoom consultChatRoom = new ConsultChatRoom();
        consultChatRoom.consultType = ConsultType.COUNSEL;
        consultChatRoom.useYn = "Y";
        consultChatRoom.role = "CUSTOMER";
        return consultChatRoom;
    }

    /**
     * 옵저버 의한 채팅방 생성
     * @param refRoomId 참조하는 채팅방의 PK
     * @return
     */
    public static ConsultChatRoom createByObserver(Long refRoomId){
        ConsultChatRoom consultChatRoom = new ConsultChatRoom();
        consultChatRoom.consultType = ConsultType.OBSERVE;
        consultChatRoom.useYn = "Y";
        consultChatRoom.role = UserRole.OBSERVER.name();
        consultChatRoom.refRoomId = refRoomId;
        return consultChatRoom;
    }

    /**
     * 채팅방 종료
     */
    public void end(){
        this.useYn = "N";
    }
}
