package cloud.popples.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


@Configuration
// 配置类不仅配置了WebSocket，还配置了基于代理的STOMP信息
@EnableWebSocketMessageBroker
public class WebSocketStompConfig
    extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * broker本地设置
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue", "/topic");
        registry.setUserDestinationPrefix("/app");
    }

    /**
     * broker中继配置
     * @param registry
     */
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        String host = "rabbit.someotherserver";
//        Integer port = 61613; // default
//        String login = "guest"; // default
//        String passcode = "guest"; // default
//
//        registry.enableStompBrokerRelay("/queue", "/topic")
//            .setRelayHost(host)
//            .setRelayPort(port)
//            .setClientLogin(login)
//            .setClientPasscode(passcode);
//        // If you can use the default value
//        // you can just ignore the item.
//
//        registry.setUserDestinationPrefix("/app");
//    }


    /**
     * 端点设置
     * 将"/marcopolo"注册为STOMP端点。
     * 这个路径与之前发送和接收消息的目的地路径有所不同。
     * 这是一个端点，客户端在订阅或发布消息到目的地路径前，要连接它。
     * @param registry
     */
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 为 "/marcopolo" 路径添加SockJS支持
        registry.addEndpoint("/marcopolo").withSockJS();
    }


    /**
     * 1、设置拦截器
     * 2、首次连接的时候，获取其Header信息，利用Header里面的信息进行权限认证
     * 3、通过认证的用户，使用 accessor.setUser(user); 方法，将登陆信息绑定在该 StompHeaderAccessor 上，在Controller方法上可以获取 StompHeaderAccessor 的相关信息
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptorAdapter() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //1、判断是否首次连接
                if (StompCommand.CONNECT.equals(accessor.getCommand())){
                    //2、判断用户名和密码
                    String username = accessor.getNativeHeader("username").get(0);
                    String password = accessor.getNativeHeader("password").get(0);

                    if ("admin".equals(username) && "admin".equals(password)){
                        Principal principal = new Principal() {
                            @Override
                            public String getName() {
                                return userName;
                            }
                        };
                        accessor.setUser(principal);
                        return message;
                    }else {
                        return null;
                    }
                }
                //不是首次连接，已经登陆成功
                return message;
            }

        });
    }
}
