package Logic.Common;

import Logic.Utils.*;

public abstract class Repository<T extends Entity>
{
	protected UnitOfWork _unitOfWork;

	protected Repository(UnitOfWork unitOfWork)
	{
		_unitOfWork = unitOfWork;
	}

	public final T GetById(long id)
	{
		return _unitOfWork.<T>Get(id);
	}

	public final void Add(T entity)
	{
		_unitOfWork.SaveOrUpdate(entity);
	}
}