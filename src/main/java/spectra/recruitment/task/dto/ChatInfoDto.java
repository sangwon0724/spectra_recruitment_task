package spectra.recruitment.task.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 채팅 정보 반환용 DTO
 */
@Getter
@Setter
public class ChatInfoDto {
    private String consultType;
    private Long roomId;
    private String roomUrl;
    private Long refRoomId;
}
