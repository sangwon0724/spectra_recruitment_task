package spectra.recruitment.task.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spectra.recruitment.task.dto.ApiResultDto;
import spectra.recruitment.task.dto.LoginDto;
import spectra.recruitment.task.dto.UserDto;
import spectra.recruitment.task.error.LoginFailException;
import spectra.recruitment.task.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserRestController {
    private final UserService userService;
    
    @PostMapping("/login")
    public ApiResultDto login(HttpServletRequest req, @RequestBody LoginDto loginDto){
        HttpSession session = req.getSession();

        UserDto user = userService.login(loginDto);
        if(user == null){
            throw new LoginFailException();
        }

        session.setAttribute("sessionInfo", user);

        return new ApiResultDto();
    }
}
