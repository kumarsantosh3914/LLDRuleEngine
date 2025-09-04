package lib.models;

public class Expense {
    private final String expenseId;
    private final String tripId;
    private final Double amountIUsd;
    private final ExpenseType expenseType;

    public Expense(String expenseId, String tripId, Double amountUsd, ExpenseType expenseType) {
        this.expenseId = expenseId;
        this.tripId = tripId;
        this.amountIUsd = amountUsd;
        this.expenseType = expenseType;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getTripId() {
        return tripId;
    }

    public Double getAmountIUsd() {
        return amountIUsd;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }
}