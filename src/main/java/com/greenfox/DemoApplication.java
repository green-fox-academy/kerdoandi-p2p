package com.greenfox;

import com.greenfox.model.Message;
import com.greenfox.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
@Autowired
MessageRepository messageRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		messageRepository.save(new Message("App","Hi there! Submit your message using the send button!"));
	}
}
