package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import sendto.ExpenseTypeSendto;
import entity.ExpenseType;

public interface ExpenseTypeService {

	public ExpenseTypeSendto retrieve(Specification<ExpenseType> spec);

	public void delete(Specification<ExpenseType> spec);

	public ExpenseTypeSendto save(ExpenseTypeSendto expenseType);

	public Page<ExpenseTypeSendto> findAll(Specification<ExpenseType> spec,
			Pageable pageable);

	public ExpenseTypeSendto update(Specification<ExpenseType> spec,
			ExpenseTypeSendto expenseType);

}
