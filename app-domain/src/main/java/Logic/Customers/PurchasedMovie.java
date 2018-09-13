package Logic.Customers;

import Logic.Common.*;
import Logic.Movies.*;
import java.util.*;
import java.time.*;
import java.math.*;

public class PurchasedMovie extends Entity
{
	private Movie Movie;
	public Movie getMovie()
	{
		return Movie;
	}
	protected void setMovie(Movie value)
	{
		Movie = value;
	}
	private Customer Customer;
	public Customer getCustomer()
	{
		return Customer;
	}
	protected void setCustomer(Customer value)
	{
		Customer = value;
	}

	private BigDecimal _price = new BigDecimal(0);
	public Dollars getPrice()
	{
		return Dollars.Of(_price);
	}
	protected void setPrice(Dollars value)
	{
		_price = value;
	}

	private LocalDateTime PurchaseDate = LocalDateTime.MIN;
	public LocalDateTime getPurchaseDate()
	{
		return PurchaseDate;
	}
	protected void setPurchaseDate(LocalDateTime value)
	{
		PurchaseDate = value;
	}

	private Optional<LocalDateTime> _expirationDate = Optional.empty();
	public ExpirationDate getExpirationDate()
	{
		return (ExpirationDate)_expirationDate;
	}
	protected void setExpirationDate(ExpirationDate value)
	{
		_expirationDate = Optional.of(value);
	}

	protected PurchasedMovie()
	{
	}

	public PurchasedMovie(Movie movie, Customer customer, Dollars price, ExpirationDate expirationDate)
	{
		if (price == null || price.getIsZero())
		{
			throw new IllegalArgumentException("price");
		}
		if (expirationDate == null || expirationDate.getIsExpired())
		{
			throw new IllegalArgumentException("expirationDate");
		}

//C# TO JAVA CONVERTER TODO TASK: Throw expressions are not converted by C# to Java Converter:
//ORIGINAL LINE: Movie = movie ?? throw new ArgumentNullException(nameof(movie));
		setMovie((movie != null) ? movie : throw new NullPointerException("movie"));
//C# TO JAVA CONVERTER TODO TASK: Throw expressions are not converted by C# to Java Converter:
//ORIGINAL LINE: Customer = customer ?? throw new ArgumentNullException(nameof(customer));
		setCustomer((customer != null) ? customer : throw new NullPointerException("customer"));
		setPrice(price);
		setExpirationDate(expirationDate);
		setPurchaseDate(LocalDateTime.UtcNow);
	}
}