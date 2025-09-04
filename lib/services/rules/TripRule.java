package lib.services.rules;

import java.util.List;
import java.util.Optional;

import lib.models.Expense;

public interface TripRule {
    Optional<Violation> check(List<Expense> expenses);
}
