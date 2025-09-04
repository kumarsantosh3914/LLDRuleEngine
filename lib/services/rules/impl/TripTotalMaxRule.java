package lib.services.rules.impl;

import java.util.List;
import java.util.Optional;

import lib.services.rules.TripRule;
import lib.services.rules.Violation;
import lib.models.Expense;
import lib.utils.ExpenseUtils;

public class TripTotalMaxRule implements TripRule {
    private final double maxAmount;

    public TripTotalMaxRule(double  maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public Optional<Violation> check(List<Expense> expenses) {
        if (!ExpenseUtils.areAllExpensesOfSameTrip(expenses)) {
            return Optional.of(Violation.of("Expenses are not of the same trip"));
        }

        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmountIUsd();
        }
        if(total > maxAmount) {
            return Optional.of(Violation.of("Trip total exceeds the maximum amount"));
        }

        return Optional.empty();
    }
}
