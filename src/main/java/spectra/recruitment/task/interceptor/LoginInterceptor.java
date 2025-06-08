package spectra.recruitment.task.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import spectra.recruitment.task.values.ConstValue;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        log.info("[LoginInterceptor] " + req.getServletPath());

        HttpSession session = req.getSession();
        Object sessionInfo = session == null ? null : session.getAttribute(ConstValue.SESSION_INFO.getValue());
        if ( sessionInfo == null ){
            res.sendRedirect("/");
            return false; //더이상 컨트롤러 요청으로 가지 않도록 false로 반환함
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mav) throws Exception {
        //super.postHandle(request, response, handler, modelAndView);
    }
}