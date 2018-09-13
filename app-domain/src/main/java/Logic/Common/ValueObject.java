package CSharpFunctionalExtensions;

@Deprecated
public abstract class ValueObject<T extends ValueObject<T>>
{
	@Override
	public boolean equals(Object obj)
	{
		T valueObject = (T)obj;

		if (ReferenceEquals(valueObject, null))
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

	public static boolean OpEquality(ValueObject<T> a, ValueObject<T> b)
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

	public static boolean OpInequality(ValueObject<T> a, ValueObject<T> b)
	{
		return !CSharpFunctionalExtensions.ValueObject.OpEquality(a, b);
	}
}

public abstract class ValueObject
{
	protected abstract java.lang.Iterable<Object> GetEqualityComponents();

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}

		if (this.getClass() != obj.getClass())
		{
			throw new IllegalArgumentException(String.format("Invalid comparison of Value Objects of different types: %1$s and %2$s", this.getClass(), obj.getClass()));
		}

		ValueObject valueObject = (ValueObject)obj;

		return GetEqualityComponents().SequenceEqual(valueObject.GetEqualityComponents());
	}

	@Override
	public int hashCode()
	{
		return GetEqualityComponents().Aggregate(1, (current, obj) ->
		{
					unchecked
					{
						return current * 23 + (obj == null ? null : (obj.hashCode() != null) ? obj.hashCode() : 0);
					}
		});
	}

	public static boolean OpEquality(ValueObject a, ValueObject b)
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

	public static boolean OpInequality(ValueObject a, ValueObject b)
	{
		return !CSharpFunctionalExtensions.ValueObject.OpEquality(a, b);
	}
}
