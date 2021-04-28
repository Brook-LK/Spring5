package com.brook.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.brook.conf.SpringConfig;
import com.brook.entity.*;
import com.brook.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestSpring5IOC {

    @Test
    public void test1() {
        //1.加载spring配置文件(通过类创建)
        //ApplicationContext,在获取这个配置文件后就创建了这个对象，而不是调用的时候
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC1.xml");
        //FileSystemXmlApplicationContext()方法创建的时得带盘符路径，一般不这样写
        ApplicationContext context2 = new FileSystemXmlApplicationContext("D:\\workspace\\Java\\Spring5\\src\\bean_IOC1.xml");
        //BeanFactory context = new ClassPathXmlApplicationContext("bean_IOC1.xml");
        //2.获取配置创建的对象
        //context.getBean("user",User.class)中的”user“是Bean1.xml中的id
        User user = context.getBean("user", User.class);

        System.out.println(user);
        //3.调用创建对象的方法
        user.add();
    }

    @Test
    public void test2() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC1.xml");
        //BeanFactory,只有当获取这个对象的时候才去创建这个对象，就是在第二步中才创建
        BeanFactory context = new ClassPathXmlApplicationContext("bean_IOC1.xml");

        User user = context.getBean("user", User.class);

        System.out.println(user);

        user.add();
    }

    //property注入属性
    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC1.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user.toString());
    }

    //有参构造注入属性constructor-arg
    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC1.xml");
        User user = context.getBean("user2", User.class);
        System.out.println(user.toString());
    }

    //构造函数多的时候会无法识别用的哪个
    @Test
    public void test5() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC1.xml");
        User user = context.getBean("user3", User.class);
        System.out.println(user.toString());
    }

    //通过p空间初始化属性值
    @Test
    public void test6() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC2.xml");
        Order order = context.getBean("order", Order.class);
        System.out.println(order.toString());
    }

    //设置空置
    @Test
    public void test7() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC2.xml");
        Order order = context.getBean("order1", Order.class);
        System.out.println(order.toString());
    }

    //特殊符号属性值注入
    @Test
    public void test8() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC2.xml");
        Order order = context.getBean("order2", Order.class);
        System.out.println(order.toString());
    }

    //外部Bean
    @Test
    public void test9() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC3.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.test();
    }

    //内部Bean
    @Test
    public void test10() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC3.xml");
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp.toString());
    }

    //级联赋值1
    @Test
    public void test11() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC3.xml");
        Emp emp = context.getBean("emp1", Emp.class);
        System.out.println(emp.toString());
    }
    @Test
    public void test12() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC3.xml");
        Emp emp = context.getBean("emp2", Emp.class);
        System.out.println(emp.toString());
    }

    //注入集合属性
    @Test
    public void test13() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC4.xml");
        Stu stu = context.getBean("stu", Stu.class);
        System.out.println(stu.toString());
    }

    //xmlns:util使用
    @Test
    public void test14() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC5.xml");
        Stu stu = context.getBean("stu", Stu.class);
        System.out.println(stu.toString());
    }

    //MyBean
    @Test
    public void test15() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOCMybean.xml");
        //这里未强制规范，可以返回任意类型，定义类型和返回类型可以不一样
        User myBean = context.getBean("myBean", User.class);
        System.out.println(myBean.toString());
    }

    //单实例
    @Test
    public void test16() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC4.xml");
        Course course = context.getBean("course", Course.class);
        Course course1 = context.getBean("course", Course.class);
        //默认为单实例，创建的为同一个对象，下面输出的内容一样，和是否是ApplicationContext或BeanFactory无关
        //singleton在加载配置文件的时候创建对象
        System.out.println(course);
        System.out.println(course1);
        System.out.println(course1.hashCode());
        System.out.println(System.identityHashCode(course1));
    }

    //多实例
    @Test
    public void test17() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC4.xml");
        Course course = context.getBean("course1", Course.class);
        Course course1 = context.getBean("course1", Course.class);
        //通过scope="prototype"来设置为多实例，两次输出地址不同
        //prototype在调用getBean的时候创建对象
        System.out.println(course);
        System.out.println(course1);
    }

    //生命周期
    @Test
    public void test18() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOCCycle.xml");
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean_IOCCycle.xml");
        Cycle cycle = context.getBean("cycle", Cycle.class);
        System.out.println("4. bean可以使用了（对象获取到了）");
        cycle.print();
        //手动让bean销毁
        ((ClassPathXmlApplicationContext) context).close();
    }

    //自动装配，根据属性名称或属性类型，spring自动将匹配的属性值进行注入
    @Test
    public void test19() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC6.xml");
        Emp emp = context.getBean("emp1", Emp.class);
        System.out.println(emp);
    }

    //引入druid的jar包
    //数据库配置
    @Test
    public void test20() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOCMysql.xml");
        DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        System.out.println(dataSource.toString()+dataSource.getVersion());
    }

    //注解配置
    @Test
    public void test21() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_IOC7.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        //这里要注入属性，需要自动注入：
        //@Autowired
        //private UserDao userDao;
        //@Component
        //public class UserDaoImpl implements UserDao
        userService.test();
    }

    //纯注解配置
    @Test
    public void test22() {
        //这里用AnnotationConfigApplicationContext()加载配置类
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.test();
    }

}
