package Logic.Customers;

import CSharpFunctionalExtensions.*;

public class Email extends ValueObject<Email>
{
	private String Value;
	public final String getValue()
	{
		return Value;
	}

	private Email(String value)
	{
		Value = value;
	}

	public static Result<Email> Create(String email)
	{
		email = ((email != null) ? email : "").trim();

		if (email.length() == 0)
		{
			return Result.<Email>Fail("Email should not be empty");
		}

		if (!Regex.IsMatch(email, "^(.+)@(.+)$"))
		{
			return Result.<Email>Fail("Email is invalid");
		}

		return Result.Ok(new Email(email));
	}

	@Override
	protected boolean EqualsCore(Email other)
	{
		return getValue().equals(other.getValue(), StringComparison.InvariantCultureIgnoreCase);
	}

	@Override
	protected int GetHashCodeCore()
	{
		return getValue().hashCode();
	}

//C# TO JAVA CONVERTER TODO TASK: The following operator overload is not converted by C# to Java Converter:
	public static explicit operator Email(String email)
	{
		return Create(email).Value;
	}

//C# TO JAVA CONVERTER TODO TASK: The following operator overload is not converted by C# to Java Converter:
	public static implicit operator String(Email email)
	{
		return email.getValue();
	}
}