package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.ParameterValueSpecification;
import sendto.ParameterValueSendto;

public interface ParameterValueService {

	public ParameterValueSendto retrieve(long id);

	public void delete(long id);

	public ParameterValueSendto save(ParameterValueSendto parameterValue);

	public Page<ParameterValueSendto> findAll(ParameterValueSpecification spec, Pageable pageable);

	public ParameterValueSendto update(long id, ParameterValueSendto parameterValue);

	ParameterValueSendto retrieve(ParameterValueSpecification spec);

}
