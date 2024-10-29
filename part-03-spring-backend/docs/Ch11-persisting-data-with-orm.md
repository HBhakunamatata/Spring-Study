# Ch11 Persisting data with objected-relational mapping

Why need it

- map object properties to database columns
- create sql statement automatically
- Lazy loading
- Eager fetching
- Cascading
- Cache


Spring provides for ORM framework

- Integrated support for Spring declarative transactions
- Transparent exception handling
- Thread-safe, lightweight template classes 
- DAO support classes 
- Resource management


## 11.2 Spring and Java Persistence API

### 11.2.1 Config entity manager factory

```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-entitymanager</artifactId>
    <version>4.0.1.Final</version>
</dependency>

<dependency>
    <groupId>jakarta.persistence</groupId>
    <artifactId>jakarta.persistence-api</artifactId>
    <version>2.2.3</version>
</dependency>

<dependency>
    <groupId>org.glassfish</groupId>
    <artifactId>javax.el</artifactId>
    <version>3.0.0</version>
</dependency>
```

picture

Config File : EntityManagerFactory + TransactionManager + ExceptionTranslation

```java
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "cloud.popples.springwithorm.spittr")
public class PersistenceConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/spittr?useUnicode=true&characterEncoding=UTF-8" +
                "&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setInitialPoolSize(5);
        dataSource.setMaxPoolSize(10);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory (
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("cloud.popples.springwithorm.spittr.domain");
        return emfb;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return adapter;
    }

    /**
     * make spring realize annotations defined in JPA (@PersistenceContext )
     */
    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Autowired
    private EntityManagerFactory emf;

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
```

### 11.2.2 Writing JPA-based Repository

```java
@Repository
@Transactional
public class JpaSpitterRepository implements SpitterRepository {

    // EntityManager Proxy
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addSpitter(Spitter spitter) {
        entityManager.persist(spitter);
    }

    @Override
    public Spitter getSpitterById(long id) {
        return entityManager.find(Spitter.class, id);
    }

    @Override
    public void saveSpitter(Spitter spitter) {
        entityManager.merge(spitter);
    }
}
```

## 11.3 Automatic JPA Repository with Spring Data

```xml
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jpa</artifactId>
    <version>1.11.23.RELEASE</version>
</dependency>
```

Config file

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "cloud.popples.springwithorm.spittr.jpaspringdata")
//        ,repositoryImplementationPostfix = "Helper")
@ComponentScan(basePackages = "cloud.popples.springwithorm.spittr")
public class PersistenceConfig {
    // ...
}
```

### 11.3.1 Defining query methods

```java
public interface SpitterRepository2 extends JpaRepository<Spitter, Long> {

    Spitter findByUsername(String username);
    
}
```

### 11.3.2 Declaring custom queries

define custom queries for one table that cannot be created by Spring Data JPA

```java
public interface SpitterRepository2 extends JpaRepository<Spitter, Long> {

    @Query("select s from Spitter s where s.email like '%gmail.com'")
    List<Spitter> findAllGmailSpitters();
}
```

### 11.3.3 Mixing in custom functionality

define complex query for multi-table

```java
public interface SpitterSweeper {
    int eliteSweep();
}

public interface SpitterRepository2 extends JpaRepository<Spitter, Long>, SpitterSweeper {
    // ...
}

@Repository
@Transactional
public class SpitterRepository2Impl implements SpitterSweeper {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int eliteSweep() {
        String update =
                "UPDATE Spitter spitter " +
                        "SET spitter.status = 'Elite' " +
                        "WHERE spitter.status = 'Newbie' " +
                        "AND spitter.id IN (" +
                        "SELECT s FROM Spitter s WHERE (" +
            "SELECT COUNT(spittles) FROM s.spittles spittles) > 10000" +
        ")";
        return em.createQuery(update).executeUpdate();
    }
}
```