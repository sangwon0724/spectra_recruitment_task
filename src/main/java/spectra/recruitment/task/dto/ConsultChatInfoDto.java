package spectra.recruitment.task.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 채팅 정보 반환용 DTO
 */
@NoArgsConstructor
@Getter
@Setter
public class ConsultChatInfoDto {
    private Long roomId;
    private Long refRoomId;
    private String roomNm;
    private String useYn;
    private List<ChatMessageDto> mainChats = new ArrayList<>();
    private List<ChatMessageDto> subChats = new ArrayList<>();

    public ConsultChatInfoDto(
        Long roomId,
        Long refRoomId,
        String roomNm,
        String useYn
    ){
        this.roomId = roomId;
        this.refRoomId = refRoomId;
        this.roomNm = roomNm;
        this.useYn = useYn;
    }
}
