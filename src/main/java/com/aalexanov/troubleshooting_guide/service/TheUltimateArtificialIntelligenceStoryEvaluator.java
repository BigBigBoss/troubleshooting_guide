package com.aalexanov.troubleshooting_guide.service;

import static java.util.Arrays.stream;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;

import com.aalexanov.troubleshooting_guide.data.Customer;
import com.aalexanov.troubleshooting_guide.data.Story;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class TheUltimateArtificialIntelligenceStoryEvaluator implements StoryEvaluator {

    private final Cache<Customer, Set<Story>> data = CacheBuilder.newBuilder()
        .softValues()
        .build();

    public Integer evaluateStory(Customer customer, Story story) {
        Set<Story> stories = cacheStory(customer, story);
        Set<String> vocabulary = stream(story.getText().split("\\W")).collect(toSet());
        return multipliedByFactor(customer, stories, story, vocabulary);
    }

    private Set<Story> cacheStory(Customer customer, Story story) {
        Set<Story> cachedStories = data.getIfPresent(customer);
        Set<Story> stories = mergeSet(cachedStories, singleton(story));
        data.put(customer, stories);
        return stories;
    }

    private Integer multipliedByFactor(Customer customer, Set<Story> stories,
                                       Story story,
                                       Set<String> vocabulary) {
        double vocabularyQualityFactor = 1.0f;
        for (String word : vocabulary) {
            if (word.length() > 5) {
                vocabularyQualityFactor += 0.05;
            }
        }

        return (int) Math.round(customer.getRating() *
            vocabularyQualityFactor *
            stories.size() *
            ThreadLocalRandom.current().nextInt(10, 50));
    }

    private <E> Set<E> mergeSet(Set<E> set1, Set<E> set2) {
        return Stream.of(set1, set2)
            .filter(Objects::nonNull)
            .flatMap(Collection::stream)
            .collect(toSet());
    }

}
