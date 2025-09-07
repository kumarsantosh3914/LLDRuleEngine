package lib.services.rules.impl;

import java.util.Optional;

import lib.models.Expense;
// import lib.models.ExpenseType;
import lib.services.rules.ExpenseRule;
import lib.services.rules.Violation;

public class DisallowRule implements ExpenseRule {
    
    @Override
    public Optional<Violation> check(Expense e) {
        String type = e.getExpenseType().name();
        if("AIRFARE".equals(type)) {
            return Optional.of(Violation.of("Airfare expenses not allowed"));
        }
        if("ENTERTAINMENT".equals(type)) {
            return Optional.of(Violation.of("Entertainment expenses not allowed"));
        }

        return Optional.of(Violation.of(type + " expenses not allowed")); 
    }
}
