HandlerMapping接口主要实现类
(1) RequestMappingHandlerMapping->支持带@RequestMapping注释的方法,配合@Controller使用

     protected boolean isHandler(Class<?> beanType) {
		return (AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) ||
				AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class));
	}
	
  Spring在进行request匹配的时候，不仅会匹配url，method，contentType等条件  
  还会对用户提供的定制条件进行匹配，用户提供的定制条件是使用RequestCondition进行封装的。
  
  关于RequestCondition的注入，我们需要重写RequestMappingHandlerMapping的   
  getCustomMethodCondition()方法，在RequestMappingHandlerMapping扫描BeanFactory中所有的能处理请求
     的bean（Controller对象）的时候，其会将每个方法都声明为一个RequestMappingInfo对象，并且会调用RequestMappingHandlerMapping.getCustomMethodCondition()方法，获取当前RequestMappingInfo所注册的条件，默认情况下该方法返回值是null
 
(2) SimpleUrlHandlerMapping-> 如：在xml中配置mapping关系，registerHandler
       
       <beans>
         <bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
            <property name="mappings">
             <props>
                <prop key="/welcome.htm">welcomeController</prop>
                <prop key="/*/welcome.htm">welcomeController</prop>
                <prop key="/helloGuest.htm">helloGuestController</prop>
              </props>
            </property>
         </bean>
         
         <bean id="welcomeController" 
             class="com.mkyong.common.controller.WelcomeController" />
             
         <bean id="helloGuestController" 
             class="com.mkyong.common.controller.HelloGuestController" />
             
     </beans>
     

