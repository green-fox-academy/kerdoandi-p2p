package com.greenfox;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.model.Client;
import com.greenfox.model.Message;
import com.greenfox.model.MessageWithClientId;
import com.greenfox.model.User;
import com.greenfox.repository.MessageRepository;
import com.greenfox.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
@EnableWebMvc
public class DemoApplicationTests {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          Charset.forName("utf8"));

  private MockMvc mockMvc;
  private User user;
  private Message message;
  private Client client;
  private MessageWithClientId mwci;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  MessageRepository messageRepository;

  @Autowired
  UserRepository userRepository;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
    this.user = new User("andi");
    this.client = new Client();
    client.setId("andi");
    this.message = new Message(user.getName(), "test");
    this.mwci = new MessageWithClientId(this.message, this.client);
  }

  @Test
  public void testStatusIsOk() throws Exception {
    mockMvc.perform(get("/enter"))
            .andExpect(status().isOk());
  }

  @Test
  public void testSendMessage() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsoninput = objectMapper.writeValueAsString(mwci);

    System.out.println(jsoninput);

    mockMvc.perform(post("/api/message/receive")
            .contentType(contentType)
            .content(jsoninput))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status").value("ok"));

  }



}