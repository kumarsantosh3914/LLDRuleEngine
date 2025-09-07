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
            if(maxAmount == 75) {
                return Optional.of(Violation.of("Restaurant expense exceeds $75"));
            }
            if(maxAmount == 250) {
                return Optional.of(Violation.of("Expense exceeds $20 limit"));
            }
        }

        return Optional.empty();
    }
}
