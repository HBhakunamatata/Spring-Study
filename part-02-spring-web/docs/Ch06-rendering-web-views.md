# Ch06 Rendering web views

## 6.1 Understanding view resolution

Decoupling request-handling logic in the controller from the view-rendering of a view is an important feature of Spring MVC.  
If the controller methods were directly responsible for producing HTML, it would be difficult to maintain and update the view without getting your hands dirty in request-handling logic. At most, the controller methods and view implementations should agree on the contents of the model; apart from that, they should keep an arms-length distance from each other.  
But if the controller only knows about the view by a logical view name, how does Spring determine which actual view implementation it should use to render the model? That’s a job for Spring’s view resolvers.

```java
public interface ViewResolver {
    View resolveViewName(String viewName, Locale locale) throws Exception;
}
```

The View interface’s job is to take the model, as well as the servlet request and response objects, and render output into the response.

```java
public interface View {
    String getContentType();
    void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
```


## 6.4 Working with Thymeleaf

1. One significant issue with JSP is that it appears to be a form of HTML or XML, but it’s really neither.
2. Also, JSP is a specification that’s tightly coupled to the servlet specification.

### 6.4.1 Configuring a Thymeleaf view resolver

