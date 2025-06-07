package spectra.recruitment.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/consult")
@Controller
public class ConsultController {
    @GetMapping("/chats")
    public String list(){
        return "chat/list";
    }

    @GetMapping("/chats/{chatRoomId}")
    public String chat(@PathVariable("chatRoomId") String chatRoomId){
        return "chat/chat";
    }
}
