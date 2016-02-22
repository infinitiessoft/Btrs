package service;

import java.util.Collection;

import entity.ParameterValue;

public interface ParameterValueService {
	public ParameterValue retrieve(long id);

	public void delete(long id);

	public void save(ParameterValue parameterValue);

	public Collection<ParameterValue> findAll();

	public void update(long id, ParameterValue parameterValue);

}
