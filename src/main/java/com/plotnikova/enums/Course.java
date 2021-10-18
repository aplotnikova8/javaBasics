package com.plotnikova.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Course {

    JAVA("Java", 16),
    JDBC("JDBC", 24),
    SPRING("Spring", 16),
    TEST_DESIGN("Test design", 10),
    PAGE_OBJECT("Page object", 16),
    SELENIUM("Selenium", 16),
    AGILE("Agile", 7);

    String name;
    int duration;

}
