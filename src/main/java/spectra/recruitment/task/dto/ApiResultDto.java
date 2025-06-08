package spectra.recruitment.task.dto;

import lombok.Getter;
import lombok.Setter;
import spectra.recruitment.task.values.ErrorCode;

import java.util.HashMap;
import java.util.List;

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

    /**
     * 에러 데이터로 변환
     * - 특이사항 : result_data 초기화 코드 존재
     * @param errorCode 에러 코드
     * @param errors 에러 메시지 리스트
     */
    public void changeToError(ErrorCode errorCode, List<String> errors){
        this.type= TYPE_ERROR;
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data.clear();
        this.addResultData("errors", errors);
    }
}
