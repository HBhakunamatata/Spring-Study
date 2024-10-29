# Ch09 Securing Web Application

- Introducing Spring Security
- Configure a user store (Authentication)
- Specify which requests should and should not require authentication, as well as
  what authorities they require
- Provide a custom login screen to replace the plain default login screen

[//]: # (- Securing web applications using servlet filters)

[//]: # (- Authentication against databases and LDAP)

## 9.1 Getting started with Spring Security

Security is a concern that transcends an application's functionality.  
It is better to keep security concerns separate from your applications concerns.

Spring Security tackles security from two angles.  
To secure web requests and restrict access at the URL level, Spring Security uses servlet filters. 
Spring Security can also secure method invocations using Spring AOP, proxying objects and applying advice to ensure that the user has the proper authority to invoke secured
methods.

### 9.1.1 Understanding Spring Security modules

### 9.1.2 Filtering web requests

```java
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
}
```

AbstractSecurityWebApplicationInitializer --> DelegatingFilterProxy --> springSecurityFilterChain

### 9.1.3 Writing a simple security configuration

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {}

// When using SpringMVC
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
}
```

@EnableWebMvcSecurity

- handler methods can receive the authenticated user’s principal (or username) via @AuthenticationPrincipal-annotated parameters.
- configures a bean that automatically adds a hidden cross-site request forgery(CSRF) token field on forms using Spring’s form-binding tag library.

### 9.1.4 Three override methods in WebSecurityConfigurerAdapter

| Override Method | Description |
|:---:|---|
| configure(WebSecurity) | Override to configure Spring Security’s filter chain. |
| configure(HttpSecurity) | Override to configure how requests are secured by interceptors. |
| configure(AuthenticationManagerBuilder) | Override to configure user-details services. |

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .httpBasic();
}
```

## 9.2 Selecting user details services

### 9.2.1 Working with an in-memory user store

```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .withUser("user").password("123456").roles("USER")
            .and()
            .withUser("admin").password("123456").roles("ADMIN", "USER");
}
```


### 9.2.2 Authenticating against database tables

```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, true from spitter where username = ?")
            .authoritiesByUsernameQuery("select username, 'ROLE_USER' from spitter where username = ?")
                .passwordEncoder(new StandardPasswordEncoder())
    ;
}
```

### 9.2.3 Configuring a custom user service

```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(new SpitterUserService(repository));
}
```


## 9.3 Configure requests intercepting (HttpSecurity)

```java
.antMatchers(HttpMethod.POST, "/spittles").authenticated()
.antMatchers("/spitters/**").authenticated();
.regexMatchers("/spitters/.*").authenticated();
```

### 9.3.1 Securing with Spring Expressions

intercepting requests with multi-condition

```java
.antMatchers("/spitter/me").access("hasRole('ROLE_SPITTER')")
```

### 9.3.2 Enforcing channel security

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/spitter/me").hasRole("SPITTER")
        .antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER")
        .anyRequest().permitAll()
        .and()
        .requiresChannel()
        .antMatchers("/spitter/form").requiresSecure();
}
```

### 9.3.3 Preventing cross-site request forgery

```
@Override
protected void configure(HttpSecurity http) throws Exception {
      http
      ...
      .csrf()
      .disable();  // close csrf token
}
```

## 9.4 Authentication Users

### 9.4.1 Customize login page

```
.formLogin().loginPage("/login")
```

### 9.4.2 Enabling HTTP Basic authentication

```
.httpBasic().realmName("Spittr")
```

### 9.4.3 Remember me

```
.rememberMe().tokenValiditySeconds(24 * 60 * 60).key("spitterKey")

<input id="remember_me" name="remember-me" type="checkbox"/>
<label for="remember_me" class="inline">Remember me</label></td></tr>
```

### 9.4.4. Logout

```
.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).logoutSuccessUrl("/")
```

