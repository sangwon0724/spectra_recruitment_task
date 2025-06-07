package spectra.recruitment.task;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spectra.recruitment.task.entity.User;
import spectra.recruitment.task.repository.UserRepository;
import spectra.recruitment.task.values.UserRole;

import java.util.TimeZone;

@RequiredArgsConstructor
@SpringBootApplication
public class TaskApplication {
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// timezone 설정
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
