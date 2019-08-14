package com.zhoupu.dy.methodfilter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.util.ReflectionUtils.MethodFilter;

/**
 * 测试类
 */
public class MethodFilterTests {


    @Test
    public void methodFilterTest() {
        String methodName = "method2";
        Object[] args = new Object[] {"asd", 18};
        Set<Method> methods = MethodIntrospector.selectMethods(Teacher.class, (MethodFilter) (method) -> {
            String name = method.getName();
            int argLength = method.getParameterCount();
            return (name.equals(methodName) && argLength == args.length);
        });
        Method targetMethod = methods.iterator().next();
        int paramCount = targetMethod.getParameterCount();
        final Map<String, Object> uriVars = new HashMap<>();
        for (int i = 0; i < paramCount; i++) {
            MethodParameter param = new SynthesizingMethodParameter(targetMethod, i);
            param.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());
            uriVars.put(targetMethod.getName(),param);
        }
        System.out.println(methods);
    }
}
