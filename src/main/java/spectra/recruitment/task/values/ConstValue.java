package spectra.recruitment.task.values;

import lombok.Getter;

@Getter
public enum ConstValue {
    /**
     * 세션 정보
     */
    SESSION_INFO("sessionInfo"),
    /**
     * 채팅 정보
     */
    CHAT_INFO("chatInfo"),
    /**
     * 목록형 데이터
     */
    LIST("list");

    private String value = "";

    ConstValue(String value) {
        this.value = value;
    }
}
