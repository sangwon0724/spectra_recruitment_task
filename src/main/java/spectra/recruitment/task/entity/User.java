package spectra.recruitment.task.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import spectra.recruitment.task.values.UserRole;

/**
 * 사용자
 */
@Slf4j
@Getter
@Table(name = "tb_user")
@Entity
@NoArgsConstructor
@Comment("사용자")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "BIGINT UNSIGNED")
    @Comment("PK")
    private Long id;

    @Column(length = 30, nullable = false)
    @Comment("이름")
    private String name;

    @Column(length = 30)
    @Comment("연락처")
    private String mobile;

    @Column(name = "office_call_no", length = 30)
    @Comment("내선 전화번호")
    private String officeCallNo;

    @Column(name = "login_id", length = 30, nullable = false, unique = true)
    @Comment("아이디")
    private String loginId;

    @Column(name = "login_pw", length = 200, nullable = false)
    @Comment("비밀번호")
    private String loginPw;

    @Column(length = 30, nullable = false)
    @Comment("권한")
    private UserRole role;

    @Column(name = "use_yn", length = 1, nullable = false)
    @ColumnDefault("'Y'")
    @Comment("사용 여부")
    private String useYn;

    public static User createTestUser(String name, String loginId, String loginPw, UserRole role){
        User user = new User();
        user.name = name;
        user.loginId = loginId;
        user.loginPw = loginPw;
        user.role = role;

        user.mobile = "123";
        user.officeCallNo = "123";
        user.useYn = "Y";

        return user;
    }
}
