package spectra.recruitment.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/chat")
@Controller
public class ChatController {
    @GetMapping("/list")
    public String list(){
        return "chat/list";
    }

    @GetMapping("/{chatId}")
    public String chat(@PathVariable("chatId") String chatId){
        return "chat/chat";
    }
}
