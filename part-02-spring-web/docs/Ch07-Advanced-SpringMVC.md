# 7. Advanced Spring MVC

- Alternative Spring MVC configuration options
- Handle file uploads
- Handle exceptions in controllers
- Working with flash attributes

## 7.1 Alternate Spring MVC configuration

## 7.1.1 Customizing DispatcherServlet configuration

Method1 : customizeRegistration(Dynamic registration)

```java
import javax.servlet.MultipartConfigElement;

@Override
protected void customizeRegistration(Dynamic registration) {
    registration.setMultiPartConfig(
            new MultipartConfigElement("/tmp/spittr/uploads")
    );
}
```

### 7.1.2 