package Logic.Customers;

import CSharpFunctionalExtensions.*;
import java.util.*;
import java.time.*;
import java.math.*;

public class CustomerStatus extends ValueObject<CustomerStatus>
{
	public static final CustomerStatus Regular = new CustomerStatus(CustomerStatusType.Regular, getExpirationDate().Infinite);

	private CustomerStatusType Type = CustomerStatusType.values()[0];
	public final CustomerStatusType getType()
	{
		return Type;
	}

	private Optional<LocalDateTime> _expirationDate = Optional.empty();
	public final ExpirationDate getExpirationDate()
	{
		return (ExpirationDate)_expirationDate;
	}

	public final boolean getIsAdvanced()
	{
		return getType() == CustomerStatusType.Advanced && !getExpirationDate().getIsExpired();
	}

	private CustomerStatus()
	{
	}

	private CustomerStatus(CustomerStatusType type, ExpirationDate expirationDate)
	{
		this();
		Type = type;
//C# TO JAVA CONVERTER TODO TASK: Throw expressions are not converted by C# to Java Converter:
//ORIGINAL LINE: _expirationDate = expirationDate ?? throw new ArgumentNullException(nameof(expirationDate));
		_expirationDate = (expirationDate != null) ? expirationDate : throw new NullPointerException("expirationDate");
	}

	public final BigDecimal GetDiscount()
	{
		return getIsAdvanced() ? 0.25 : java.math.BigDecimal.ZERO;
	}

	public final CustomerStatus Promote()
	{
		return new CustomerStatus(CustomerStatusType.Advanced, (ExpirationDate)LocalDateTime.UtcNow.plusYears(1));
	}

	@Override
	protected boolean EqualsCore(CustomerStatus other)
	{
		return getType() == other.getType() && getExpirationDate() == other.getExpirationDate();
	}

	@Override
	protected int GetHashCodeCore()
	{
		return getType().hashCode() ^ getExpirationDate().hashCode();
	}
}