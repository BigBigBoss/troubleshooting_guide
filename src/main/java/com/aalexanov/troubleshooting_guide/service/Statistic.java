package com.aalexanov.troubleshooting_guide.service;

import com.aalexanov.troubleshooting_guide.data.Customer;
import com.aalexanov.troubleshooting_guide.data.Story;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Statistic {
   private Customer customer;
   private Story story;
   private LocalDateTime dateTime;
   private Integer storyTotalCount;
   private Integer vocabularyCount;
   private Integer price;
   private String evaluatorVersion;

}
