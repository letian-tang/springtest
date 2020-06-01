谈起AOP，大家都知道是面向切面编程，但你真的了解Spring中的AOP吗？SpringAOP、JDK动态代理、CGLIB、Aspectj之间又有什么关联和区别？

在Spring中AOP包含两个概念，一是Spring官方基于JDK动态代理和CGLIB实现的SpringAOP，二是集成面向切面编程神器Aspectj。Spring AOP和AspectJ不是竞争关系，基于代理的框架的Spring AOP和成熟框架AspectJ都是有价值的，它们是互补的。Spring无缝地将Spring AOP、IoC与AspectJ集成在一起，从而达到AOP的所有能力。Spring AOP默认将标准JDK动态代理用于AOP代理，可以代理任何接口。但如果没有面向接口编程，只有业务类，则使用CGLIB。当然也可以全部强制使用用CGLIB，只要设置proxy-target-class="true"

![](https://s1.51cto.com/images/blog/201911/07/895e2225977eb9e82c73ce08c93fa80d.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)

AOP中的术语
通知（Advice）
Spring切面可以应用5种类型的通知： 
前置通知（Before）：在目标方法被调用之前调用通知功能； 
后置通知（After）：在目标方法完成之后调用通知，此时不会关心方法的输出是什么； 
返回通知（After-returning）：在目标方法成功执行之后调用通知； 
异常通知（After-throwing）：在目标方法抛出异常后调用通知；
环绕通知（Around）：通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行 自定义的行为。
连接点（Join point）
切点（Poincut）
切面（Aspect）
引入（Introduction）
织入（Weaving）
意思术语的解释，其他博文中很多，这里就不再赘述。

用2个例子来说明SpringAOP和AspectJ的用法
现在有这样一个场景，页面传入参数当前页page和每页展示多少条数据rows,我们需要写个拦截器将page、limit参数
转换成MySql的分页语句offset、rows
先看SpringAOP实现
1、实现MethodInterceptor，拦截方法
` public class MethodParamInterceptor implements MethodInterceptor {
 
 
     /**
      *
      * @param invocation
      * @return
      * @throws Throwable
      */
     @Override
     @SuppressWarnings("unchecked")
     public Object invoke(MethodInvocation invocation) throws Throwable {
         Object[] params = invocation.getArguments();
         if (ArrayUtils.isEmpty(params)) {
             return invocation.proceed();
         }
         for (Object param : params) {
             //如果参数类型是Map
             if (param instanceof Map) {
                 Map<String, Object> paramMap = (Map<String, Object>) param;
                 processPage(paramMap);
                 break;
             }
         }
         return invocation.proceed();
     }
 
     /**
      *
      * @param paramMap
      */
     private void processPage(Map<String, Object> paramMap) {
         if (!paramMap.containsKey("page") && !paramMap.containsKey("limit")) {
             return;
         }
         int page = 1;
         int rows = 10;
         for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
             String key = entry.getKey();
             String value = entry.getValue().toString();
             if ("page".equals(key)) {
                 page = NumberUtils.toInt(value, page);
             } else if ("limit".equals(key)) {
                 rows = NumberUtils.toInt(value, rows);
             }else {
                 //TODO
             }
         }
         int offset = (page - 1) * rows;
         paramMap.put("offset", offset);
         paramMap.put("rows", rows);
     }
 }`
 
 
 2、定义后置处理器，将方法拦截件加入到advisor中。我们通过注解@Controller拦截所有的Controller，@RestController继承于Controller，
 所以统一拦截了
 `public class RequestParamPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor
          implements InitializingBean {
  
      //拦截所有的@Controller，@RestController继承于Controller，所以统一拦截了
      private Class<? extends Annotation> validatedAnnotationType = Controller.class;
  
      @Override
      public void afterPropertiesSet() throws Exception {
          Pointcut pointcut = new AnnotationMatchingPointcut(this.validatedAnnotationType, true);
          this.advisor = new DefaultPointcutAdvisor(pointcut, new MethodParamInterceptor());
      }
  }
  
  3、万事具备只欠东风，Processor也写好了，只需要让Processor生效。
  `@Configuration
   public class MethodInterceptorConfig {
       @Bean
       public RequestParamPostProcessor converter() {
           return new RequestParamPostProcessor();
       }
   }`
   
这里有个坑需要注意一下，如果在配置类中注入业务Bean
`@Configuration
 public class MethodInterceptorConfig {
 
     @Autowired
     private UserService userService;
 
     @Bean
     public RequestParamPostProcessor converter() {
         return new RequestParamPostProcessor();
     }
 }
`
启动时，会出现
`2019-11-08 14:55:50.954  INFO 51396 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'sqlSessionFactory' of type [org.apache.ibatis.session.defaults.DefaultSqlSessionFactory] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
 2019-11-08 14:55:50.960  INFO 51396 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'sqlSessionTemplate' of type [org.mybatis.spring.SqlSessionTemplate] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
 2019-11-08 14:55:51.109  INFO 51396 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'rememberMapper' of type [com.sun.proxy.$Proxy84] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
 2019-11-08 14:55:53.406  INFO 51396 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration' of type [org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
`
 导致很多切面失效，如事务切面，这是因为我们注入了自定义的bean，自定义的bean优先级最低，由最低优先级的BeanPostProcessor来加载并完成初始化的。
 但为了加载其中的RequestParamPostProcessor，导致不得不优先装载低优先级bean，此时，aop处理器，数据库处理器等都未完成装载，
 故由这部分业务bean牵扯到的相关逻辑的aop初始化，注解事务初始化，都事实上失败了。但spring就提示了一个INFO级别的提示，然后剩下的bean由最低优先级的BeanPostProcessor正常处理。
 
 
 AspectJ方式实现切面
 
 
 


`
 
 

