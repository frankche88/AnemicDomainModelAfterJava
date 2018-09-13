package Logic.Customers;

import Logic.Common.ValueObject;

import java.math.*;

public class Dollars extends ValueObject<Dollars>
{
	private static final BigDecimal MaxDollarAmount = new BigDecimal(1_000_000);

	private BigDecimal Value = new BigDecimal(0);
	public final BigDecimal getValue()
	{
		return Value;
	}

	public final boolean getIsZero()
	{
		return getValue().compareTo(BigDecimal.ZERO) == 0;
	}

	private Dollars(BigDecimal value)
	{
		Value = value;
	}

	public static Dollars Create(BigDecimal dollarAmount)
	{
		if (dollarAmount.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new IllegalArgumentException("Dollar amount cannot be negative");
		}

		if (dollarAmount.compareTo(MaxDollarAmount) > 0)
		{
			throw new IllegalArgumentException("Dollar amount cannot be greater than " + MaxDollarAmount);
		}

		if (dollarAmount.doubleValue() % 0.01 > 0)
		{
			throw new IllegalArgumentException("Dollar amount cannot contain part of a penny");
		}

		return new Dollars(dollarAmount);
	}

	public static Dollars Of(BigDecimal dollarAmount)
	{
		return Create(dollarAmount);
	}

	public static Dollars OpMultiply(Dollars dollars, BigDecimal multiplier)
	{
		return new Dollars(dollars.getValue().multiply(multiplier));
	}

	public static Dollars OpAddition(Dollars dollars1, Dollars dollars2)
	{
		return new Dollars(dollars1.getValue().add(dollars2.getValue()));
	}

	@Override
	protected boolean EqualsCore(Dollars other)
	{
		return getValue().compareTo(other.getValue()) == 0;
	}

	@Override
	protected int GetHashCodeCore()
	{
		return getValue().hashCode();
	}

}