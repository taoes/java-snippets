<div align=center>
 <img alt="Java代码" width="320" src="http://www.zhoutao123.com/picture/book_convert/java.png"/>
</div>



<div style="height:20px"></div>

您可以通过以下导航来在 语雀 中访问我的读书笔记，涵盖了技术、服务端开发与基础架构、闲谈杂记等多个项目：


《[前端开发杂记](https://www.yuque.com/zhoutao123/front_end)》 
《[设计模式](https://www.yuque.com/zhoutao123/design_pattern)》
《[深入理解JVM虚拟机](https://www.yuque.com/zhoutao123/jvm)》
《[Java 并发编程](https://www.yuque.com/zhoutao123/java_concurrent)》 
《[Netty入门与实战](https://www.yuque.com/zhoutao123/netty)》
《[高性能MySQL](https://www.yuque.com/zhoutao123/mysql)》

> + [最新文档，请访问语雀文档](https://www.yuque.com/zhoutao123)

---




## 代码 & 三方库

###  [自定义类加载器](./class_loader/README.md)
>类加载器是 JVM 一个非常重要的内容，我们可以通过自定义类加载器实现从网络中甚至数据库中加载class。



### [反序列化多态类型](./jackson/README.md)
> 著名的Json 库 JackSon 特性支持多态反序列化, 可以根据接送类型的某个字段值来反序列化到不同的子类中，实现动态序列化
 
 
### [MyBatis Plus](https://mp.baomidou.com)
> MyBatis-Plus（简称 MP）是一个 MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。


### [Guava](https://github.com/google/guava)
> Guava is a set of core Java libraries from Google that includes new collection types (such as multimap and multiset), immutable collections, a graph library, and utilities for concurrency, I/O, hashing, caching, primitives, strings, and more! It is widely used on most Java projects within Google, and widely used by many other companies as well.


### [Redisson](https://redisson.pro)
> 内存型数据库Redis的Java客户端，支持超过50种类型的对象和服务: Set,Map，Queue，SpringCache，MyBatis，分布式锁等 


### [自定义参数转换器](https://www.baeldung.com/spring-mvc-custom-data-binder)
> 有的时候，前端传递的参数并不符合我们接口的参数类型，我们想对参数进行处理，这时候需要使用如下的代码配置,下面就完成当前前端传过来String，接口中却需要EntityId类型的问题

```java
public class StringToEntityIdConverterFactory implements ConverterFactory<String, EntityId> {
  
  @Override
  public <T extends EntityId> Converter<String, T> getConverter(@NotNull Class<T> targetType) {
    return source -> {
      return new EntityId(1L);
    };
  }
}

```

```java
// 路径参数/查询参数序列化
@Configuration
public class ParameterConverterConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverterFactory(new StringToEntityIdConverterFactory());
  }

}

```





## 插件类(Idea)

+ [JRebel](https://manuals.jrebel.com/jrebel/)
> JRebel是一款JVM插件，它使得Java代码修改后不用重启系统，立即生效。IDEA上原生是不支持热部署的，一般更新了 Java 文件后要手动重启 Tomcat 服务器，才能生效，浪费时间浪费生命。
  目前对于idea热部署最好的解决方案就是安装JRebel插件。

+ [Lombok](https://projectlombok.org/)
> Lombok项目是一个Java库，它会自动插入编辑器和构建工具中，Lombok提供了一组有用的注释，用来消除Java类中的大量样板代码。仅五个字符(@Data)就可以替换数百行代码从而产生干净，简洁且易于维护的Java类。

+ [Google Java Format](https://github.com/google/google-java-format)
> 一种推荐的代码格式化统一风格，支持IDEA，提供[插件形式](https://plugins.jetbrains.com/plugin/8527-google-java-format)和[配置文件](https://raw.githubusercontent.com/google/styleguide/gh-pages/intellij-java-google-style.xml)形式

+ [MyBatisX](https://mp.baomidou.com/guide/mybatisx-idea-plugin.html)
> MyBatis Plus 出品， 由Mapper到跳转XML的便捷工具
 


## 工具类 (Unix)

### [Tmux 终端复用](http://tmux.github.io/) 
> tmux is a terminal multiplexer. It lets you switch easily between several programs in one terminal, detach them (they keep running in the background) and reattach them to a different terminal.

### zsh & Oh my Zsh (http://tmux.github.io/)  
> 强大的Shell & 插件


## DevOps

+ [Docker & Docker-compose & Docker Swarm](https://www.docker.com)
> Debug your app, not your environment & Securely build and share any application, anywhere

+ [Kubernetes K8s From Google](https://kubernetes.io/docs/home/)
> Kubernetes is an open source container orchestration engine for automating deployment, scaling, and management of containerized applications. The open source project is hosted by the Cloud Native Computing Foundation (CNCF).


+ [Sentry](https://sentry.io/welcome/)
> Sentry provides self-hosted and cloud-based error monitoring that helps all software teams discover, triage, and prioritize errors in real-time.

+  [Prometheus](https://prometheus.io/)
> Power your metrics and alerting with a leading open-source monitoring solution.
