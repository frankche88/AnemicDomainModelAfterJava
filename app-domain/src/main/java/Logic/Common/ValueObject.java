package Logic.Common;

public abstract class ValueObject<T extends ValueObject<T>>
{
	@Override
	public boolean equals(Object obj)
	{
		T valueObject = (T)obj;

		if ((valueObject == null))
		{
			return false;
		}

		if (this.getClass() != obj.getClass())
		{
			return false;
		}

		return EqualsCore(valueObject);
	}

	protected abstract boolean EqualsCore(T other);

	@Override
	public int hashCode()
	{
		return GetHashCodeCore();
	}

	protected abstract int GetHashCodeCore();

	

	
}


