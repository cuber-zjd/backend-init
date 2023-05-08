package com.cuber.backend.system.filter;

import com.cuber.backend.system.config.SecurityIgnoreUrl;
import com.cuber.backend.system.exception.BusinessException;
import com.cuber.backend.system.model.common.ErrorCode;
import com.cuber.backend.system.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION="Authorization";

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private SecurityIgnoreUrl securityIgnoreUrl;

    /**
     * jwt拦截器，检验token
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=request.getHeader(AUTHORIZATION);

        Stream<RequestMatcher> matchers = Arrays.stream(securityIgnoreUrl.getUrls()).map(AntPathRequestMatcher::new);
        if (matchers.anyMatch(matcher->matcher.matches(request))){
            filterChain.doFilter(request,response);
            return;
        }

        // token为空
        if (token==null){
            throw new BusinessException(ErrorCode.NULL_TOKEN_ERROR);
        }
        // token无效
        if (!JwtUtil.verify(token)){
            throw new BusinessException(ErrorCode.INVALIDATE_TOKEN_ERROR);
        }
        // token过期失效
//        if (JwtUtil.isExpire(token)){
//            filterChain.doFilter(request,response);
//            return;
//        }

        String username=JwtUtil.getUsername(token);
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);

    }
}
