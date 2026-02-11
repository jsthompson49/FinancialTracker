package com.maui.productivity.financial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaggedTransaction {
    private Transaction transaction;
    private Set<Tag> tags;
}
