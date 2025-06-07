package spectra.recruitment.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Long sessionId;
    private String consultType;
    private Long roomId;
    private String roomUrl;
    private String role = "CUSTOMER";
}
