package com.aalexanov.troubleshooting_guide;

import com.aalexanov.troubleshooting_guide.rest.StoryEvaluatorController;
import com.aalexanov.troubleshooting_guide.service.TheUltimateArtificialIntelligenceStoryEvaluator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {StoryEvaluatorController.class,
    TheUltimateArtificialIntelligenceStoryEvaluator.class})
public class TroubleshootingGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(TroubleshootingGuideApplication.class, args);
    }
}