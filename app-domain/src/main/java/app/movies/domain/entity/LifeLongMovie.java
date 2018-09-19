package app.movies.domain.entity;

import java.math.BigDecimal;

import app.customers.domain.entity.Dollars;
import app.customers.domain.entity.ExpirationDate;

public class LifeLongMovie extends Movie {
	@Override
	public ExpirationDate getExpirationDate() {
		return ExpirationDate.Infinite;
	}

	@Override
	protected Dollars getBasePrice() {
		return Dollars.Of(new BigDecimal(8));
	}
}