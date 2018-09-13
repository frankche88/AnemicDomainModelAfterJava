package Logic.Utils;

import NHibernate.*;
import NHibernate.Linq.*;

public class UnitOfWork
{
	private ISession _session;
	private ITransaction _transaction;
	private boolean _isAlive = true;

	public UnitOfWork(SessionFactory sessionFactory)
	{
		_session = sessionFactory.OpenSession();
		_transaction = _session.BeginTransaction(IsolationLevel.ReadCommitted);
	}

	public final void Commit()
	{
		if (!_isAlive)
		{
			return;
		}

		try
		{
			_transaction.Commit();
		}
		finally
		{
			_isAlive = false;
			_transaction.Dispose();
			_session.Dispose();
		}
	}

	public final <T> T Get(long id)
	{
		return _session.<T>Get(id);
	}

	public final <T> void SaveOrUpdate(T entity)
	{
		_session.SaveOrUpdate(entity);
	}

	public final <T> void Delete(T entity)
	{
		_session.Delete(entity);
	}

	public final <T> IQueryable<T> Query()
	{
		return _session.<T>Query();
	}

	public final ISQLQuery CreateSQLQuery(String q)
	{
		return _session.CreateSQLQuery(q);
	}
}