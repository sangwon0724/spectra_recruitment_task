package spectra.recruitment.task.api;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import spectra.recruitment.task.dto.ChatMessage;

@RestController
@RequiredArgsConstructor
public class ChatRestController {
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/consult/chats/{roomId}")
    public void send(@DestinationVariable("roomId") String roomId, @Payload ChatMessage message) {
        messagingTemplate.convertAndSend("/sub/consult/chats/" + roomId, message);
    }
}
