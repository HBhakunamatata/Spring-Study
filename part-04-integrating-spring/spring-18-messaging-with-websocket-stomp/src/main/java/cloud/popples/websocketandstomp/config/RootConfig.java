package cloud.popples.websocketandstomp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Configuration
@ComponentScan(basePackages = {"cloud.popples.websocketandstomp"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {EnableWebMvc.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {EnableWebSocket.class})
})
public class RootConfig {
}
