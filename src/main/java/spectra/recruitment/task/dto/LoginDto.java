package spectra.recruitment.task.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 DTO
 */
@Getter
@Setter
public class LoginDto {
    @NotEmpty(message ="필요한 데이터 중 [아이디]에 대한 값이 존재하지 않습니다.")
    private String loginId;
    @NotEmpty(message ="필요한 데이터 중 [비밀번호]에 대한 값이 존재하지 않습니다.")
    private String loginPw;
}
