package lib.registry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lib.models.ExpenseType;
import lib.services.rules.ExpenseRule;
import lib.services.rules.TripRule;
import lib.services.rules.impl.DisallowRule;
import lib.services.rules.impl.MaxAmountRule;
import lib.services.rules.impl.TripTotalMaxRule;

public class RuleRegistry {
    public static Map<ExpenseType, List<ExpenseRule>> getExpenseRulesRegistry() {
        Map<ExpenseType, List<ExpenseRule>> registry = new HashMap<>();

        registry.put(ExpenseType.RESTAURANT, List.of(
                new MaxAmountRule(75)));

        registry.put(ExpenseType.AIRFARE, List.of(
                new DisallowRule()));

        registry.put(ExpenseType.ENTERTAINMENT, List.of(
                new DisallowRule()));

        return registry;
    }

    public static List<ExpenseRule> getAllExpenseRulesRegistry() {
        return List.of(
                new MaxAmountRule(250));
    }

    public static List<TripRule> getAllTripRulesRegistry() {
        return List.of(
                new TripTotalMaxRule(1000));
    }
}
