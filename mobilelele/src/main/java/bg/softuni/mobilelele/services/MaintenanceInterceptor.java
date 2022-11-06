package bg.softuni.mobilelele.services;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

@Component
public class MaintenanceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var timeNow = LocalTime.now();

        var requestUri = request.getRequestURI();
        if (!requestUri.equals("/maintenance")) {
            if (timeNow.getHour() >= 21) {
                response.sendRedirect("/maintenance");
                return false;
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
