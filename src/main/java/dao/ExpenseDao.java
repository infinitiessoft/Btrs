package dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import entity.Expense;

public interface ExpenseDao extends PagingAndSortingRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

}
