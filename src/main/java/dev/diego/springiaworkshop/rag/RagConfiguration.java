package dev.diego.springiaworkshop.rag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RagConfiguration {

    private final Logger log = LoggerFactory.getLogger(RagConfiguration.class);
    private final String vectorStoreName = "vectorstore.json";
}
