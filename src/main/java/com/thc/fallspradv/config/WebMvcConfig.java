package com.thc.fallspradv.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import com.thc.fallspradv.interceptor.DefaultInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //파일 접근하는 통로 열어두고 싶다면 사용하면 됨
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        // 절대경로 file:///c:/upload/
        // 상대경로 file:./upload/
        registry
            .addResourceHandler("/files/**")
            //접근할 파일이 있는 위치를 지정
            .addResourceLocations("file:" + "C:/workspace/uploadfiles/")
            .setCachePeriod(60 * 60) // 초 단위
            .resourceChain(true)
            .addResolver(new PathResourceResolver());
    }

    // spring security 다음, interceptor가 있고, 이후, controller가 있다.
    // interceptor 설정
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
            .addInterceptor(new DefaultInterceptor())
            .addPathPatterns("/api/**") // interceptor가 실행되어야 하는 url 패턴 설정  -> 여기 속한 api만 인터셉터가 실행됨
            .excludePathPatterns(
                "/resources/**", "/api/tbuser/logout"); // interceptor가 실행되지 않아야 하는 url 패턴 설정
    }

}