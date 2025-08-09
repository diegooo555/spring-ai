package dev.diego.springiaworkshop.promp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    private final ChatClient chatClient;

    public ArticleController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/posts/new")
    public String newPost(@RequestParam( value = "topic", defaultValue = "JDK virtual Threads") String topic) {
        String system = """
            Create a small but interesting post. Give me examples and real use cases.
        """;

        return chatClient.prompt()
                .user(u -> {
                    u.text("Write me a text about {topic}");
                    u.param("topic", topic);
                })
                .system(system)
                .call()
                .content();
    }
}
