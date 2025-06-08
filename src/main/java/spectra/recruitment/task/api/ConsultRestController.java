package spectra.recruitment.task.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spectra.recruitment.task.dto.*;
import spectra.recruitment.task.service.ConsultService;
import spectra.recruitment.task.values.ConstValue;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/consult")
public class ConsultRestController {
    private final ConsultService consultService;

    /**
     * 고객에 의한 채팅방 생성
     * @param req
     * @param customerDto
     * @return
     */
    @PostMapping("/customer")
    public ResponseEntity<ApiResultDto> createRoomByCustomer(HttpServletRequest req, @RequestBody CustomerDto customerDto){
        ApiResultDto result = new ApiResultDto();
        HttpSession session = req.getSession();

        // 회원 정보 및 채팅방 생성
        ChatInfoDto chatInfoDto = consultService.startConsultByCustomer(customerDto);

        // 세션 설정
        session.setAttribute(ConstValue.SESSION_INFO.getValue(), customerDto);

        // 정보 설정
        result.addResultData(ConstValue.CHAT_INFO.getValue(), chatInfoDto);

        return ResponseEntity.ok(result);
    }

    /**
     * 옵저버에 의한 채팅방 생성
     * @param req
     * @return
     */
    @PostMapping("/observer")
    public ResponseEntity<ApiResultDto> createRoomByObserver(HttpServletRequest req, @RequestBody ChatInfoDto dto){
        ApiResultDto result = new ApiResultDto();
        HttpSession session = req.getSession();

        UserDto userDto = (UserDto) session.getAttribute(ConstValue.SESSION_INFO.getValue());

        // 채팅방 생성
        ChatInfoDto chatInfoDto = consultService.startConsultByObserver(userDto, dto.getRefRoomId());

        // 정보 설정
        result.addResultData(ConstValue.CHAT_INFO.getValue(), chatInfoDto);

        return ResponseEntity.ok(result);
    }

    /**
     * 채팅방 목록 조회
     * @return
     */
    @GetMapping("/chats")
    public ResponseEntity<ApiResultDto> getRooms(){
        ApiResultDto result = new ApiResultDto();

        // 채팅방 목록 조회
        List<ConsultChatRoomDto> rooms = consultService.getRooms();

        // 정보 설정
        result.addResultData(ConstValue.LIST.getValue(), rooms);

        return ResponseEntity.ok(result);
    }

    /**
     * 채팅 목록 조회
     * @return
     */
    @GetMapping("/chats/{roomId}")
    public ResponseEntity<ApiResultDto> getChats(@PathVariable("roomId") Long roomId){
        ApiResultDto result = new ApiResultDto();

        // 채팅방 목록 조회
        ConsultChatInfoDto chatInfo = consultService.getChats(roomId);

        // 정보 설정
        result.addResultData(ConstValue.CHAT_INFO.getValue(), chatInfo);

        return ResponseEntity.ok(result);
    }

    /**
     * 상담 종료
     * @param req
     * @param consultEndDto 상담 종료 DTO
     * @return
     */
    @PostMapping("/end")
    public ResponseEntity<ApiResultDto> endConsult(HttpServletRequest req, @RequestBody ConsultEndDto consultEndDto){
        ApiResultDto result = new ApiResultDto();
        HttpSession session = req.getSession();

        UserDto userDto = (UserDto) session.getAttribute(ConstValue.SESSION_INFO.getValue());
        consultService.endConsult(consultEndDto, userDto);

        return ResponseEntity.ok(result);
    }
}
