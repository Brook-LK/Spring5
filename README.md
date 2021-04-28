# _Spring5_ 

![开心一刻](title.jpg)

Spring5及Spring功能特性  
[下载地址](https://repo.spring.io/release/org/springframework/spring/)

### Spring概述 

针对bean的生命周期进行管理的轻量级容器;   
Spring 主要由七部分组成,分别是Spring Core, Spring AOP,
 Spring ORM, Spring DAO, Spring Context, Spring Web 和
 Spring Web MVC;  
 
 _开始使用_   
 使用Spring先得导入如下必须的包  
 spring-beans-5.3.5.jar  
 spring-context-5.3.5.jar  
 spring-core-5.3.5.jar  
 spring-expression-5.3.5.jar  
 - 其他用到的包后面需要的时候再导入

### IOC容器  

启用@Test，引入JUnit4  
技术：xml解析--DOM4J是 dom4j.org 出品的一个开源 XML 解析包，工厂模式，反射   

 - ioc底层原理（为了降低耦合度）  
 通过反射的方式获取到对应的类，然后再通过工厂模式（ioc接口）实例化对应的类  
 - ioc接口  
 BeanFactory(Spring自带的开发中一般不适用)或ApplicationContext（BeanFactory的子接口，面向开发者的接口）  
 - ioc操作Bean管理（基于xml）  
 ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");    
 ClassName object = context.getBean("id",ClassName.class);
 - ioc操作Bean管理（基于注解）  
 
##### Bean类型  

普通Bean: 手动创建的Bean，在配置文件定义bean类型就是返回类型  
FactoryBean: 通过工厂创建的Bean，在配置文件定义bean类型可以与返回类型不一样  
1、创建类，让这个类作为工厂bean，实现接口FactoryBean   
2、实现接口里面的方法，在实现的方法中定义返回的bean类型   

##### Bean作用域  

 - 在默认情况下创建的bean是单实例,可以通过scope="prototype"来设置为多实例      
 singleton在加载配置文件的时候创建对象   
 prototype在调用getBean的时候创建对象    
 另外还有request(在一次请求中有效)和session(在一次对话中有效)   

##### Bean生命周期  

 1. 通过构造器创建bean实例（无参构造） 
 2. 为bean的属性设置值和对其他bean的引用（调用set方法）  
 3.0. 把bean实例传递bean后置处理器的方法（postProcessBeforeInitialization）  
 3. 调用bean的初始化方法（需要进行配置初始化方法）  
 3.2. 把bean实例传递bean后置处理器的方法（postProcessAfterInitialization）  
 4. bean可以使用了（对象获取到了） 
 5. 当容器关闭的时候，调用bean的销毁方法（需要配置销毁的方法） 

##### Bean注解  

 - Spring针对Bean管理中创建对象提供注解：  
 @Component、@Controller、@Service、@Repository，其中后三个都是基于第一个来创建的注解，@Repository一般用在dao层（这三个注解没有非常明确的区分，只不过一般区分引用在控制层，服务层，dao层）   
 - 引入spring-aop-5.3.5.jar包   
 开启组件扫秒，告诉Spring哪个包中的类有注解   
 - 注入属性：   
 @Resource、@Autowired、@Qualifier、@Value,其中@Autowired（根据属性类型自动装配）、@Qualifier（根据属性名称进行注入）这两一般搭配使用获取多实现接口的类（多态）；@Resource（既可以根据属性类型有可以根据名称注入）；@Value（注入普通类型属性）     

### AOP   

 - 面向切面（方面）编程    
 - 对业务逻辑各个部分进行隔离，从而降低业务逻辑间的耦合度，提高程序的可重用性能，提高开发效率     
 - 不通过修改源代码方式，在主干功能里添加新功能       
 
##### AOP原理    

 - 底层使用动态代理   
 1. 有接口的情况-JDK动态代理：1、创建接口实现代理对象；2、增强类的方法     
 通过public class Proxy implements java.io.Serializable类中的public static Object newProxyInstance(ClassLoader loader,
                                                                                                  Class<?>[] interfaces,
                                                                                                  InvocationHandler h)
                                                                throws IllegalArgumentException方法
 2. 没有接口的情况-CGLIB动态代理：1、创建当前类子类的代理对象；2、实现增强类的方法        
 - Spring框架一般基于AspectJ实现AOP操作，AspectJ(可以基于注解和xml配置实现)是一个AOP框架，并不是Spring的
 - 引入依赖：spring-aspects-5.3.5.jar

##### AOP术语    
 1. 连接点，可以被增强的方法叫连接点   
 2. 切入点，实际被增强的方法叫切入点    
 3. 通知（增强）   
  3.1 正真被增强的部分叫通知     
  3.2 通知类型  
    3.2.1 前置通知    
    3.2.2 后置通知    
    3.2.3 环绕通知    
    3.2.4 异常通知    
    3.2.5 最终通知    
 4. 切面（动作），把通知应用到切入点的过程     

##### 切入点表达式    

 1. 作用：知道对哪个类里面的哪个方法增强     
 2. 语法结构    
  2.1 execution([权限修饰符] [返回类型] [类全路径] [方法名称] ([参数列表]))     
  例： execution( * com.brook.dao.UserDao.add(..))    //* （ * 前没有空格， * 后有一个空格）表示所有修饰符，只增强add方法    
  例： execution( * com.brook.dao.UserDao.* (..))    //* 表示所有修饰符，增强所有方法       
  例： execution( * com.brook.dao.* .* (..))    //包中所有类所有方法增强      

### JdbcTemplate    

##### 引入依赖    
 - druid-1.0.20.jar 连接池     
 - mysql-connector-java-8.0.17.jar 数据库对应版本jar包     
 - spring-jdbc-5.3.5.jar Spring JDBC包     
 - spring-tx-5.3.5.jar Spring事务     
 - spring-orm-5.3.5.jar 工具包引入Mybatis，Hibernate时需要用到    

##### 操作数据库    
 - 增、删、改    
  1. JdbcTemplate.update(sql) ,sql就是执行的sql语句     
  2. jdbcTemplate.update(sql,args) ,sql就是带？执行的sql语句,args为参数列表     
 - 查        
  1. queryForObject(String sql, Class<T> var2) ,var2为返回类型;返回对象，包括Integer.class,    
  2. queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)   ,返回对象rowMapper为对应对象规则      
  3. query(String sql, RowMapper<T> rowMapper) ,返回集合--,new BeanPropertyRowMapper<Teacher>(Teacher.class)     
 - 批量操作
  1. batchUpdate(String sql, List<Object[]> batchArgs) ,batchArgs为批量操作数组，顺序和sql中参数对应      
 
### 事务管理

 - 事务是数据库操作最基本单位

##### 特性   

 - 原子性  ：一次事务操作要么都执行，要么都不执行    
 - 一致性  ：数据操作之前和操纵后总数不变      
 - 持久性  ：可以持久化保存到数据库中     
 - 隔离性  ：事务之间互相执行不影响       
 
##### 操作   

 1. 没有引入spring之前操作方法(编程式)    
 1.1. 开启事务     
 1.2. 进行业务操作     
 1.3. 异常捕获,没有发生异常，提交事务    
 1.4. 发生异常进行事务回滚     
 2. xml操作或注解操作(声明式)    
 2.1. 创建事务管理器     
 2.2. 开启事务注解      
 2.3. 对业务进行事务配置      
 3. @Transactional   //参数propagation代表传播波行为，isolation代表隔离级别       
 4. 事务传播行为：有事务调没事务的方法，没事务调用有事务的方法

##### 事务传播行为            

##### 事务隔离级别，隔离性（脏读，不可重复读，幻读）          

### Spring5新特性    

##### log4j2的整合（spring5框架自带了通用的日志封装）    

 1. 引入jar包   
 1.1. log4j-api-2.14.1.jar;log4j-core-2.14.1.jar;log4j-slf4j-impl-2.14.1.jar;slf4j-api-1.7.30.jar      
 1.2. 配置log4j2.xml(名字是固定的，不能写别的名字)     
 2. 可以通过配置获取log日志，也可以手动创建log日志
 
##### 核心容器支持@Nullable    

 - 可以使用在方法上（表示返回值可以为空），属性上，方法参数上

##### 核心容器支持函数式风格GenericApplicationContext    

##### 整合JUnit5单元测试    

 - jar包引入： spring-test-5.3.5.jar     
 - 注解：     
 1. 整合Junit4
    @RunWith(SpringJUnit4ClassRunner.class)     //加入测试框架版本    
    @ContextConfiguration("classpath:bean_IOC1.xml")       //加载配置文件    
 2. 整合JUnit5    
    //@ExtendWith(SpringExtension.class)    
    //@ContextConfiguration("classpath:bean_IOC1.xml")     
    @SpringJUnitConfig(locations = "classpath:bean_IOC1.xml")  //@ExtendWith和@ContextConfiguration的组合注解     

#### webflux


