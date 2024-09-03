# Ch14 Securing Methods

- Securing method invocations
- Defining securing rules with expressions
- Creating securing expression evaluators

```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-core</artifactId>
    <version>${spring-security-version}</version>
</dependency>

<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    <version>${spring-security-version}</version>
</dependency>

<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>${spring-security-version}</version>
</dependency>

<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-taglibs</artifactId>
    <version>${spring-security-version}</version>
</dependency>

<!-- https://mvnrepository.com/artifact/javax.annotation/javax.annotation-api -->
<dependency>
    <groupId>javax.annotation</groupId>
    <artifactId>javax.annotation-api</artifactId>
    <version>1.2</version>
</dependency>
```

## 14.1 Securing method with annotations

- @Secured and @RolesAllowed
- @PreAuthorize @PostAuthorize @PreFilter @PostFilter

### 14.1 Securing Methods Configuration

```java
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecuredMethodConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("password").roles("ADMIN")
                .and()
                .withUser("user").password("password").roles("SPITTER");
    }

    @Bean
    public SpittleService securedService() {
        return new SecuredSpittleService();
    }
}
```

### 14.2 @Secured and @RolesAllowed

```java
public class SecuredSpittleService implements SpittleService {
    @Override
    @Secured({"ROLE_SPITTER", "ROLE_ADMIN"})
    public void addSpittle(Spittle spittle) {
        System.out.println("Call method successfully");
    }
}

public class JSR250SpittleService implements SpittleService {
    @Override
    @RolesAllowed({"ROLE_ADMIN", "ROLE_SPITTER"})
    public void addSpittle(Spittle spittle) {
        System.out.println("Calling addSpittle successfully");
    }
}
```


### 14.3 @PreAuthorize @PostAuthorize @PreFilter @PostFilter

Expressing method access rules : @PreAuthorize @PostAuthorize
Filtering method inputs and outputs : @PostFilter @PreFilter

```java
public class PrePostSpittleService implements SpittleService {
    @Override
    @PreAuthorize("hasRole('ROLE_SPITTER') " +
                    "and #spittle.message.length() < 100" +
                    "or hasRole('ROLE_ADMIN')")
    public void addSpittle(Spittle spittle) {
        System.out.println("Call addSpittle successfully");
    }

    @PostAuthorize("authentication.principal == returnObject.spitter.username")
    public Spittle getSpittleById(Long id) {
        Spitter spitter = new Spitter();
        spitter.setId(1L);
        spitter.setUsername("user");
        spitter.setPassword("password");

        Spittle spittle = new Spittle();
        spittle.setId(id);
        spittle.setMessage("Hello World");
        spittle.setPostedTime(new Date());
        spittle.setSpitter(spitter);
        return spittle;
    }

    @Override
    @PostFilter("filterObject.spitter.username == authentication.principal")
    public List<Spittle> getAllSpittles() {
        List<Spittle> spittles = new ArrayList<Spittle>();

        Spitter spitter = new Spitter();
        spitter.setId(1L);
        spitter.setUsername("user");
        spitter.setPassword("password");

        Spitter admin = new Spitter();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("password");

        for (int i = 0; i < 3; i++) {
            Spittle spittle = new Spittle();
            spittle.setId((long)i);
            spittle.setMessage("Hello World");
            spittle.setPostedTime(new Date());
            spittle.setSpitter(spitter);

            spittles.add(spittle);

            Spittle spittle2 = new Spittle();
            spittle2.setId((long)i);
            spittle2.setMessage("Hello World");
            spittle2.setPostedTime(new Date());
            spittle2.setSpitter(admin);

            spittles.add(spittle2);
        }

        return spittles;
    }

    @PreFilter("hasRole('ROLE_ADMIN') or filterObject.spitter.username == authentication.principal")
    public void deleteSpittles(List<Spittle> spittles) {
        return;
    }
    
}
```

## 14.4 Creating securing expression evaluators

```java
public class SpittlePermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (targetDomainObject instanceof Spittle) {
            Spittle spittle = (Spittle) targetDomainObject;
            String username = spittle.getSpitter().getUsername();
            if ("delete".equals(permission)) {
                return isAdmin(authentication) || username.equals(authentication.getName());
            }
        }
        throw new UnsupportedOperationException("hasPermission method not supported for object " + targetDomainObject + " with permission " + permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException("hasPermission method not supported for object " + targetId + " with permission " + permission);
    }


    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}

// register evaluator in SecuredConfig.java 
@Override
protected MethodSecurityExpressionHandler createExpressionHandler() {
    DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
    expressionHandler.setPermissionEvaluator(new SpittlePermissionEvaluator());
    return expressionHandler;
}

// use hasPermission(filterObject, 'delete') in @PreFilter 
@PreFilter("hasPermission(filterObject, 'delete')")
public int updateSpittle(List<Spittle> spittle) {
    return spittle.size();
}
```