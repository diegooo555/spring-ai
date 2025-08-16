package dev.diego.springiaworkshop.tools.action;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskManagementTools {
    public record TaskResult(
            Long taskId,
            String title,
            String status,
            String assignee,
            String message
    ) {}

    public enum TaskStatus {
        PENDING, IN_PROGRESS, COMPLETES, CANCELLED
    }

    private final AtomicLong taskIdGenerator = new AtomicLong(1);
    private record Task(Long id, String title, String description, String assignee, TaskStatus message) {}

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();

    @Tool(description = "Create a new task with title, description, and assignee")
    public TaskResult createTask(String title, String description, String assignee) {
        Long taskId = taskIdGenerator.getAndIncrement();
        Task task = new Task(taskId, title, description, assignee, TaskStatus.PENDING);
        tasks.put(taskId, task);
        return new TaskResult(taskId, title, "PENDING", assignee, description);
    }
}
