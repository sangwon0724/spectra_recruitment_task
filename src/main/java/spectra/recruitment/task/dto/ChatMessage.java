package spectra.recruitment.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String content;
    private String role;
    private String target;
    private String type;
}