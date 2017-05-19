package com.greenfox.Service;

import com.greenfox.model.MessageWithClientId;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

@Service
public class ReceivedMessageValidator {
  private String missingParams;

  public ReceivedMessageValidator() {
    missingParams = "";
  }

  public void setMissingParams(MessageWithClientId messageWithClientId) {
    int id = messageWithClientId.getMessage().getId();
    String username = messageWithClientId.getMessage().getUsername();
    String text = messageWithClientId.getMessage().getText();
    Timestamp timestamp = messageWithClientId.getMessage().getTimestamp();
    String userid = messageWithClientId.getClient().getId();

    if (id == 0) {
      missingParams += "message.id, ";
    }
    if (username == null || username.isEmpty()) {
      missingParams += "message.username, ";
    }
    if (text == null || text.isEmpty()) {
      missingParams += "message.text, ";
    }
    if (timestamp == null) {
      missingParams += "message.timestamp, ";
    }
    if (userid == null || userid.isEmpty()) {
      missingParams += "client.id.";
    }
  }

  public String getMissingParams() {
    return missingParams;
  }
}
