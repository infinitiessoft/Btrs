package service;

import java.util.Collection;

import sendto.ParameterValueSendto;

public interface ParameterValueService {

	public ParameterValueSendto retrieve(long id);

	public void delete(long id);

	public ParameterValueSendto save(ParameterValueSendto parameterValue);

	public Collection<ParameterValueSendto> findAll();

	public ParameterValueSendto update(long id);

}
