package spectra.recruitment.task.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import spectra.recruitment.task.dto.ChatMessageDto;
import spectra.recruitment.task.values.ChatType;

@Slf4j
@NoArgsConstructor
@Getter
@Table(name = "tb_consult_chat")
@Entity
@Comment("상담 > 채팅")
public class ConsultChat extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consult_chat_id", columnDefinition = "BIGINT UNSIGNED")
    @Comment("PK")
    private Long id;
    
    @Column(name = "chat_by", columnDefinition = "BIGINT UNSIGNED")
    @Comment("발언자")
    private Long chatBy;

    @Column(length = 500, nullable = false)
    @Comment("내용")
    private String content;

    @Column(length = 30, nullable = false)
    @Comment("유형")
    private ChatType chatType;

    @Column(length = 30, nullable = false)
    @Comment("채팅방 생성자의 권한")
    private String role;

    @Column(name = "chat_room_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    @Comment("채팅방 PK")
    private Long chatRoomId;

    //@JsonIgnore
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "chat_room_id", columnDefinition = "BIGINT UNSIGNED")
    //@Comment("채팅방 PK")
    //private ConsultChatRoom chatRoom;


    public static ConsultChat dtoToEntity(ChatMessageDto dto){
        ConsultChat entity = new ConsultChat();
        entity.chatBy = dto.getSessionId();
        entity.content = dto.getContent();
        entity.role = dto.getRole();
        switch (dto.getType()){
            case "message":
                entity.chatType = ChatType.MESSAGE;
                break;
            case "event":
                entity.chatType = ChatType.EVENT;
                break;
            default:
                entity.chatType = ChatType.MESSAGE;
                break;
        }
        entity.chatRoomId = dto.getRoomId();
        return entity;
    }
}
