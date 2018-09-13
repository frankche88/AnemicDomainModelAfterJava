package Logic.Customers;

import CSharpFunctionalExtensions.*;

public class CustomerName extends ValueObject<CustomerName>
{
	private String Value;
	public final String getValue()
	{
		return Value;
	}

	private CustomerName(String value)
	{
		Value = value;
	}

	public static Result<CustomerName> Create(String customerName)
	{
		customerName = ((customerName != null) ? customerName : "").trim();

		if (customerName.length() == 0)
		{
			return Result.<CustomerName>Fail("Customer name should not be empty");
		}

		if (customerName.length() > 100)
		{
			return Result.<CustomerName>Fail("Customer name is too long");
		}

		return Result.Ok(new CustomerName(customerName));
	}

	@Override
	protected boolean EqualsCore(CustomerName other)
	{
		return getValue().equals(other.getValue(), StringComparison.InvariantCultureIgnoreCase);
	}

	@Override
	protected int GetHashCodeCore()
	{
		return getValue().hashCode();
	}

//C# TO JAVA CONVERTER TODO TASK: The following operator overload is not converted by C# to Java Converter:
	public static implicit operator String(CustomerName customerName)
	{
		return customerName.getValue();
	}

//C# TO JAVA CONVERTER TODO TASK: The following operator overload is not converted by C# to Java Converter:
	public static explicit operator CustomerName(String customerName)
	{
		return Create(customerName).Value;
	}
}