package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.ParameterValue;

public interface ParameterValueDao extends JpaSpecificationExecutor<ParameterValue> {

	ParameterValue findOne(long parameterId);

	void delete(long id);

	ParameterValue save(ParameterValue parameter);

	Collection<ParameterValue> findAll();

}
