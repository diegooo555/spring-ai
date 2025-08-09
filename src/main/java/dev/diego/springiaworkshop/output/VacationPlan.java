package dev.diego.springiaworkshop.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPlan {
    private final ChatClient chatClient;

    public VacationPlan(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/vacation/unstructured")
    public String vacationPlanUnstructured(){
        return chatClient.prompt()
                .user("Give me plans to go on vacation.")
                .call().content();
    }


    @GetMapping("/vacation/structured")
    public Itenerary vacationsStructured(){
        return chatClient.prompt()
                .user("Give me plans to go on vacation.")
                .call()
                .entity(Itenerary.class);

    }
}
