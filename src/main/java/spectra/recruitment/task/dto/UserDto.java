package spectra.recruitment.task.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import spectra.recruitment.task.entity.User;

/**
 * 사용자 DTO
 */
@Slf4j
@Getter
@Setter
public class UserDto {
    private Long sessionId;
    private String name;
    private String mobile;
    private String officeCallNo;
    private String role;

    public static UserDto fromEntity(User entity) {
        UserDto dto = new UserDto();
        dto.sessionId = entity.getId();
        dto.name = entity.getName();
        dto.mobile = entity.getMobile();
        dto.officeCallNo = entity.getOfficeCallNo();
        dto.role = entity.getRole().name();
        return dto;
    }
}
