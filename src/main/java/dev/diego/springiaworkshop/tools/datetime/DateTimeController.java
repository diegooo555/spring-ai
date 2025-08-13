package dev.diego.springiaworkshop.tools.datetime;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateTimeController {

    private final ChatClient chatClient;

    public DateTimeController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/tools")
    public String tools() {
        return chatClient.prompt()
                .user("What is tomorrowś date?")
                .tools(new DateTimeTools())
                .call()
                .content();
    }

}
