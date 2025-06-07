package spectra.recruitment.task.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spectra.recruitment.task.dto.ApiResultDto;
import spectra.recruitment.task.dto.CustomerDto;
import spectra.recruitment.task.entity.Customer;
import spectra.recruitment.task.service.ConsultService;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/consult")
public class ConsultRestController {
    private final ConsultService consultService;

    @PostMapping("/customer")
    public ResponseEntity consultCustomer(HttpServletRequest req, @RequestBody CustomerDto customerDto){
        ApiResultDto result = new ApiResultDto();
        HttpSession session = req.getSession();

        // 회원 정보 및 채팅방 생성
        consultService.startConsultByCustomer(customerDto);

        // 세션 설정
        session.setAttribute("sessionInfo", customerDto);

        // 채팅방 주소 설정
        customerDto.setRoomUrl("/consult/chats/" +   customerDto.getRoomId());

        result.addResultData("chatInfo", customerDto);

        return ResponseEntity.ok(result);
    }
}
