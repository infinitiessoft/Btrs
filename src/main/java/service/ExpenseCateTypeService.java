package service;

import java.util.Collection;

import sendto.ExpenseCateTypeSendto;

public interface ExpenseCateTypeService {

	public ExpenseCateTypeSendto retrieve(long id);

	public void delete(long id);

	public ExpenseCateTypeSendto save(ExpenseCateTypeSendto expenseCateType);

	public ExpenseCateTypeSendto update(long id, ExpenseCateTypeSendto expenseCateType);

	public void revokeCategoryFromType(long categoryId, long typeId);

	public Collection<ExpenseCateTypeSendto> findAll();

	public ExpenseCateTypeSendto findByCategoryIdAndTypeId(long categoryId, long typeId);

	public void grantCategoryToType(long categoryId, long typeId);

}
