springbean注入方式
1. 使用@Bean注解
1. 使用@Controller @Service @Repository @Component 注解标注该类，然后再使用 @ComponentScan 扫描包
1. @Import 方法
1. 实现FactoryBean
1. BeanFactoryPostProcessor 使用BeanDefinitionBuilder.genericBeanDefinition(类.class)方式
1. BeanDefinitionRegistryPostProcessor 使用BeanDefinitionBuilder.genericBeanDefinition(类.class)方式
