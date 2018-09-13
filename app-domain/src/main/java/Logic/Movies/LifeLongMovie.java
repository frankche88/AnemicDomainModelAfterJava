package Logic.Movies;

import Logic.Common.*;
import Logic.Customers.*;
import java.time.*;
import java.math.*;

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
		return Dollars.Of(8);
	}
}