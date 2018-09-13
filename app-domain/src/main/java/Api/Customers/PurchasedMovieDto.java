package Api.Customers;

import java.util.*;
import java.time.*;
import java.math.*;

public class PurchasedMovieDto
{
	private MovieDto Movie;
	public final MovieDto getMovie()
	{
		return Movie;
	}
	public final void setMovie(MovieDto value)
	{
		Movie = value;
	}
	private BigDecimal Price = new BigDecimal(0);
	public final BigDecimal getPrice()
	{
		return Price;
	}
	public final void setPrice(BigDecimal value)
	{
		Price = value;
	}
	private LocalDateTime PurchaseDate = LocalDateTime.MIN;
	public final LocalDateTime getPurchaseDate()
	{
		return PurchaseDate;
	}
	public final void setPurchaseDate(LocalDateTime value)
	{
		PurchaseDate = value;
	}
	private Optional<LocalDateTime> ExpirationDate = Optional.empty();
	public final Optional<LocalDateTime> getExpirationDate()
	{
		return ExpirationDate;
	}
	public final void setExpirationDate(Optional<LocalDateTime> value)
	{
		ExpirationDate = value;
	}
}