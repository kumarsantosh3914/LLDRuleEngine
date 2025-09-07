package lib.services.rules.impl;

import java.util.List;
import java.util.Optional;

import lib.models.Expense;
import lib.models.ExpenseType;
import lib.services.rules.TripRule;
import lib.services.rules.Violation;
import lib.utils.ExpenseUtils;

public class TripMealTotalMaxRule implements TripRule {
    private final double maxAmount;

    public TripMealTotalMaxRule(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public Optional<Violation> check(List<Expense> expenses) {
        if(!ExpenseUtils.areAllExpensesOfSameTrip(expenses)) {
            return Optional.of(Violation.of("Expense are not of the same trip"));
        }

        double total = 0;
        for(Expense e: expenses) {
            if(e.getExpenseType() == ExpenseType.RESTAURANT) {
                total += e.getAmountIUsd();
            }
        }
        if(total > maxAmount) {
            return Optional.of(Violation.of("Total meal (restaurant) expenses $" + (int) total + " exceed $1000"));
        }

        return Optional.empty();
    }
}
