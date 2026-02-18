package com.maui.productivity.financial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaggedTransaction {
    public static final Function<TaggedTransaction, LocalDate> DATE_EXTRACTOR =
            taggedTransaction -> taggedTransaction.getTransaction().getDate();
    public static final Function<TaggedTransaction, String> DESCRIPTION_EXTRACTOR =
            taggedTransaction -> taggedTransaction.getTransaction().getDescription();

    private Transaction transaction;
    private Set<Tag> tags;
}
