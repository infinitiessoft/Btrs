package service;

import java.util.Collection;

import entity.ExpenseCateType;

public interface ExpenseCateTypeService {

	public ExpenseCateType retrieve(long id);

	public void delete(long id);

	public void save(ExpenseCateType expenseCateType);

	public Collection<ExpenseCateType> findAll();

	public void update(long id, ExpenseCateType expenseCateType);
}
