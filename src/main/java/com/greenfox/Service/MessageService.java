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
  final String URL =  System.getenv("CHAT_APP_PEER_ADDRESSS") + "/api/message/receive";
  RestTemplate restTemplate = new RestTemplate();

  public Message createMessageObjectFromText(String messageText) {
    String username = userRepository.findOne((long) 1).getName();
    System.out.println("messageet csinal");
    return new Message(username, messageText);
  }

  public void saveAndSandMessage(String messageText) {
    Message message = createMessageObjectFromText(messageText);
    messageRepository.save(message);
    System.out.println("messaget lement");
    MessageWithClientId mwci = new MessageWithClientId(message, new Client());
    restTemplate.postForLocation(URL, mwci);
    System.out.println("messaget tovabbit");
  }

  public boolean checkIfMessageWasSentByUser(MessageWithClientId mwci) {
    if(!mwci.getClient().equals(System.getenv("CHAT_APP_UNIQUE_ID"))) {
      return true;
    }
    return true;
  }

  public void saveAndSandMessageWithClientId(MessageWithClientId mwci) {
    messageRepository.save(mwci.getMessage());
    restTemplate.postForLocation(URL, mwci);
  }
}
