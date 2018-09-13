package Logic.Movies;

import Logic.Common.*;
import Logic.Customers.*;
import java.time.*;
import java.math.*;

public class TwoDaysMovie extends Movie
{
	@Override
	public ExpirationDate GetExpirationDate()
	{
		return (ExpirationDate)LocalDateTime.UtcNow.plusDays(2);
	}

	@Override
	protected Dollars GetBasePrice()
	{
		return Dollars.Of(4);
	}
}