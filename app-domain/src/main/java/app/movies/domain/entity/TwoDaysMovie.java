package app.movies.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import app.customers.domain.entity.Dollars;
import app.customers.domain.entity.ExpirationDate;

public class TwoDaysMovie extends Movie {
	@Override
	public ExpirationDate getExpirationDate() {
		return ExpirationDate.Create(LocalDateTime.now().plusDays(2));
	}

	@Override
	protected Dollars getBasePrice() {
		return Dollars.Of(new BigDecimal(4));
	}
}