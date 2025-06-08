package spectra.recruitment.task.api;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import spectra.recruitment.task.dto.ChatMessageDto;
import spectra.recruitment.task.service.ConsultService;

@RestController
@RequiredArgsConstructor
public class ChatRestController {
    private final ConsultService consultService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/consult/chats/{roomId}")
    public void send(@DestinationVariable("roomId") String roomId, @Payload ChatMessageDto message) {
        consultService.saveChat(message);
        messagingTemplate.convertAndSend("/sub/consult/chats/" + roomId, message);
    }
}
