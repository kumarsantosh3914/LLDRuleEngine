import java.util.ArrayList;
import java.util.List;

import lib.RuleManagerRunner;
import lib.models.Expense;
import lib.models.ExpenseType;
import lib.services.impl.SimpleRuleEngine;

public class Main {
    public static void main(String[] args) {
        List<Expense> expenses = new ArrayList<>();

        expenses.add(new Expense("001", "trip1", 80.0, ExpenseType.RESTAURANT, "Outback Roadhouse"));
        expenses.add(new Expense("002", "trip1", 120.0, ExpenseType.valueOf("SUPPLIES"), "Staples"));
        expenses.add(new Expense("003", "trip1", 199.0, ExpenseType.AIRFARE, "Delta Airlines"));
        expenses.add(new Expense("004", "trip1", 260.0, ExpenseType.valueOf("HOTEL"), "Marriott"));
        expenses.add(new Expense("005", "trip1", 70.0, ExpenseType.RESTAURANT, "Chipotle"));
        expenses.add(new Expense("006", "trip1", 40.0, ExpenseType.ENTERTAINMENT, "AMC Theaters"));

        RuleManagerRunner ruleManagerRunner = new RuleManagerRunner(new SimpleRuleEngine());

        ruleManagerRunner.run(expenses);
    }
}
