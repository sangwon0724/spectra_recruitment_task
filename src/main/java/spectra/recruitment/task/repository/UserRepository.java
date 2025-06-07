package spectra.recruitment.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spectra.recruitment.task.entity.Customer;
import spectra.recruitment.task.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 로그인
     * @param loginId 아이디
     * @param loginPw 비밀번호
     */
    @Query("select u from User u where u.useYn = 'Y' and u.loginId = :loginId and u.loginPw = :loginPw")
    public User execLogin(String loginId, String loginPw);
}
