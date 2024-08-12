# Ch02 Wiring Beans

Main Content  

- Declare beans
- Naming beans
- Injecting constructors and setters
- Wiring beans


What : The act of creating associations between application objects is the essence of dependency injection(DI) and is commonly referred to as _wiring_.

## 2.1 Exploring Spring's configuration options

As mentioned in chapter1, the Spring container is responsible for creating the beans in your application and coordinating the relationships between those objects via DI.
  But it's your responsibility as a developer to tell Spring which beans to create and how to wire them together.  
  
There are three ways to express a bean wiring specification:
   1. Explicit configuration in XML
   2. Explicit configuration in JavaConfig
   3. Implicit bean discovery and automatic wiring

Recommendation : 
   - lean on automatic configurations as much as you can;
      the less configuration you have to do it explicitly
   - When you must do explicitly(you don't maintain the code), 
      Would rather JavaConfig than XML
   - Only use XML when JavaConfig is not equivalent

## 2.2 Automatically wiring beans (Recommended)

Spring attacks automatic wiring from two angles:  
  - _Component scanning_ : Spring automatically discovers beans to be created in the application context.
  - _Autowiring_ : Spring automatically satisfies bean dependencies.

### 2.2.1 Creating discoverable beans

- SgtPeppers.java
- CDPlayerConfig.java

### 2.2.2 Naming a component-scanned bean

```java
@Component("component_name")
public class SgtPeppers implements CompactDisc {
   // ....
}
```


### 2.2.3 Setting a base package for component scanning

```java
// method0: use the config class directory
@Configuration
@ComponentScan
public class CDPlayerConfig0 {
}

// method1: set base package explictly
@Configuration
@ComponentScan(basePackage="")
public class CDPlayerConfig1 {
}

// method2: multiple base packages
@Configuration
@ComponentScan(basePackageClasses={A.class, B.class})
public class CDPlayerConfig1 {
}

// method3: Best Practice -- marked interfaces
// use empty interface to mark the base packages
interface MarkedInterface1 {}
```

### 2.2.4 Annotating beans to be automatically wired

```java
@Autowired(required=false)
private InterfaceName childName;
```


## 2.3 Wiring beans with Java

There are times when automatic configuration isn't an option and you must configure Spring explicitly. For instance, you want to wire components from some third-party library into your application.

Notes: JavaConfig classes must not have business logic and domain code.

### 2.3.1 Creating a configuration class

```java
@Configuration
public class CDPlayerConfig2 {

    @Bean
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }

}
```

Naming a bean

- use the method name
- use @Bean(name="beanName")

### 2.3.2 Injecting with JavaConfig

```
@Bean
public MediaPlayer cdPlayer() {
    return new CDPlayer(sgtPeppers());
}
```

Notes:

- Spring will intercept any calls to the @bean method and ensure that the bean produced by that method is returned rather than allowing it to be invoked again.
- By default, all beans in Spring are singletons.(Beans are services)
- CHOOSING BETWEEN CONSTRUCTOR INJECTION AND PROPERTY INJECTION: As a general rule, I favor constructor injection for hard dependencies and property injection for any optional dependencies.


## 2.5 Importing and mixing configurations

create an upper layer config class and import all config files

```java
@Configuration
@Import({CDPlayerConfig.class, CDConfig.class}) // JavaConfig
@ImportResource({"classpath:cd-config.xml"})  // xml-config
public class SoundSystemConfig {
}
```
