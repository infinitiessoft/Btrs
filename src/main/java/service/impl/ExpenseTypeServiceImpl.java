package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import sendto.ExpenseTypeSendto;
import service.ExpenseTypeService;
import dao.ExpenseTypeDao;
import entity.ExpenseType;
import entity.ExpenseTypePara;
import entity.TypeParameter;
import exceptions.ExpenseTypeNotFoundException;

public class ExpenseTypeServiceImpl implements ExpenseTypeService {

	private ExpenseTypeDao expenseTypeDao;

	public ExpenseTypeServiceImpl(ExpenseTypeDao expenseTypeDao) {
		this.expenseTypeDao = expenseTypeDao;
	}

	@Transactional("transactionManager")
	@Override
	public ExpenseTypeSendto retrieve(long id) {
		ExpenseType expenseType = expenseTypeDao.findOne(id);
		if (expenseType == null) {
			throw new ExpenseTypeNotFoundException(id);
		}
		return toExpenseTypeSendto(expenseType);
	}

	private ExpenseTypeSendto toExpenseTypeSendto(ExpenseType expenseType) {
		ExpenseTypeSendto ret = new ExpenseTypeSendto();
		ret.setId(expenseType.getId());
		ret.setTaxPercent(expenseType.getTaxPercent());
		ret.setValue(expenseType.getValue());
		ExpenseTypeSendto.ExpenseCategory category = new ExpenseTypeSendto.ExpenseCategory();
		category.setId(expenseType.getExpenseCategory().getId());
		category.setName(expenseType.getExpenseCategory().getName_key());
		ret.setExpenseCategory(category);

		for (ExpenseTypePara typePara : expenseType.getExpenseTypePara()) {
			TypeParameter typeParameter = typePara.getTypeParameter();
			ExpenseTypeSendto.TypeParameter typeParameterSendto = new ExpenseTypeSendto.TypeParameter();
			typeParameterSendto.setId(typeParameter.getId());
			typeParameterSendto.setValue(typeParameter.getValue());
			typeParameterSendto.setDataType(typePara.getDataType());
			ret.getTypeParameters().add(typeParameterSendto);
		}

		return ret;
	}

	@Transactional("transactionManager")
	@Override
	public void delete(long id) {
		try {
			ExpenseType expenseType = expenseTypeDao.findOne(id);
			if (expenseType == null) {
				throw new ExpenseTypeNotFoundException(id);
			}
			expenseTypeDao.delete(expenseType);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new ExpenseTypeNotFoundException(id);
		}

	}

	@Transactional("transactionManager")
	@Override
	public ExpenseTypeSendto save(ExpenseTypeSendto expenseType) {
		expenseType.setId(null);
		ExpenseType newEntry = new ExpenseType();
		setUpExpenseType(expenseType, newEntry);
		newEntry = expenseTypeDao.save(newEntry);
		return toExpenseTypeSendto(newEntry);
	}

	@Transactional("transactionManager")
	@Override
	public Page<ExpenseTypeSendto> findAll(Specification<ExpenseType> spec,
			Pageable pageable) {
		List<ExpenseTypeSendto> sendto = new ArrayList<ExpenseTypeSendto>();
		Page<ExpenseType> expType = expenseTypeDao.findAll(spec, pageable);
		for (ExpenseType expenseType : expType) {
			sendto.add(toExpenseTypeSendto(expenseType));
		}
		Page<ExpenseTypeSendto> rets = new PageImpl<ExpenseTypeSendto>(sendto,
				pageable, expType.getTotalElements());
		return rets;
	}

	@Transactional("transactionManager")
	@Override
	public ExpenseTypeSendto update(long id, ExpenseTypeSendto updated) {
		ExpenseType exptype = expenseTypeDao.findOne(id);
		if (exptype == null) {
			throw new ExpenseTypeNotFoundException(id);
		}
		setUpExpenseType(updated, exptype);
		return toExpenseTypeSendto(expenseTypeDao.save(exptype));
	}

	private void setUpExpenseType(ExpenseTypeSendto sendto, ExpenseType newEntry) {
		if (sendto.isTaxPercentSet()) {
			newEntry.setTaxPercent(sendto.getTaxPercent());
		}
		if (sendto.isValueSet()) {
			newEntry.setValue(sendto.getValue());
		}

	}

}
