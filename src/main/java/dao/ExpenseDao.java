package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.Expense;

public interface ExpenseDao extends JpaSpecificationExecutor<Expense> {

}
