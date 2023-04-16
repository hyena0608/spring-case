package com.example.springargumentresolver.jwt;

import com.example.springargumentresolver.domain.User;
import com.example.springargumentresolver.exception.TokenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthorizationArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JwtAuthorization.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory
    ) {
        log.info("{} 실행", getClass().getSimpleName());

        // NativeWebRequest로 부터 HttpServletRequest를 받아온다.
        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        // HttpServletRequest가 존재하면 Header에서 Key가 Authorization인 Value를 받아온다. (토큰값)
        if (Objects.nonNull(request)) {
            final String token = request.getHeader("Authorization");

            // 토큰값이 null이 아닐 경우
            // 토큰값이 비어있는 값이 아닐 경우
            // 토큰값이 JwtProvider에 의해 검증이 통과될 경우
            if (Objects.nonNull(token) && !token.trim().equals("") && jwtProvider.validateToken(token)) {
                // 토큰값으로부터 User를 생성하여 반환한다.
                return jwtProvider.getClaim(token);
            }
        }

        final JwtAuthorization annotation = parameter.getParameterAnnotation(JwtAuthorization.class);
        // @JwtAuthorization 어노테이션은 있지만 위에서 통과되지 않았을 경우
        if (Objects.nonNull(annotation)) {
            // 비어있는 User를 반환한다.
            return new User();
        }

        throw new TokenException("[ERROR] 권한이 없습니다.");
    }
}
