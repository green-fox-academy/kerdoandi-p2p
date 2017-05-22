package com.greenfox.Service;

import com.greenfox.model.MessageWithClientId;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendMessageService {
  final String URL =  System.getenv("CHAT_APP_PEER_ADDRESSS") + "/api/message/receive";
  RestTemplate restTemplate = new RestTemplate();

  public void sendMessage(MessageWithClientId mwci) {
    restTemplate.postForLocation(URL, mwci);

  }
}
