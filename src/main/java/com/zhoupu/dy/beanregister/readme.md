springbean注入方式
1. 使用@Bean注解
1. 使用@Controller @Service @Repository @Component 注解标注该类，然后再使用 @ComponentScan 扫描包
1. @Import 方法
1. 实现FactoryBean
1. new ProxyFactoryBean
1. BeanFactoryPostProcessor 使用BeanDefinitionBuilder.genericBeanDefinition(类.class)方式
1. BeanDefinitionRegistryPostProcessor 使用BeanDefinitionBuilder.genericBeanDefinition(类.class)方式
1. applicationContext.getDefaultListableBeanFactory

SpringAOP
AspectJ、ProxyFactory、ProxyFactoryBean  
spring支持的AOP的方式有：AspectJ,ProxyFactoryBean,ProxyFactory  
其中：  
AspectJ是目前大家最常用的 起到集成AspectJ和Spring;  
ProxyFactoryBean是将我们的AOP和IOC融合起来;  
ProxyFactory则是只能通过代码硬编码进行编写一般都是给spring自己使用;  
也就是，ProxyFactoryBean可以直接registerSingleton到beanFactory，getBean值可以取到  
原对象，而ProxyFactory registerSingleton到beanFactory取到的还是ProxyFactory对象需要  
getProxy才能获得原对象
