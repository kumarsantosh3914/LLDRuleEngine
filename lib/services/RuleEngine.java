package lib.services;

import java.util.List;
import java.util.Map;
import lib.services.rules.ExpenseRule;
import lib.services.rules.TripRule;
import lib.services.rules.Violation;
import lib.models.Expense;
import lib.models.ExpenseType;

public interface RuleEngine {
    List<Violation> evaluate(
            List<Expense> expenses,
            Map<ExpenseType, List<ExpenseRule>> expenseRulesRegistry,
            List<ExpenseRule> allExpenseRulesRegistry,
            List<TripRule> tripRulesRegistry);

}