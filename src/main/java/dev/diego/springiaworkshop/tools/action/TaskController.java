package dev.diego.springiaworkshop.tools.action;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private final ChatClient chatClient;
    private final TaskManagementTools taskManagementTools;

    public TaskController(ChatClient.Builder chatClient, TaskManagementTools taskManagementTools) {
        this.chatClient = chatClient.build();
        this.taskManagementTools = taskManagementTools;
    }

    @GetMapping("/task/action")
    public String createTask(@RequestParam String message) {
        return chatClient.prompt()
                .tools(taskManagementTools)
                .user(message)
                .call()
                .content();
    }
}
