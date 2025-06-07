package spectra.recruitment.task.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import spectra.recruitment.task.dto.ApiResultDto;
import spectra.recruitment.task.error.LoginFailException;
import spectra.recruitment.task.values.ErrorCode;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class ApiAdvice {

    /**
     * 로그인에 실패한 경우
     */
    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity<ApiResultDto> handelLoginFailException(Exception e) {
        log.error("[ApiAdvice] LoginFailException");
        return ResponseEntity
                .badRequest()
                .body(ApiResultDto.error(ErrorCode.LOGIN_FAIL));
    }

    /**
     * 404, Not Found.에 대한 처리
     */
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    //@ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResultDto> handle404(HttpServletRequest request, HttpServletResponse response, NoResourceFoundException exception) throws IOException {
        log.error("[ApiAdvice] 404, Not Found.");
        if(!request.getServletPath().startsWith("/api")){
            response.sendRedirect("/error");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResultDto.error(ErrorCode.ERROR_404));
    }

    /**
     * 405, Method not allowed.에 대한 처리
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResultDto> handle405(HttpServletRequest request, HttpServletResponse response, HttpRequestMethodNotSupportedException exception) throws IOException {
        log.error("[ApiAdvice] 405, Method not allowed.");
        if(!request.getServletPath().startsWith("/api")){
            response.sendRedirect("/error");
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiResultDto.error(ErrorCode.ERROR_405));
    }

    /**
     * 예외 공통 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResultDto> handelException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        log.error("[ApiAdvice] handelException");
        if(!request.getServletPath().startsWith("/api")){
            response.sendRedirect("/error");
        }
        return ResponseEntity
                .internalServerError()
                .body(ApiResultDto.error(ErrorCode.ERROR));
    }
}
