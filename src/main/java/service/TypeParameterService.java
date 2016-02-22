package service;

import java.util.Collection;

import entity.TypeParameter;

public interface TypeParameterService {

	public TypeParameter retrieve(long id);

	public void delete(long id);

	public void save(TypeParameter typeParameter);

	public Collection<TypeParameter> findAll();

	public void update(long id, TypeParameter typeParameter);
}
