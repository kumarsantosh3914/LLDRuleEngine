package lib.services.rules.impl;

import java.util.Optional;

import lib.models.Expense;
import lib.services.rules.ExpenseRule;
import lib.services.rules.Violation;

public class DisallowRule implements ExpenseRule {
    
    @Override
    public Optional<Violation> check(Expense e) {
        return Optional.of(Violation.of("Expense type " + e.getExpenseType() + " is not allowed"));
    }
}
