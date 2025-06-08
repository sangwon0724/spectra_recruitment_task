package spectra.recruitment.task.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 채팅 종료 DTO
 */
@Getter
@Setter
public class ConsultEndDto {
    private Long mainRoomId;
    private Long subRoomId;
    private String saveYn;
}
