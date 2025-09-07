package lib;

import java.util.List;
import java.util.Map;

import lib.models.Expense;
import lib.models.ExpenseType;
import lib.registry.RuleRegistry;
import lib.services.RuleEngine;
import lib.services.rules.ExpenseRule;
import lib.services.rules.TripRule;
import lib.services.rules.Violation;

public class RuleManagerRunner {
    private final RuleEngine ruleEngine;

    public RuleManagerRunner(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    // }
    public void run(List<Expense> expenses) {
        Map<ExpenseType, List<ExpenseRule>> expenseRulesRegistry = RuleRegistry.getExpenseRulesRegistry();
        List<ExpenseRule> allExpenseRulesRegistry = RuleRegistry.getAllExpenseRulesRegistry();
        List<TripRule> tripRulesRegistry = RuleRegistry.getAllTripRulesRegistry();

        System.out.println("Expense Violations:");
        for (Expense expense : expenses) {
            List<String> reasons = new java.util.ArrayList<>();
            for (ExpenseRule r : expenseRulesRegistry.getOrDefault(expense.getExpenseType(), List.of())) {
                r.check(expense).ifPresent(v -> reasons.add(v.getMessage()));
            }
            for (ExpenseRule r : allExpenseRulesRegistry) {
                r.check(expense).ifPresent(v -> reasons.add(v.getMessage()));
            }
            if (reasons.isEmpty()) {
                System.out.println(expense.getExpenseId() + " -> APPROVED");
            } else {
                System.out.println(expense.getExpenseId() + " -> REJECTED: [" + reasons.get(0) + "]");
            }
        }

        System.out.println("Trip Violations:");
        List<String> tripReasons = new java.util.ArrayList<>();
        for (TripRule r : tripRulesRegistry) {
            r.check(expenses).ifPresent(v -> tripReasons.add(v.getMessage()));
        }
        String tripId = expenses.isEmpty() ? "" : expenses.get(0).getTripId();
        if (tripReasons.isEmpty()) {
            System.out.println(tripId + " -> OK");
        } else {
            System.out.println(tripId + " -> VIOLATIONS: [" + String.join("; ", tripReasons) + "]");
        }
    }
}