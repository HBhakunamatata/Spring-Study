# Ch03 Advanced wiring

## 3.0 Content

- Spring profiles
- Conditional bean declaration
- Autowiring and ambiguity
- Bean scoping
- Spring Expression Language

- Issue Solution Tradeoff Recover

## 3.1 Runtime Environments and Profiles

### 3.1.1 Issue

One of the most challenging things is transitioning an application from one environment to another.
  - database configuration
  - encryption algorithm
  - integration with external system varying across deployment environment

### 3.1.2 Request
    
  - the same code compiled
  - the behavior determined in the runtime

### 3.1.3 Solution

1.Profile config

```java
@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("dev")
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
    }

    @Bean
    @Profile("prod")
    public DataSource jndiDataSource() {
        JndiObjectFactoryBean jndiObjectFactoryBean =
                new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/myDS");
        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
        return (DataSource) jndiObjectFactoryBean.getObject();
    }
}
```

2.Active profiles

spring.profiles.default  
spring.profiles.active

```java
// test profile : @ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
@ActiveProfiles("dev")
public class PersistenceTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void context() {
        System.out.println(dataSource);
    }
}
```

## 3.2 Conditional wiring beans

### 3.2.1 Issue : Conditional wiring

Beans will be wired only when the _Condition_ is satisfied at the runtime.

eg.:

### 3.2.2 Solution

```java
@Bean
@Conditional(MagicExistsCondition.class)
public MagicBean magicBean() {
  return new MagicBean();
}

public interface Condition {
    boolean matches(ConditionContext ctxt, AnnotatedTypeMetadata metadata);
}

public interface ConditionContext {
    BeanDefinitionRegistry getRegistry();
    ConfigurableListableBeanFactory getBeanFactory();
    Environment getEnvironment();
    ResourceLoader getResourceLoader();
    ClassLoader getClassLoader();
}

public interface AnnotatedTypeMetadata {
    boolean isAnnotated(String var1);
    Map<String, Object> getAnnotationAttributes(String var1);
    Map<String, Object> getAnnotationAttributes(String var1, boolean var2);
    MultiValueMap<String, Object> getAllAnnotationAttributes(String var1);
    MultiValueMap<String, Object> getAllAnnotationAttributes(String var1, boolean var2);
}
```

From the ConditionContext, you can do the following:
- Check for bean definitions via the BeanDefinitionRegistry returned from getRegistry().
- Check for the presence of beans, and even dig into bean properties via the ConfigurableListableBeanFactory returned from getBeanFactory().
- Check for the presence and values of environment variables via the Environment retrieved from getEnvironment().
- Read and inspect the contents of resources loaded via the ResourceLoader returned from getResourceLoader().
- Load and check for the presence of classes via the ClassLoader returned from getClassLoader().


As for the AnnotatedTypeMetadata, it offers you a chance to inspect annotations that
may also be placed on the @Bean method. Like ConditionContext, Annotated-
TypeMetadata is an interface.


eg. ProfileCondition.class

```java
class ProfileCondition implements Condition {
    public boolean matches(
            ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (context.getEnvironment() != null) {
            MultiValueMap<String, Object> attrs =
                    metadata.getAllAnnotationAttributes(Profile.class.getName());
            if (attrs != null) {
                for (Object value : attrs.get("value")) {
                    if (context.getEnvironment()
                            .acceptsProfiles(((String[]) value))) {
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }
}
```

## 3.3 Addressing ambiguity in autowiring

### 3.3.1 Issue

Autowiring only works when exactly one bean matches the desired result. 
When there’s more than one matching bean, the ambiguity prevents Spring from autowiring the property, constructor argument, or method parameter.

### 3.3.2 Method1 : Primary

```java
// Autowired
@Component
@Primary
public class Cake implements Dessert {}

// JavaConfig can use it as well
@Bean
@Primary
public class Cake implements Dessert {}
```


### 3.3.3 Method2 : Qualifier

1. Issue : The limitation of primary beans is that @Primary doesn’t limit the choices to a single unambiguous option. It only designates a preferred option. When there’s more than one primary, there’s not much else you can do to narrow the choices further.

2. use @Qualifier when wiring beans

```java
@Autowired
@Qualifier('iceCream')
public void setDessert(Dessert dessert) {
  this.dessert = dessert;
}
```

3. Qualifier value when declaring beans

- default value : Bean name
- customized value : @Qualifier("otherName")

```java
@Component
@Qualifier('cold')
public class IceCream implements Dessert {}

// JavaConfig can use it as well
@Bean
@Qualifier('cold')
public Dessert iceCream() {
    return new IceCream();
}
```

Best Practice : Use descriptive word as Qualifier value, but lead to ambiguity again. So we need to use multiple @Qualifier, but it is banned by Java, so we need to create annotation by ourselves.

```java
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Cold {}


@Component
@Cold
@Creamy
public class IceCream implements Dessert {}
```

## 3.4 Bean Scoping

### 3.4.1 Issue

Most of the time, singleton beans are ideal. The cost of instantiating and garbage-collecting instances of objects that are only used for small tasks can’t be justified when an object is stateless and can be reused over and over again in an application.

### 3.4.2 Solution : Scope

- Singleton—One instance of the bean is created for the entire application. (Default)
- Prototype—One instance of the bean is created every time the bean is injected
into or retrieved from the Spring application context.
- Session—In a web application, one instance of the bean is created for each session.
- Request—In a web application, one instance of the bean is created for each request.

### 3.4.3 Prototype

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Notepad {}
```

### 3.4.4 Session-In and Request-In

```java
@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION,
        proxyMode=ScopedProxyMode.INTERFACES)
public ShoppingCart cart() {}
```

#### Notes: When session or request beans are wired in a singleton bean, the former will be missed when session has not been created.

Solution : so instead of injecting the actual ShoppingCart bean into StoreService, Spring just injects a proxy to the ShoppingCart bean. This proxy will expose the same methods as ShoppingCart so that for all StoreService knows, it is the shopping cart. But when StoreService calls methods on ShoppingCart, the proxy will lazily resolve it and delegate the call to the actual session-scoped Shopping-Cart bean.

- proxyMode  
  Interface : ScopedProxyMode.INTERFACES  
  Classes   : ScopedProxyMode.TARGET_CLASS


## 3.5 Runtime value injection

### 3.5.1 Issue

dependency injection has two kinds : (1) wiring a bean (2) inject a value . Sometimes we need wiring a values be determined at runtime.

### 3.5.2 Property PlaceHolder

1. Method1: Declare a property source and Retrieve properties via the Environment

```java
@Configuration
@PropertySource("classpath:app.properties")
public class ExpressiveConfig {

    @Autowired
    private Environment env;

    @Bean
    public BlankDisc disc() {
        return new BlankDisc(
                env.getProperty("disc.title"),
                env.getProperty("disc.artist")
        );
    }
}
```

2. Method2: configure PropertySourcesPlaceholderConfigurer and use @Value

```java
@Configuration
@ComponentScan(basePackageClasses = BlankDisc2.class)
@PropertySource("classpath:app.properties")
public class PlaceholderConfig {

  @Bean
  public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }
  
}

// when using autowiring, using @Value in constructor
public BlankDisc2(
        @Value("${disc.title}") String title,
        @Value("${disc.artist}") String artist) {
  this.title = title;
  this.artist = artist;
}
```

### 3.5.3 Method3:Spring Expression Language

- The ability to reference beans by their IDs

```
#{sgtPepper.artist}
#{systemProperties['disc.title']}
```

- Invoking methods and accessing properties on objects

```
#{artistSelector.select()?.toUpperCase()}
```

- Mathematical, relational, and logical operations on values

```
#{T(java.lang.Math).PI * circle.radius ^ 2}
```

- Regular expression matching
```
#{admin.email matches '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com'}
```

- Collection manipulation

```
#{jukebox.songs.?[artist eq 'Aerosmith']}
#{jukebox.songs.^[artist eq 'Aerosmith']}
#{jukebox.songs.![title]}
```