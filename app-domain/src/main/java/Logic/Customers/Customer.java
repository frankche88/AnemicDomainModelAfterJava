package Logic.Customers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Logic.Common.Entity;
import Logic.Movies.Movie;

public class Customer extends Entity {
	private CustomerName _name;

	public CustomerName getName() {
		return (CustomerName) _name;
	}

	public void setName(CustomerName value) {
		_name = value;
	}

	private Email _email;

	public Email getEmail() {
		return (Email) _email;
	}

	private CustomerStatus Status;

	public CustomerStatus getStatus() {
		return Status;
	}

	protected void setStatus(CustomerStatus value) {
		Status = value;
	}

	private Dollars _moneySpent = Dollars.Create(new BigDecimal(0));

	public Dollars getMoneySpent() {
		return _moneySpent;
	}

	protected void setMoneySpent(Dollars value) {
		_moneySpent = value;
	}

	private List<PurchasedMovie> _purchasedMovies;

	public List<PurchasedMovie> getPurchasedMovies() {
		return _purchasedMovies;
	}

	protected Customer() {
		_purchasedMovies = new ArrayList<PurchasedMovie>();
	}

	public Customer(CustomerName name, Email email)
	{
		this();
		
		if (name != null) {
			throw new NullPointerException("name");
		}
		
		if (email != null) {
			throw new NullPointerException("email");
		}

		_name = name; 
		_email = email;

		setMoneySpent(Dollars.Create(BigDecimal.ZERO));
		setStatus(CustomerStatus.Regular);
	}

	public boolean HasPurchasedMovie(Movie movie)
	{
		return getPurchasedMovies().stream().map(x -> x.getMovie()).anyMatch(x -> x.equals(movie) || !x.GetExpirationDate().getIsExpired()); //&& !x.ExpirationDate.IsExpired;
	}

	public void PurchaseMovie(Movie movie) {
		if (HasPurchasedMovie(movie)) {
			throw new RuntimeException();
		}

		ExpirationDate expirationDate = movie.GetExpirationDate();
		Dollars price = movie.CalculatePrice(getStatus());

		PurchasedMovie purchasedMovie = new PurchasedMovie(movie, this, price, expirationDate);
		_purchasedMovies.add(purchasedMovie);

		setMoneySpent(Logic.Customers.Dollars.OpAddition(getMoneySpent(), price));
	}

	public boolean CanPromote() {
		if (getStatus().getIsAdvanced()) {
			throw new IllegalArgumentException("The customer already has the Advanced status");
		}

		
		
		if (getPurchasedMovies().stream().filter(x -> x.getExpirationDate() == ExpirationDate.Infinite  || x.getExpirationDate().getDate().isAfter(LocalDateTime.now().minusDays(30))).count()<2
				) {
			throw new IllegalArgumentException("The customer has to have at least 2 active movies during the last 30 days");
		}

		if (getPurchasedMovies().stream().filter(x -> x.getPurchaseDate().isAfter(LocalDateTime.now().minusYears(-1)))
				.mapToDouble(x -> x.getPrice().getValue().doubleValue()).sum() < 100) {
			throw new IllegalArgumentException("The customer has to have at least 100 dollars spent during the last year");
		}

		return true;
	}

	public void Promote() {
		if (!CanPromote()) {
			throw new RuntimeException();
		}

		setStatus(getStatus().Promote());
	}
}