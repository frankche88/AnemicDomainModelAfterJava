package Logic.Movies;

import java.math.BigDecimal;

import Logic.Customers.Dollars;
import Logic.Customers.ExpirationDate;

public class LifeLongMovie extends Movie
{
	@Override
	public ExpirationDate GetExpirationDate()
	{
		return ExpirationDate.Infinite;
	}

	@Override
	protected Dollars GetBasePrice()
	{
		return Dollars.Of(new BigDecimal(8));
	}
}