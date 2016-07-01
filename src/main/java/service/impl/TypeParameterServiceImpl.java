//package service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.transaction.annotation.Transactional;
//
//import resources.specification.TypeParameterSpecification;
//import sendto.TypeParameterSendto;
//import service.TypeParameterService;
//import dao.TypeParameterDao;
//import entity.TypeParameter;
//import exceptions.TypeParameterNotFoundException;
//
//public class TypeParameterServiceImpl implements TypeParameterService {
//
//	private TypeParameterDao typeParameterDao;
//
//	public TypeParameterServiceImpl(TypeParameterDao typeDao) {
//		this.typeParameterDao = typeDao;
//	}
//
//	@Transactional("transactionManager")
//	@Override
//	public TypeParameterSendto retrieve(long id) {
//		TypeParameter type = typeParameterDao.findOne(id);
//		if (type == null) {
//			throw new TypeParameterNotFoundException(id);
//		}
//		return toTypeParameterSendto(type);
//
//	}
//
//	public static TypeParameterSendto toTypeParameterSendto(TypeParameter type) {
//		TypeParameterSendto ret = new TypeParameterSendto();
//		ret.setId(type.getId());
//		ret.setValue(type.getValue());
//		return ret;
//	}
//
//	@Transactional("transactionManager")
//	@Override
//	public void delete(long id) {
//		try {
//
//			typeParameterDao.delete(id);
//		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
//			throw new TypeParameterNotFoundException(id);
//		}
//	}
//
//	@Transactional("transactionManager")
//	@Override
//	public TypeParameterSendto save(TypeParameterSendto typeParameter) {
//		typeParameter.setId(null);
//		TypeParameter newEntry = new TypeParameter();
//		setUpTypeParameter(typeParameter, newEntry);
//		return toTypeParameterSendto(typeParameterDao.save(newEntry));
//
//	}
//
//	@Transactional("transactionManager")
//	@Override
//	public Page<TypeParameterSendto> findAll(TypeParameterSpecification spec, Pageable pageable) {
//		List<TypeParameterSendto> sendto = new ArrayList<TypeParameterSendto>();
//		Page<TypeParameter> types = typeParameterDao.findAll(spec, pageable);
//		for (TypeParameter typeParameter : types) {
//			sendto.add(toTypeParameterSendto(typeParameter));
//		}
//		Page<TypeParameterSendto> rets = new PageImpl<TypeParameterSendto>(sendto, pageable, types.getTotalElements());
//		return rets;
//	}
//
//	@Override
//	public TypeParameterSendto update(long id, TypeParameterSendto updated) {
//		TypeParameter type = typeParameterDao.findOne(id);
//		if (type == null) {
//			throw new TypeParameterNotFoundException(id);
//		}
//
//		return toTypeParameterSendto(typeParameterDao.save(type));
//	}
//
//	private void setUpTypeParameter(TypeParameterSendto sendto, TypeParameter newEntry) {
//		if (sendto.isValueSet()) {
//			newEntry.setValue(sendto.getValue());
//
//		}
//	}
//}
