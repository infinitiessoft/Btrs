package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import dao.ExpenseTypeDao;
import dao.ExpenseTypeParaDao;
import dao.TypeParameterDao;
import entity.ExpenseType;
import entity.ExpenseTypePara;
import entity.TypeParameter;
import exceptions.ExpenseTypeNotFoundException;
import exceptions.TypeParameterAssignmentNotFoundException;
import exceptions.TypeParameterNotFoundException;
import resources.specification.ExpenseTypeParaSpecification;
import sendto.ExpenseTypeParaSendto;
import service.ExpenseTypeParaService;

public class ExpenseTypeParaServiceImpl implements ExpenseTypeParaService {

	private ExpenseTypeParaDao expenseTypeParaDao;
	private ExpenseTypeDao expenseTypeDao;
	private TypeParameterDao typeParameterDao;

	public ExpenseTypeParaServiceImpl(ExpenseTypeDao expenseTypeDao, TypeParameterDao typeParameterDao,
			ExpenseTypeParaDao expenseTypeParaDao) {
		this.expenseTypeDao = expenseTypeDao;
		this.typeParameterDao = typeParameterDao;
		this.expenseTypeParaDao = expenseTypeParaDao;
	}

	@Override
	public Page<ExpenseTypeParaSendto> findAll(ExpenseTypeParaSpecification spec, Pageable pageable) {
		List<ExpenseTypeParaSendto> sendto = new ArrayList<ExpenseTypeParaSendto>();
		Page<ExpenseTypePara> expenseTypeParas = expenseTypeParaDao.findAll(spec, pageable);
		for (ExpenseTypePara expenseTypePara : expenseTypeParas) {
			sendto.add(ExpenseTypeParaServiceImpl.toExpenseTypeParaSendto(expenseTypePara));
		}
		Page<ExpenseTypeParaSendto> rets = new PageImpl<ExpenseTypeParaSendto>(sendto, pageable,
				expenseTypeParas.getTotalElements());
		return rets;
	}

	private static ExpenseTypeParaSendto toExpenseTypeParaSendto(ExpenseTypePara expenseTypePara) {
		ExpenseTypeParaSendto expenseTypeParaSendto = new ExpenseTypeParaSendto();
		expenseTypeParaSendto.setId(expenseTypePara.getId());
		expenseTypeParaSendto.getExpenseType().setId(expenseTypePara.getExpenseType().getId());
		expenseTypeParaSendto.getTypeParameter().setId(expenseTypePara.getTypeParameter().getId());
		return expenseTypeParaSendto;
	}

	@Override
	public ExpenseTypeParaSendto findByExpenseTypeIdAndParameterValueId(long expenseTypeId, long typeParameterId) {
		ExpenseTypePara expenseTypePara = expenseTypeParaDao.findByExpenseTypeIdAndTypeParameterId(expenseTypeId,
				typeParameterId);
		if (expenseTypePara == null) {
			throw new TypeParameterAssignmentNotFoundException(typeParameterId, expenseTypeId);
		}
		return ExpenseTypeParaServiceImpl.toExpenseTypeParaSendto(expenseTypePara);
	}

	@Override
	public void revokeTypeParameterFromExpenseType(long typeParameterId, long expenseTypeId) {
		ExpenseTypePara expenseTypePara = expenseTypeParaDao.findByExpenseTypeIdAndTypeParameterId(expenseTypeId,
				typeParameterId);
		if (expenseTypePara == null) {
			throw new TypeParameterAssignmentNotFoundException(typeParameterId, expenseTypeId);
		}
		expenseTypeParaDao.delete(expenseTypePara);
	}

	@Override
	public void grantTypeParameterToExpenseType(long typeParameterId, long expenseTypeId) {
		ExpenseType expenseType = expenseTypeDao.findOne(expenseTypeId);
		if (expenseType == null) {
			throw new ExpenseTypeNotFoundException(expenseTypeId);
		}
		TypeParameter typeParameter = typeParameterDao.findOne(typeParameterId);
		if (typeParameter == null) {
			throw new TypeParameterNotFoundException(typeParameterId);
		}
		ExpenseTypePara expenseTypePara = new ExpenseTypePara();
		expenseTypePara.setExpenseType(expenseType);
		expenseTypePara.setTypeParameter(typeParameter);
		expenseTypeParaDao.save(expenseTypePara);
	}

}