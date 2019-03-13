package com.aalexanov.troubleshooting_guide.rest;

import com.aalexanov.troubleshooting_guide.data.Customer;
import com.aalexanov.troubleshooting_guide.data.Story;
import com.aalexanov.troubleshooting_guide.service.StoryEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storyEvaluator")
public class StoryEvaluatorController {

    @Autowired
    private StoryEvaluator storyEvaluator;

    @PostMapping("/story")
    public String calcStoryPrice(@RequestBody StoryHolder jh) {
        return storyEvaluator.evaluateStory(jh.getCustomer(), jh.getStory()) +
            " dollars has been transferred to your account!!!";
    }

    private static class StoryHolder {

        private Customer customer;
        private Story story;

        public StoryHolder(Customer customer, Story story) {
            this.customer = customer;
            this.story = story;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public Story getStory() {
            return story;
        }

        public void setStory(Story story) {
            this.story = story;
        }
    }

}
