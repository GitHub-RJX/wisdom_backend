package com.rjx.security.config;

import com.rjx.security.security.DefaultPasswordEncoder;
import com.rjx.security.security.TokenManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig {

//    private final AuthenticationManagerBuilder authenticationManager;

    private UserDetailsService userDetailsService;

    private TokenManager tokenManager;

    private DefaultPasswordEncoder defaultPasswordEncoder;

    private RedisTemplate redisTemplate;

//    public TokenWebSecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
//                                  TokenManager tokenManager, RedisTemplate redisTemplate, AuthenticationManagerBuilder authenticationManager) {
//        this.userDetailsService = userDetailsService;
//        this.defaultPasswordEncoder = defaultPasswordEncoder;
//        this.tokenManager = tokenManager;
//        this.redisTemplate = redisTemplate;
//        this.authenticationManager = authenticationManager;
//    }

//    /**
//     * 配置设置
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.exceptionHandling()
//                .authenticationEntryPoint(new UnauthorizedEntryPoint())
//                .and().csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and().logout().logoutUrl("/admin/acl/index/logout")
//                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate)).and()
//                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
//                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();
//    }

//    /**
//     * 密码处理
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
//    }

//    /**
//     * 配置哪些请求不拦截
//     *
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/api/**",
//                "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"
//        );
//    }
}