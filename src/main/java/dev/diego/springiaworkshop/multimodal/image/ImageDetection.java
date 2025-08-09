package dev.diego.springiaworkshop.multimodal.image;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageDetection {

    private final ChatClient chatClient;
    @Value("classpath:/images/horse.jpeg")
    Resource sampleImage;

    public ImageDetection(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/image-to-text")
    public String imageToText() {
        return  chatClient.prompt()
                .user( u -> {
                    u.text("Can you please describe ");
                    u.media(MimeTypeUtils.IMAGE_JPEG, sampleImage);
                })
                .call()
                .content();
    }

}
