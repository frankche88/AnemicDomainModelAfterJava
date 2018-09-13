package Logic.Common;

import NHibernate.Proxy.*;

public abstract class Entity
{
	private long Id;
	public long getId()
	{
		return Id;
	}
	protected void setId(long value)
	{
		Id = value;
	}

	@Override
	public boolean equals(Object obj)
	{
		Entity other = obj instanceof Entity ? (Entity)obj : null;

		if (ReferenceEquals(other, null))
		{
			return false;
		}

		if (ReferenceEquals(this, other))
		{
			return true;
		}

		if (GetRealType() != other.GetRealType())
		{
			return false;
		}

		if (getId() == 0 || other.getId() == 0)
		{
			return false;
		}

		return getId() == other.getId();
	}

	public static boolean OpEquality(Entity a, Entity b)
	{
		if (ReferenceEquals(a, null) && ReferenceEquals(b, null))
		{
			return true;
		}

		if (ReferenceEquals(a, null) || ReferenceEquals(b, null))
		{
			return false;
		}

		return a.equals(b);
	}

	public static boolean OpInequality(Entity a, Entity b)
	{
		return !Logic.Common.Entity.OpEquality(a, b);
	}

	@Override
	public int hashCode()
	{
		return (GetRealType().toString() + getId()).hashCode();
	}

	private java.lang.Class GetRealType()
	{
		return NHibernateProxyHelper.GetClassWithoutInitializingProxy(this);
	}
}