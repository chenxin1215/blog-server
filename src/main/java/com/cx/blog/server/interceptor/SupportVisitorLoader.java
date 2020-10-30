package com.cx.blog.server.interceptor;

import com.cx.blog.server.config.SupportVisitor;
import com.cx.utils.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SupportVisitorLoader implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupportVisitorLoader.class);

    private static List<String> urls = new ArrayList<>();

    public List<String> getVisitorUrls() {
        return urls;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * @Description: 在所有Spring IOC容器实例化Bean之后 判断游客名单方法
     *
     * @Author: chenxin
     * @Param: [bean, beanName]
     * @Date: 2020/10/28
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clz = bean.getClass();
        /** 判断此bean是否是controller */
        boolean annotationPresent = clz.isAnnotationPresent(Controller.class);
        boolean clzAnnotationPresent = clz.isAnnotationPresent(RestController.class);
        if (annotationPresent || clzAnnotationPresent) {
            Method[] methods = clz.getDeclaredMethods();
            for (Method method : methods) {
                /** 优先判断某个方法是否有自定义注解 */
                boolean supportVisitorAnnotation = method.isAnnotationPresent(SupportVisitor.class);
                if (supportVisitorAnnotation) {
                    /** 获取此类的一级路径，没有返回空 */
                    String firstPath =
                        StringUtils.defaultString(lookupRequestMappingPath(bean.getClass().getAnnotations()));
                    RequestMapping isRequestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                    if (isRequestMapping != null) {
                        String path = lookupRequestMappingPath(AnnotationUtils.getAnnotations(method));
                        if (StringUtils.isNotBlank(path)) {
                            urls.add(firstPath + path);
                            LOGGER.info("添加游客模式:{}", firstPath + path);
                        } else {
                            urls.add(firstPath);
                            LOGGER.info("添加游客模式:{}", firstPath);
                        }
                    }
                }
            }
        }

        return bean;
    }

    private String lookupRequestMappingPath(Annotation[] annotations) {
        for (Annotation ann : annotations) {
            Class annType = ann.annotationType();
            // 如果annType与RequestMapping类型一致 或 RequestMapping注释存在于annType上
            if (annType.isAssignableFrom(RequestMapping.class) || annType.isAnnotationPresent(RequestMapping.class)) {
                String[] paths = (String[])AnnotationUtils.getValue(ann);
                return paths.length > 0 ? paths[0] : null;
            }
        }
        return null;
    }

}
