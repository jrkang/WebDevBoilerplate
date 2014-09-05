package me.idess.web.controller.websocket;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;

import me.idess.web.controller.AuthenticationController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
	
	private static final Logger	logger	= LoggerFactory.getLogger(AuthenticationController.class);
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
			throws Exception {
		String payloadMessage = (String) message.getPayload();
		int i = 0;
		try {
			while(true && session.isOpen()) {
				session.sendMessage(new TextMessage("ECHO: " + payloadMessage + " " + i++));
				logger.debug("ECHO: " + payloadMessage + " " + i);
				Thread.sleep(1*1000);
			}
		} catch (IOException e) {
			if (session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
}
