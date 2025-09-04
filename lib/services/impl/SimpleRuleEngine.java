package lib.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lib.models.Expense;
import lib.models.ExpenseType;
import lib.services.RuleEngine;
import lib.services.rules.ExpenseRule;
import lib.services.rules.TripRule;
import lib.services.rules.Violation;

public class SimpleRuleEngine implements RuleEngine {
    @Override
    public List<Violation> evaluate(
            List<Expense> expenses,
            Map<ExpenseType, List<ExpenseRule>> expenseRulesRegistry,
            List<ExpenseRule> allExpenseRulesRegistry,
            List<TripRule> tripRulesRegistry) {

        List<Violation> violationsResut = new ArrayList<>();

        // step 1: Check all expenses against all expense rules
        for (Expense expense : expenses) {
            // Fetch all the rules for the expense type, if no rules are found, then we have
            // empty list
            List<ExpenseRule> rules = expenseRulesRegistry.getOrDefault(expense.getExpenseType(), List.of());

            checkExpenseAgainstRules(expense, rules, violationsResut);
            checkExpenseAgainstRules(expense, allExpenseRulesRegistry, violationsResut);
        }

        // step 2: Check all expenses against all trip rules
        for (TripRule rule : tripRulesRegistry) {
            Optional<Violation> violation = rule.check(expenses);
            if (violation.isPresent()) {
                violationsResut.add(violation.get());
            }
        }

        return violationsResut;
    }

    private void checkExpenseAgainstRules(Expense expense, List<ExpenseRule> rules, List<Violation> violationsResult) {
        for (ExpenseRule rule : rules) {
            Optional<Violation> violation = rule.check(expense);
            if (violation.isPresent()) {
                violationsResult.add(violation.get());
            }
        }
    }
}
