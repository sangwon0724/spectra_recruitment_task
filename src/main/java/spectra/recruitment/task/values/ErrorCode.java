package spectra.recruitment.task.values;

import lombok.Getter;

@Getter
public enum ErrorCode {
    LOGIN_FAIL("E01", "로그인 정보를 가져오는데 실패하였습니다.\n아이디나 비밀번호를 확인해주세요."),
    ERROR("10", "오류가 발생했습니다."),
    ERROR_404("404", "404, Not Found."),
    ERROR_405("405", "405, Method not allowed.");


    private String code = "";
    private String msg = "";

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
