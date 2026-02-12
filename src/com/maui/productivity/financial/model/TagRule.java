package com.maui.productivity.financial.model;

import java.util.Set;
import java.util.function.Function;

public interface TagRule extends Function<Transaction, Set<Tag>> {

}
