package spectra.recruitment.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", columnDefinition = "BIGINT UNSIGNED")
    @Comment("채팅방 PK")
    private ConsultChatRoom chatRoom;
}
