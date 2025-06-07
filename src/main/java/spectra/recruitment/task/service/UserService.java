package spectra.recruitment.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spectra.recruitment.task.dto.LoginDto;
import spectra.recruitment.task.dto.UserDto;
import spectra.recruitment.task.entity.User;
import spectra.recruitment.task.repository.UserRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * 로그인
     * @param loginDto 로그인용 객체
     * @return
     */
    public UserDto login(LoginDto loginDto) {
        String encodePw = "";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(loginDto.getLoginPw().getBytes());
            encodePw =  Base64.getEncoder().encodeToString(encodedHash);
        } catch (NoSuchAlgorithmException e){
            log.error(e.getMessage());
        }
        User user = userRepository.execLogin(loginDto.getLoginId(), encodePw);
        if(user == null){
            return null;
        }

        return UserDto.fromEntity(user);
    }
}
