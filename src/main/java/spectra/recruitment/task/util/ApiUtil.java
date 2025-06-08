package spectra.recruitment.task.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ApiUtil {

    /**
     * 유효성 검사
     * @param bindingResult
     * @return 유효성 검사 결과 메시지 목록이 담긴 리스트
     */
    public static List<String> checkValidation(BindingResult bindingResult) {
        List<String> error_list = new ArrayList<>(); //결과 반환용

        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors(); //에러 목록

            for (ObjectError error : errorList) {
                error_list.add(error.getDefaultMessage());
            }
        }

        return error_list;
    }
}
