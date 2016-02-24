package service;

import java.util.Collection;

import sendto.TypeParameterSendto;

public interface TypeParameterService {

	public TypeParameterSendto retrieve(long id);

	public void delete(long id);

	public TypeParameterSendto save(TypeParameterSendto typeParameter);

	public Collection<TypeParameterSendto> findAll();

	public TypeParameterSendto update(long id, TypeParameterSendto typeParameter);

}
