package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import resources.specification.TypeParameterSpecification;
import sendto.TypeParameterSendto;

public interface TypeParameterService {

	public TypeParameterSendto retrieve(long id);

	public void delete(long id);

	public TypeParameterSendto save(TypeParameterSendto typeParameter);

	public Page<TypeParameterSendto> findAll(TypeParameterSpecification spec, Pageable pageable);

	public TypeParameterSendto update(long id, TypeParameterSendto typeParameter);

}
