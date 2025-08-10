package dev.diego.springiaworkshop.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelController {

    private final ChatClient chatClient;

    public ModelController(ChatClient.Builder chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();

    }

    @GetMapping("/rag/models")
    public Models rag(@RequestParam(name = "message", defaultValue = "Give me a list of all models of OpenaAI along the context window" ) String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(Models.class);
    }
}
