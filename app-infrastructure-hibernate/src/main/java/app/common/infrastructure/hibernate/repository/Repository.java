package app.common.infrastructure.hibernate.repository;

import app.commons.domain.entity.Entity;

public interface Repository<T extends Entity> {



	public abstract T GetById(long id);

	public abstract void Add(T entity);
}