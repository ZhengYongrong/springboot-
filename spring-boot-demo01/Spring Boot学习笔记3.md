# Spring boot 学习笔记
## 参考资料

https://spring.io/projects/spring-boot/#learn

官方文档和API

| 2.2.0 **CURRENT** **GA** | [ Reference Doc.](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/html/) | [ API Doc.](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/api/) |
| ------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 2.2.1 **SNAPSHOT**       | [ Reference Doc.](https://docs.spring.io/spring-boot/docs/2.2.1.BUILD-SNAPSHOT/reference/html/) | [ API Doc.](https://docs.spring.io/spring-boot/docs/2.2.1.BUILD-SNAPSHOT/api/) |
| 2.1.10 **SNAPSHOT**      | [ Reference Doc.](https://docs.spring.io/spring-boot/docs/2.1.10.BUILD-SNAPSHOT/reference/html/) | [ API Doc.](https://docs.spring.io/spring-boot/docs/2.1.10.BUILD-SNAPSHOT/api/) |
| 2.1.9 **GA**             | [ Reference Doc.](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/) | [ API Doc.](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/api/) |
| 1.5.22 **GA**            | [ Reference Doc.](https://docs.spring.io/spring-boot/docs/1.5.22.RELEASE/reference/html/) | [ API Doc.](https://docs.spring.io/spring-boot/docs/1.5.22.RELEASE/api/) |

## 项目初始化

在线初始化：https://start.spring.io/



Eclipse 安装Spring Tools插件 或者下载Spring官方的STS整合版。

IntelliJ IDEA 安装Spring Assistant插件。



Spring Boot CLI初始化项目：

You can download the Spring CLI distribution from the Spring software repository:

- [spring-boot-cli-2.1.9.RELEASE-bin.zip](https://repo.spring.io/release/org/springframework/boot/spring-boot-cli/2.1.9.RELEASE/spring-boot-cli-2.1.9.RELEASE-bin.zip)
- [spring-boot-cli-2.1.9.RELEASE-bin.tar.gz](https://repo.spring.io/release/org/springframework/boot/spring-boot-cli/2.1.9.RELEASE/spring-boot-cli-2.1.9.RELEASE-bin.tar.gz)



##  开发系统要求

**使用Spring Boot 2.1.9.RELEASE**

**JDK版本:**

**[Java 8](https://www.java.com/)** and is compatible up to **Java 12** (included).

**Spring版本:**

Spring Framework 5.1.10.RELEASE or above is also required.

 **构建工具版本:**

| Build Tool | Version |
| ---------- | ------- |
| Maven      | 3.3+    |
| Gradle     | 4.4+    |

## Servlet 容器

Spring Boot supports the following embedded servlet containers:

| Name         | Servlet Version |
| ------------ | --------------- |
| Tomcat 9.0   | 4.0             |
| Jetty 9.4    | 3.1             |
| Undertow 2.0 | 4.0             |

You can also deploy Spring Boot applications to any **Servlet 3.1+** compatible container.



## 第一个HelloWorld

参考官方文档[10. Installing Spring Boot](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/getting-started-installing-spring-boot.html)：

步骤:

1. 新建一个基于Maven 的 Spring boot Java 工程
2. 自动生成启动类
3. 配置文件中修改默认端口

3. 编写Controller类

5. 运行程序

下面是一个典型的程序结构：

```xml
com
	+- example
		+- myproject
			+- Application.java
			|
			+- domain
				| +- Customer.java
				|
			+- repository
				| +- CustomerRepository.java
				|
			+- service
				| +- CustomerService.java
				|
			+- web
				+- CustomerController.java
```



Application.java 将声明 main 方法和 @SpringBootApplication



1.使用插件或者在线网页进行spring boot项目的初始化

- jdk版本 8
- maven项目
- java语言

- 设置项目的路径等配置

  

2.选择spring boot版本 和 工具支持

- 创建spring web项目  就要勾选  Web>Spring Web
- 以后需要spring data jpa项目  就要勾选  SQL>Spring Data JPA -对应需要配置数据源

- 如此类推



3.自动生成程序入口启动类：

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemo01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemo01Application.class, args);
	}

}
```

通常建议将应用的main类放到其他类所在包的顶层(root package)，项目启动时自动扫描main启动类所在的根路径com.example.demo下所有带spring组件注解的类：

@Component -无法界定属于下面哪个具体的层的时候使用，例如：AOP、LOG、工具类...

@Repository   - 持久层
@Service		 -业务层
@Controller	-控制层
@RestController  -控制层-Restful风格API设计



**4.默认配置文件**

src\main\resources\application.properties

**可以修改下端口号防止端口占用：**

```xml
server.port=8081
```



**5.自定义一个web层类**

```java
package com.example.demo.hello.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController 
{
	@GetMapping("/hello")
	public String hello() {
		
		return "Hello World!";
	}
}
```



**6.启动项目**

选中 SpringBootDemo01Application.java文件   Run as..>Spring Boot App

启动成功后打开浏览器访问：http://localhost:8081/hello



7.Maven介绍

Maven用户可以继承 spring-boot-starter-parent 项目来获取合适的默认设
置。该parent项目提供以下特性：

- 默认编译级别为Java 1.6

- 源码编码为UTF-8

- 一个Dependency management节点，允许你省略常见依赖的 <version> 标
  签，继承自 spring-boot-dependencies POM。

- 恰到好处的资源过滤

- 恰到好处的插件配置（exec插件，surefire，Git commit ID，shade）

- 恰到好处的对 application.properties 和 application.yml 进行筛选，

  包括特定profile（profile-specific）的文件，比如 applicationfoo.
  properties 和 application-foo.yml

  

  最后一点：由于配置文件默认接收Spring风格的占位符（ ${...} ），所以Maven
  filtering需改用 @..@ 占位符（你可以使用Maven属性 resource.delimiter 来覆
  盖它）。


**继承starter parent**
如果你想配置项目，让其继承自 spring-boot-starter-parent ，只需
将 parent 按如下设置：

```xml
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.9.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
```



注：你应该只需在该依赖上指定Spring Boot版本，如果导入其他的starters，放心
的省略版本号好了。更多starters ：[参考官方文档](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/using-boot-build-systems.html#using-boot-maven-parent-pom) **Table 13.1. Spring Boot application starters**

```xml
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```



**改变Java版本**

```xml
<properties>
<java.version>1.8</java.version>
</properties>
```



**使用Spring Boot Maven插件**

Spring Boot包含一个Maven插件，它可以将项目打包成一个可执行jar。如果想使用
它，你可以将该插件添加到 <plugins> 节点处：

```xml
<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
		</plugin>
	</plugins>
</build>
```

注：如果使用Spring Boot starter parent pom，你只需添加该插件而无需配置它，除非你想改变定义在partent中的设置。





## Spring Boot Web开发

### SpringBoot热部署spring-boot-devtools

```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
		<optional>true</optional>
	</dependency>
```

在web开发中，静态资源的访问是必不可少的，如：图片、js、css 等资源的访问。spring Boot 对静态资源访问提供了很好的支持，基本使用默认配置就能满足开发需求。



### 默认静态资源映射

**一、Spring Boot 对静态资源映射提供了默认配置**

Spring Boot 默认将 /** 所有访问映射到以下目录：

v  classpath:/static

v  classpath:/public

v  classpath:/resources

v  classpath:/META-INF/resources

如果上述目录有**多张同名不同内容**的的图片fengjing.jpg，当我们访问地址 http://localhost:8080/fengjing.jpg 的时候，显示哪张图片？

**优先级顺序为**：

​	META-INFO/resources > resources > static > public 

 

如：在resources目录下新建 META-INFO/resources 、public、resources、static 四个目录，并分别放入 a.jpg b.jpg c.jpg  d.jpg 四张不同的图片:



浏览器分别访问：

http://localhost:8081/a.jpg

http://localhost:8081/b.jpg

http://localhost:8081/c.jpg

http://localhost:8081/d.jpg

均能正常访问相应的图片资源。那么说明，Spring Boot 默认会挨个从META-INFO/resources 、 resources 、static、public  里面找是否存在相应的资源，如果有则直接返回。



**二、自定义静态资源映射**

在实际开发中，可能需要自定义静态资源访问路径，那么可以继承WebMvcConfigurerAdapter来实现。

第一种方式：静态资源配置类

```java
package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
      //将所有/static/** 访问都映射到classpath:/static/ 目录下
       registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
  }
}
```

访问：http://localhost:8081/static/c.jpg 能正常访问static目录下的c.jpg图片资源。



第二种方式：在application.properties配置

在application.properties中添加配置：

```xml
spring.mvc.static-path-pattern=/static/**
```

重启项目，访问：http://localhost:8081/static/c.jpg 同样能正常访问static目录下的c.jpg图片资源。

 

### Spring Boot 中文乱码解决

中文乱码可能发生的环境：

1.数据库编码

2.IDE工具编码

3.项目文件编码

4.项目Web开发设置

4.部署容器端口编码

5.服务器字体支持



一、spring boot项目修改application.properties配置文件

增加如下配置：

```xml
spring.http.encoding.force=true
spring.http.encoding.charset=UTF-8
spring.http.encoding.enabled=true
server.tomcat.uri-encoding=UTF-8
```



二、响应时发生乱码？

此时拦截器中返回的中文已经不乱码了，但是controller中返回的数据依旧乱码。

修改controller的@RequestMapping修改如下：

```java
@RequestMapping(value = "/listForDoing", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
```

这种方法的弊端是限定了数据类型，更好的办法是配置全局编码控制：

```java
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		 StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		 return converter;

	 }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
       converters.add(responseBodyConverter());
    }
}
```

便可以解决SpringBoot的中文乱码问题了。 



三、Spring Boot使用FastJson中文乱码解决：

http://412887952-qq-com.iteye.com/blog/2413390







## Spring Boot 集成 spring-data-jpa

步骤:

1.在pom.xml文件中添加Spring Boot Maven依赖

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<scope>runtime</scope>
</dependency>
```

2.配置数据源

```xml
#mysql datasource
spring.datasource.url=jdbc:mysql://localhost:3306/demo?serverTimezone=GMT&characterEncoding=utf-8 
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver

#spring data jpa & mysql
spring.jpa.database=MYSQL
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.hibernate.naming.implicit-strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl


```

**hibernate.implicit_naming_strategy解释是：**
当一个实体没有显式地命名它映射到的数据库表时，我们需要隐式地确定该表的名称。或者当特定属性没有显式地命名它映射到的数据库列时，我们需要隐式地确定该列的名称。说的简单点就是表或者属性没有指定使用名称，我的理解就是没有用@TableName和@Column。（When an entity does not explicitly name the database table that it maps to, we need to implicitly determine that table name. Or when a particular attribute does not explicitly name the database column that it maps to, we need to implicitly determine that column name.）它有以下5个属性，由于篇幅有限（我太懒-.-）。并且没有得出具体结论：

- 1.org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl

- 2.org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy

- 3.org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl

- 4.org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyHbmImpl

- 5.org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
  但是我们依然可以自己修改命名策略。然后在配置文件中设置自己的命名策略

  

**hibernate.physical_naming_strategy的解释是：**
used to convert a "logical name" (either implicit or explicit) name of a table or column into a physical name (e.g. following corporate naming guidelines)，翻译后：物理命名策略，用于转换“逻辑名称”(隐式或显式)的表或列成一个物理名称。这个 physical_naming_strategy 命名策略有两个子属性，如下：

- 1.PhysicalNamingStrategyStandardImpl：不做修改，直接映射
  spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
- 2.SpringPhysicalNamingStrategy:在进行领域映射时,首字母小写，大写字母变为下划线加小写
  ex: LoginName --> login_name
  spring.jpa.hibernate.naming.physical-strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy


3.编写domain、repository、service、web(Controller)层



domain

```java
package com.example.demo.hello.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_hello")
public class Hello 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String msg;
	private String myTestMsg;
	
	public Long getId() {
		return id;
	}
	public String getMsg() {
		return msg;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMyTestMsg() {
		return myTestMsg;
	}
	public void setMyTestMsg(String myTestMsg) {
		this.myTestMsg = myTestMsg;
	}	
}
```

repository层

```java
package com.example.demo.hello.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.hello.domain.Hello;

/**
 * PagingAndSortingRepository
 * 	基础CRUD功能
 * 	分页+排序功能
 * 	自定义查询功能
 * 	统计分析功能
 */
@Repository
public interface HelloRepository 
	extends PagingAndSortingRepository<Hello, Long>{

}
```

service层

```java
package com.example.demo.hello.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.hello.domain.Hello;

public interface IHelloService 
{
	public void saveOrUpdate(Hello hello);
	public void delete(Hello hello);
	public Hello findOne(Long id);
	public Page<Hello> findPage(Pageable pageable);
}
```



```java
package com.example.demo.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.hello.domain.Hello;
import com.example.demo.hello.repository.HelloRepository;

@Service
public class HelloService implements IHelloService
{
	@Autowired
	private HelloRepository helloRepository;

	@Override
	public void saveOrUpdate(Hello hello) {
		helloRepository.save(hello);
	}

	@Override
	public void delete(Hello hello) {
		helloRepository.delete(hello);
	}

	@Override
	public Hello findOne(Long id) {
		return helloRepository.findById(id).get();
	}

	@Override
	public Page<Hello> findPage(Pageable pageable) {
		return helloRepository.findAll(pageable);
	}
}

```

web(Controller) 层

```java
package com.example.demo.hello.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.hello.domain.Hello;
import com.example.demo.hello.service.IHelloService;

@RestController("/hello")
public class HelloController 
{
	@Autowired
	private IHelloService helloService;
	
	@PostMapping
	public void save(Hello hello) {// id = null
		 helloService.saveOrUpdate(hello);
	}
	
	@PutMapping
	public void update(Hello hello) {// id = 1
		 helloService.saveOrUpdate(hello);
	}
	

	@DeleteMapping
	public void delete(Long id) {
		Hello hello = helloService.findOne(id);
		if(null!=hello)
		 helloService.delete(hello);
	}
	
	@GetMapping
	public Hello findOne(Long id) {
		return helloService.findOne(id);
	}
}
```



4.运行程序,并使用postman调试后台：

POST:http://localhost:8081/hello?msg=你好springboot！

PUT:http://localhost:8081/hello?id=1&msg=我们更新msg字段

GET:http://localhost:8081/hello?id=1

DELETE:http://localhost:8081/hello?id=1