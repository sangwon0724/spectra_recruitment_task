package spectra.recruitment.task.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spectra.recruitment.task.dto.*;
import spectra.recruitment.task.service.ConsultService;
import spectra.recruitment.task.util.ApiUtil;
import spectra.recruitment.task.values.ConstValue;
import spectra.recruitment.task.values.ErrorCode;

import java.util.ArrayList;
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
     * @param bindingResult
     * @return
     */
    @PostMapping("/customer")
    public ResponseEntity<ApiResultDto> createRoomByCustomer(
            HttpServletRequest req,
            @Valid @RequestBody CustomerDto customerDto,
            BindingResult bindingResult
    ){
        ApiResultDto result = new ApiResultDto();
        HttpSession session = req.getSession();

        List<String> error_list = ApiUtil.checkValidation(bindingResult);
        if(!error_list.isEmpty()){
            result.changeToError(ErrorCode.NEED_PARAM, error_list);
            return ResponseEntity.badRequest().body(result);
        }

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

        List<String> error_list = new ArrayList<>();
        if(dto.getRefRoomId() == null){
            error_list.add("필요한 데이터 중 [참조하는 채팅방 고유번호]에 대한 값이 존재하지 않습니다.");
        }
        if(!error_list.isEmpty()){
            result.changeToError(ErrorCode.NEED_PARAM, error_list);
            return ResponseEntity.badRequest().body(result);
        }

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
     * 채팅 상세 조회
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
     * @param bindingResult
     * @return
     */
    @PostMapping("/end")
    public ResponseEntity<ApiResultDto> endConsult(
            HttpServletRequest req,
            @Valid @RequestBody ConsultEndDto consultEndDto,
            BindingResult bindingResult
    ){
        ApiResultDto result = new ApiResultDto();
        HttpSession session = req.getSession();

        List<String> error_list = ApiUtil.checkValidation(bindingResult);
        if(!error_list.isEmpty()){
            result.changeToError(ErrorCode.NEED_PARAM, error_list);
            return ResponseEntity.badRequest().body(result);
        }

        UserDto userDto = (UserDto) session.getAttribute(ConstValue.SESSION_INFO.getValue());
        consultService.endConsult(consultEndDto, userDto);

        return ResponseEntity.ok(result);
    }
}
