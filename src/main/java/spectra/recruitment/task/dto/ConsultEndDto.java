package spectra.recruitment.task.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 채팅 종료 DTO
 */
@Getter
@Setter
public class ConsultEndDto {
    @NotNull(message ="필요한 데이터 중 [메인 채팅방 고유번호]에 대한 값이 존재하지 않습니다.")
    private Long mainRoomId;
    private Long subRoomId;
    private String saveYn;
}
