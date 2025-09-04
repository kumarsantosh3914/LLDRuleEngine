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

        // Per-expense results
        for (Expense expense : expenses) {
            List<String> reasons = new java.util.ArrayList<>();

            for (ExpenseRule rule : expenseRulesRegistry.getOrDefault(expense.getExpenseType(), List.of())) {
                java.util.Optional<Violation> v = rule.check(expense);
                v.ifPresent(x -> reasons.add(x.getMessage()));
            }
            for (ExpenseRule rule : allExpenseRulesRegistry) {
                java.util.Optional<Violation> v = rule.check(expense);
                v.ifPresent(x -> reasons.add(x.getMessage()));
            }

            String status = reasons.isEmpty() ? "APPROVED" : "REJECTED";
            System.out.println("Expense " + expense.getExpenseId() + ": " + status);
            if (!reasons.isEmpty()) {
                for (String r : reasons)
                    System.out.println(" - " + r);
            }
        }

        // Per-trip results
        List<String> tripReasons = new java.util.ArrayList<>();
        for (TripRule rule : tripRulesRegistry) {
            java.util.Optional<Violation> v = rule.check(expenses);
            v.ifPresent(x -> tripReasons.add(x.getMessage()));
        }

        String tripStatus = tripReasons.isEmpty() ? "OK" : "VIOLATIONS";
        System.out.println("Trip: " + tripStatus);
        if (!tripReasons.isEmpty()) {
            for (String r : tripReasons)
                System.out.println(" - " + r);
        }
    }
}