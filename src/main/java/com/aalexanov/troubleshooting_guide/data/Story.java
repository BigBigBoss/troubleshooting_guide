package com.aalexanov.troubleshooting_guide.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@Data
public class Story {

    private String userName;
    private String topic;
    private String title;
    private String text;
}
