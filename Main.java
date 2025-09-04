import java.util.ArrayList;
import java.util.List;

import lib.RuleManagerRunner;
import lib.models.Expense;
import lib.models.ExpenseType;
import lib.services.impl.SimpleRuleEngine;

public class Main {
    public static void main(String[] args) {
        List<Expense> expenses = new ArrayList<>();

        expenses.add(new Expense("1", "1", 10.0, ExpenseType.RESTAURANT));
        expenses.add(new Expense("2", "1", 50.0, ExpenseType.RESTAURANT));
        expenses.add(new Expense("3", "1", 100.0, ExpenseType.RESTAURANT));

        RuleManagerRunner ruleManagerRunner = new RuleManagerRunner(new SimpleRuleEngine());

        ruleManagerRunner.run(expenses);
    }
}
