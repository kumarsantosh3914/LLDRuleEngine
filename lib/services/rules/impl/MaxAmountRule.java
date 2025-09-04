package lib.services.rules.impl;

import java.util.Optional;

import lib.models.Expense;
import lib.services.rules.ExpenseRule;
import lib.services.rules.Violation;

public class MaxAmountRule implements ExpenseRule {
    private final double maxAmount;

    public MaxAmountRule(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public Optional<Violation> check(Expense e) {
        if (e.getAmountIUsd() > maxAmount) {
            return Optional.of(Violation.of("Expense amount is greater than the max amount" + " id:" + e.getExpenseId()));
        }

        return Optional.empty();
    }
}
