package com.greenfox.Service;

import com.greenfox.model.Client;
import com.greenfox.model.Message;
import com.greenfox.model.MessageWithClientId;
import com.greenfox.repository.MessageRepository;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {
  @Autowired
  MessageRepository messageRepository;
  @Autowired
  UserRepository userRepository;

  String URL =  System.getenv("CHAT_APP_PEER_ADDRESSS") + "/api/message/receive";
  RestTemplate restTemplate = new RestTemplate();

  public Message createMessageObjectFromText(String messageText) {
    String username = userRepository.findOne((long) 1).getName();
    return new Message(username, messageText);
  }

  public void saveAndSendMessage(String messageText) {
    Message message = createMessageObjectFromText(messageText);
    messageRepository.save(message);
    Client client = new Client();
    client.setId(System.getenv("CHAT_APP_UNIQUE_ID"));
    MessageWithClientId mwci = new MessageWithClientId(message, client);
    restTemplate.postForObject(URL, mwci, MessageStatusOK.class);
  }

  public boolean checkIfMessageWasSentByUser(MessageWithClientId mwci) {
    return mwci.getClient().getId().equals(System.getenv("CHAT_APP_UNIQUE_ID"));
  }

  public void saveAndSendMessageWithClientId(MessageWithClientId mwci) {
    messageRepository.save(mwci.getMessage());
    restTemplate.postForObject(URL, mwci, MessageStatusOK.class);
  }
}
