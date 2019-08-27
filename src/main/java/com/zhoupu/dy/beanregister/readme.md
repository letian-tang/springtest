1. springbean注入方式
1. 1、使用@Bean注解
1. 2、使用@Controller @Service @Repository @Component 注解标注该类，然后再使用 @ComponentScan 扫描包
1. 3、@Import 方法
1. 4、实现FactoryBean
1. 5、BeanFactoryPostProcessor 使用BeanDefinitionBuilder.genericBeanDefinition(类.class)方式
1. 6、BeanDefinitionRegistryPostProcessor 使用BeanDefinitionBuilder.genericBeanDefinition(类.class)方式
