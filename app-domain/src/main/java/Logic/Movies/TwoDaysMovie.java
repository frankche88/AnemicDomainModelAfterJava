package Logic.Movies;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import Logic.Customers.Dollars;
import Logic.Customers.ExpirationDate;

public class TwoDaysMovie extends Movie {
	@Override
	public ExpirationDate GetExpirationDate() {
		return ExpirationDate.Create(LocalDateTime.now().plusDays(2));
	}

	@Override
	protected Dollars GetBasePrice() {
		return Dollars.Of(new BigDecimal(4));
	}
}