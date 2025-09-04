package lib.services.rules;

import java.util.Optional;

import lib.models.Expense;

public interface ExpenseRule {
    Optional<Violation> check(Expense e);
}
