package dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import entity.TypeParameter;
import sendto.TypeParameterSendto;

public interface TypeParameterDao extends JpaSpecificationExecutor<TypeParameter> {

	TypeParameter findOne(long id);

	void delete(long id);

	TypeParameter save(TypeParameter type);

	Collection<TypeParameter> findAll();

	TypeParameter save(TypeParameterSendto typeParameter);

}
