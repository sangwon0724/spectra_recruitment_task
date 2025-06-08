package spectra.recruitment.task.dto;

import lombok.Getter;
import lombok.Setter;
import spectra.recruitment.task.values.ErrorCode;

import java.util.HashMap;

/**
 * API 결과 반황용 DTO
 */
@Getter
@Setter
public class ApiResultDto {
    private static final String TYPE_SUCCESS = "success";
    private static final String TYPE_ERROR = "error";

    private String type = "success"; //(성공/실패)
    private String code = "0"; //결과 코드
    private String msg = "성공"; //결과 메시지
    private HashMap<String, Object> data = new HashMap<String, Object>(); //결과 데이터

    /**
     * 데이터 추가하기
     * @param key 키
     * @param value 값
     */
    public void addResultData(String key, Object value) {
        this.data.put(key, value);
    }

    /**
     * 에러 데이터로 변환
     * @param errorCode 에러 코드
     */
    public static ApiResultDto error(ErrorCode errorCode){
        ApiResultDto dto = new ApiResultDto();
        dto.setType(TYPE_ERROR);
        dto.setCode(errorCode.getCode());
        dto.setMsg(errorCode.getMsg());
        return dto;
    }
}
