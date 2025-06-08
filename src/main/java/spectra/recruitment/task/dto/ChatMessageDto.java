package spectra.recruitment.task.dto;

import lombok.Getter;
import lombok.Setter;
import spectra.recruitment.task.entity.ConsultChat;
import spectra.recruitment.task.values.ChatType;

/**
 * 채팅 메시지 DTO
 */
@Getter
@Setter
public class ChatMessageDto {
    private Long sessionId;
    private Long roomId;
    private String content;
    private String role;
    private String target;
    private String type;

    public static ChatMessageDto entityToDto(ConsultChat entity){
        ChatMessageDto dto = new ChatMessageDto();
        dto.sessionId = entity.getChatBy();
        dto.content = entity.getContent();
        dto.role = entity.getRole();
        switch (entity.getChatType()){
            case MESSAGE:
                dto.type = "message";
                break;
            case EVENT:
                dto.type = "event";
                break;
            default:
                dto.type = "message";
                break;
        }
        dto.roomId = entity.getChatRoomId();
        return dto;
    }
}