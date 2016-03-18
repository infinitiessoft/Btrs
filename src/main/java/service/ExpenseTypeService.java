package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import entity.ExpenseType;
import sendto.ExpenseTypeSendto;

public interface ExpenseTypeService {

	public ExpenseTypeSendto retrieve(long id);

	public void delete(long id);

	public ExpenseTypeSendto save(ExpenseTypeSendto expenseType);

	public Page<ExpenseTypeSendto> findAll(Specification<ExpenseType> spec, Pageable pageable);

	public ExpenseTypeSendto update(long id, ExpenseTypeSendto expenseType);

}
