package com.maui.productivity.financial.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Tag {
    private final String name;
    private final String value;

    @JsonCreator
    public Tag(@JsonProperty("name") String name, @JsonProperty("value") String value) {
        this.name = name;
        this.value = value;
    }
}
