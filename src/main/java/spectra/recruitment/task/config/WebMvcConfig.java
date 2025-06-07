package spectra.recruitment.task.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spectra.recruitment.task.interceptor.LoginInterceptor;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
        .excludePathPatterns(
            "/",
            "/api/**",
            "/swagger-ui/**",
            "/error"
        );
    }*/
}
