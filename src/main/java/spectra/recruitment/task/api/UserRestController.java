package spectra.recruitment.task.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spectra.recruitment.task.dto.ApiResultDto;
import spectra.recruitment.task.dto.LoginDto;
import spectra.recruitment.task.dto.UserDto;
import spectra.recruitment.task.error.LoginFailException;
import spectra.recruitment.task.service.UserService;
import spectra.recruitment.task.util.ApiUtil;
import spectra.recruitment.task.values.ErrorCode;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserRestController {
    private final UserService userService;

    /**
     * 로그인
     * @param req
     * @param loginDto
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResultDto> login(
            HttpServletRequest req,
            @Valid @RequestBody LoginDto loginDto,
            BindingResult bindingResult
    ){
        HttpSession session = req.getSession();
        ApiResultDto result = new ApiResultDto();

        List<String> error_list = ApiUtil.checkValidation(bindingResult);
        if(!error_list.isEmpty()){
            result.changeToError(ErrorCode.NEED_PARAM, error_list);
            return ResponseEntity.badRequest().body(result);
        }

        UserDto user = userService.login(loginDto);
        if(user == null){
            throw new LoginFailException();
        }

        session.setAttribute("sessionInfo", user);

        return ResponseEntity.ok(result);
    }
}
