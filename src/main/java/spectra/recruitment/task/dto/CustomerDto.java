package spectra.recruitment.task.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 고객 정보 DTO
 */
@Getter
@Setter
public class CustomerDto {
    private Long sessionId;
    @NotEmpty(message ="필요한 데이터 중 [상담 유형]에 대한 값이 존재하지 않습니다.")
    private String consultType;
    private String role = "CUSTOMER";
}
