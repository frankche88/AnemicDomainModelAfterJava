package Logic.Customers;

import CSharpFunctionalExtensions.*;
import java.util.*;
import java.time.*;

public class ExpirationDate extends ValueObject<ExpirationDate>
{
	public static final ExpirationDate Infinite = new ExpirationDate(null);

	private Optional<LocalDateTime> Date = Optional.empty();
	public final Optional<LocalDateTime> getDate()
	{
		return Date;
	}

	public final boolean getIsExpired()
	{
		return this != Infinite && getDate().compareTo(LocalDateTime.UtcNow) < 0;
	}

	private ExpirationDate(Optional<LocalDateTime> date)
	{
		Date = date;
	}

	public static Result<ExpirationDate> Create(LocalDateTime date)
	{
		return Result.Ok(new ExpirationDate(date));
	}

	@Override
	protected boolean EqualsCore(ExpirationDate other)
	{
		return getDate().equals(other.getDate());
	}

	@Override
	protected int GetHashCodeCore()
	{
		return getDate().hashCode();
	}

//C# TO JAVA CONVERTER TODO TASK: The following operator overload is not converted by C# to Java Converter:
	public static explicit operator ExpirationDate(Optional<LocalDateTime> date)
	{
		if (date.isPresent())
		{
			return Create(date.get()).Value;
		}

		return Infinite;
	}

//C# TO JAVA CONVERTER TODO TASK: The following operator overload is not converted by C# to Java Converter:
	public static implicit <LocalDateTime> operator Nullable(ExpirationDate date)
	{
		return date.getDate();
	}
}