package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.TypeParameterDao;
import entity.TypeParameter;
import exceptions.TypeParameterNotFoundException;
import sendto.TypeParameterSendto;
import service.TypeParameterService;

public class TypeParameterServiceImpl implements TypeParameterService {

	private TypeParameterDao typeDao;

	public TypeParameterServiceImpl(TypeParameterDao typeDao) {
		this.typeDao = typeDao;
	}

	@Override
	public TypeParameterSendto retrieve(long id) {
		TypeParameter type = typeDao.findOne(id);
		if (type == null) {
			throw new TypeParameterNotFoundException(id);
		}
		return toTypeParameterSendto(type);

	}

	private TypeParameterSendto toTypeParameterSendto(TypeParameter type) {
		TypeParameterSendto ret = new TypeParameterSendto();
		ret.setId(type.getId());
		return ret;
	}

	@Override
	public void delete(long id) {
		typeDao.delete(id);

	}

	@Override
	public TypeParameterSendto save(TypeParameterSendto typeParameter) {
		typeParameter.setId(null);
		TypeParameter type = new TypeParameter();
		type = typeDao.save(type);
		return toTypeParameterSendto(type);

	}

	@Override
	public Collection<TypeParameterSendto> findAll() {
		List<TypeParameterSendto> sendto = new ArrayList<TypeParameterSendto>();
		Collection<TypeParameter> type = typeDao.findAll();
		for (TypeParameter typeParameter : type) {
			sendto.add(toTypeParameterSendto(typeParameter));
		}
		return sendto;
	}

	@Override
	public TypeParameterSendto update(long id, TypeParameterSendto typeParameter) {
		TypeParameter type = typeDao.findOne(id);
		if (type == null) {
			throw new TypeParameterNotFoundException(id);
		}
		return toTypeParameterSendto(typeDao.save(typeParameter));
	}

}