package app.customers.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import app.commons.domain.entity.Entity;
import app.movies.domain.entity.Movie;

public class PurchasedMovie extends Entity {
	
	private Movie movie;

	public Movie getMovie() {
		return movie;
	}

	protected void setMovie(Movie value) {
		movie = value;
	}

	private Customer Customer;

	public Customer getCustomer() {
		return Customer;
	}

	protected void setCustomer(Customer value) {
		Customer = value;
	}

	private Dollars _price = Dollars.Of(new BigDecimal(0));

	public Dollars getPrice() {
		return _price;
	}

	protected void setPrice(Dollars value) {
		_price = value;
	}

	private LocalDateTime PurchaseDate = LocalDateTime.MIN;

	public LocalDateTime getPurchaseDate() {
		return PurchaseDate;
	}

	protected void setPurchaseDate(LocalDateTime value) {
		PurchaseDate = value;
	}

	private ExpirationDate _expirationDate;

	public ExpirationDate getExpirationDate() {
		return (ExpirationDate) _expirationDate;
	}

	protected void setExpirationDate(ExpirationDate value) {
		_expirationDate = value;
	}

	protected PurchasedMovie() {
	}

	public PurchasedMovie(Movie movie, Customer customer, Dollars price, ExpirationDate expirationDate) {
		if (price == null || price.getIsZero()) {
			throw new IllegalArgumentException("price");
		}
		if (expirationDate == null || expirationDate.getIsExpired()) {
			throw new IllegalArgumentException("expirationDate");
		}

		if (movie == null) {
			throw new NullPointerException("movie");
		}

		if (customer == null) {
			throw new NullPointerException("customer");
		}

		setMovie(movie);
		setCustomer(customer);
		setPrice(price);
		setExpirationDate(expirationDate);
		setPurchaseDate(LocalDateTime.now());
	}
}