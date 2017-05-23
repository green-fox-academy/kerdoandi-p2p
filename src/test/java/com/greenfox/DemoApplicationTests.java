package com.greenfox;

import com.greenfox.model.Client;
import com.greenfox.model.Message;
import com.greenfox.model.MessageWithClientId;
import com.greenfox.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testStatusIsOk() throws Exception {
    mockMvc.perform(get("/enter"))
            .andExpect(status().isOk());
  }

  @Test
  public void testSendMessage() throws Exception {
    MessageWithClientId mwci = new MessageWithClientId(new Message("Andi", "alma"), new Client());

    mockMvc.perform(post("/api/message/receive")
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ready").value(true))
            .andExpect(jsonPath("$.shipstatus").value("full"));
  }


}