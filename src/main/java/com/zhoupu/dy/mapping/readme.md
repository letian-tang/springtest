HandlerMapping接口主要实现类
(1) RequestMappingHandlerMapping->支持带@RequestMapping注释的方法,配合@Controller使用

     protected boolean isHandler(Class<?> beanType) {
		return (AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) ||
				AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class));
	}

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
     

