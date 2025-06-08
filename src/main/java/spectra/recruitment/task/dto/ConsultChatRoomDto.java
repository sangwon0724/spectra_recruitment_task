package spectra.recruitment.task.dto;

import lombok.Getter;
import lombok.Setter;
import spectra.recruitment.task.values.ConsultType;

@Getter
@Setter
public class ConsultChatRoomDto {
    private String sessionId;
    private Long roomId;
    private String roomNm;
    private ConsultType roomType;
    private String useYn;
    private String lastMessage;
    private Long subRoomId;
    private String createdDate;

    public ConsultChatRoomDto(
        String sessionId,
        Long roomId,
        String roomNm,
        ConsultType roomType,
        String useYn,
        String lastMessage,
        Long subRoomId,
        String createdDate
    ) {
        this.sessionId = sessionId;
        this.roomId = roomId;
        this.roomNm = roomNm;
        this.roomType = roomType;
        this.useYn = useYn;
        this.lastMessage = lastMessage;
        this.subRoomId = subRoomId;
        this.createdDate = createdDate;
    }
}
