package serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.ParameterValueDao;
import entity.ParameterValue;
import exceptions.ParameterNotFoundException;
import sendto.ParameterValueSendto;
import service.ParameterValueService;

public class ParameterValueServiceImpl implements ParameterValueService {

	private ParameterValueDao parameterDao;

	public ParameterValueServiceImpl(ParameterValueDao parameterDao) {
		this.parameterDao = parameterDao;
	}

	@Override
	public ParameterValueSendto retrieve(long id) {
		ParameterValue parameter = parameterDao.findOne(id);
		if (parameter == null) {
			throw new ParameterNotFoundException(id);
		}
		return toParameterValueSendto(parameter);
	}

	private ParameterValueSendto toParameterValueSendto(ParameterValue parameter) {
		ParameterValueSendto ret = new ParameterValueSendto();
		ret.setId(parameter.getId());
		ret.setValue(parameter.getValue());
		return ret;
	}

	@Override
	public void delete(long id) {
		parameterDao.delete(id);
	}

	@Override
	public ParameterValueSendto save(ParameterValueSendto parameterValue) {
		parameterValue.setId(null);
		ParameterValue parameter = new ParameterValue();
		parameter = parameterDao.save(parameter);
		return toParameterValueSendto(parameter);
	}

	@Override
	public Collection<ParameterValueSendto> findAll() {
		List<ParameterValueSendto> sendto = new ArrayList<ParameterValueSendto>();
		Collection<ParameterValue> parameter = parameterDao.findAll();
		for (ParameterValue parameterValue : parameter) {
			sendto.add(toParameterValueSendto(parameterValue));
		}
		return sendto;
	}

	@Override
	public ParameterValueSendto update(long id, ParameterValueSendto parameterValue) {
		ParameterValue parameter = parameterDao.findOne(id);
		if (parameter == null) {
			throw new ParameterNotFoundException(id);
		}
		return toParameterValueSendto(parameterDao.save(parameter));

	}

}
