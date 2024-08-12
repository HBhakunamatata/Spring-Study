package cloud.popples.marcopolo;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021年11月24日 14:03
 */
public class MarcoHandler extends AbstractWebSocketHandler {

    private static final Logger logger = Logger.getLogger(MarcoHandler.class);

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        // 1.记录ws接收的参数
        logger.info("Received message : " + message);

        // 2.模拟网络延时 2s
        Thread.sleep(2000);
        
        // 3.回复信息
        session.sendMessage(new TextMessage("Polo!"));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Connection established .. ");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("Connection status : " + status);
    }


}
