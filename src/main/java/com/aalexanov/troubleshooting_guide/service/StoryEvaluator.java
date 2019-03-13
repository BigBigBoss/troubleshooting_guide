package com.aalexanov.troubleshooting_guide.service;

import com.aalexanov.troubleshooting_guide.data.Customer;
import com.aalexanov.troubleshooting_guide.data.Story;

public interface StoryEvaluator {

    Integer evaluateStory(Customer customer, Story joke);
}
