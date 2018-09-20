package app.customer.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CustomerDto {
	private long Id;

	public final long getId() {
		return Id;
	}

	public final void setId(long value) {
		Id = value;
	}

	private String Name;

	public final String getName() {
		return Name;
	}

	public final void setName(String value) {
		Name = value;
	}

	private String Email;

	public final String getEmail() {
		return Email;
	}

	public final void setEmail(String value) {
		Email = value;
	}

	private String Status;

	public final String getStatus() {
		return Status;
	}

	public final void setStatus(String value) {
		Status = value;
	}

	private Optional<LocalDateTime> StatusExpirationDate = Optional.empty();

	public final Optional<LocalDateTime> getStatusExpirationDate() {
		return StatusExpirationDate;
	}

	public final void setStatusExpirationDate(Optional<LocalDateTime> value) {
		StatusExpirationDate = value;
	}

	private BigDecimal MoneySpent = new BigDecimal(0);

	public final BigDecimal getMoneySpent() {
		return MoneySpent;
	}

	public final void setMoneySpent(BigDecimal value) {
		MoneySpent = value;
	}

	private List<PurchasedMovieDto> PurchasedMovies;

	public final List<PurchasedMovieDto> getPurchasedMovies() {
		return PurchasedMovies;
	}

	public final void setPurchasedMovies(List<PurchasedMovieDto> value) {
		PurchasedMovies = value;
	}
}