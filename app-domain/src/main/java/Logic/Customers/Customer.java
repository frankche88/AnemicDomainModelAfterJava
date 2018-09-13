package Logic.Customers;

import Logic.Common.*;
import Logic.Movies.*;
import java.util.*;
import java.time.*;
import java.math.*;

public class Customer extends Entity
{
	private CustomerName _name;
	public CustomerName getName()
	{
		return (CustomerName)_name;
	}
	public void setName(CustomerName value)
	{
		_name = value;
	}

	private Email _email;
	public Email getEmail()
	{
		return (Email)_email;
	}

	private CustomerStatus Status;
	public CustomerStatus getStatus()
	{
		return Status;
	}
	protected void setStatus(CustomerStatus value)
	{
		Status = value;
	}

	private Dollars _moneySpent = Dollars.Create(new BigDecimal(0));
	public Dollars getMoneySpent()
	{
		return _moneySpent;
	}
	protected void setMoneySpent(Dollars value)
	{
		_moneySpent = value;
	}

	private List<PurchasedMovie> _purchasedMovies;
	public List<PurchasedMovie> getPurchasedMovies()
	{
		return _purchasedMovies;
	}

	protected Customer()
	{
		_purchasedMovies = new ArrayList<PurchasedMovie>();
	}

	public Customer(CustomerName name, Email email)
	{
		this();
//C# TO JAVA CONVERTER TODO TASK: Throw expressions are not converted by C# to Java Converter:
//ORIGINAL LINE: _name = name ?? throw new ArgumentNullException(nameof(name));
		_name = (name != null) ? name : throw new NullPointerException("name");
//C# TO JAVA CONVERTER TODO TASK: Throw expressions are not converted by C# to Java Converter:
//ORIGINAL LINE: _email = email ?? throw new ArgumentNullException(nameof(email));
		_email = (email != null) ? email : throw new NullPointerException("email");

		setMoneySpent(Dollars.Create(BigDecimal.ZERO);
		setStatus(CustomerStatus.Regular);
	}

	public boolean HasPurchasedMovie(Movie movie)
	{
		return getPurchasedMovies().Any(x = Logic.Movies.Movie.OpEquality(> x.Movie, movie) && !x.ExpirationDate.IsExpired);
	}

	public void PurchaseMovie(Movie movie)
	{
		if (HasPurchasedMovie(movie))
		{
			throw new RuntimeException();
		}

		ExpirationDate expirationDate = movie.GetExpirationDate();
		Dollars price = movie.CalculatePrice(getStatus());

		PurchasedMovie purchasedMovie = new PurchasedMovie(movie, this, price, expirationDate);
		_purchasedMovies.add(purchasedMovie);

		setMoneySpent(Logic.Customers.Dollars.OpAddition(getMoneySpent(), price));
	}

	public Result CanPromote()
	{
		if (getStatus().getIsAdvanced())
		{
			return Result.Fail("The customer already has the Advanced status");
		}

		if (getPurchasedMovies().Count(x -> x.ExpirationDate == ExpirationDate.Infinite || x.ExpirationDate.Date >= LocalDateTime.UtcNow.plusDays(-30)) < 2)
		{
			return Result.Fail("The customer has to have at least 2 active movies during the last 30 days");
		}

//C# TO JAVA CONVERTER TODO TASK: There is no Java equivalent to LINQ queries:
		if (getPurchasedMovies().Where(x -> x.PurchaseDate > LocalDateTime.UtcNow.plusYears(-1)).Sum(x -> x.Price) < 100)
		{
			return Result.Fail("The customer has to have at least 100 dollars spent during the last year");
		}

		return Result.Ok();
	}

	public void Promote()
	{
		if (CanPromote().IsFailure)
		{
			throw new RuntimeException();
		}

		setStatus(getStatus().Promote());
	}
}