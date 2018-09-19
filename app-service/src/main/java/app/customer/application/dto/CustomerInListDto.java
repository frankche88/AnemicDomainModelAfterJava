package app.customer.application.dto;

import java.util.*;
import java.time.*;
import java.math.*;

public class CustomerInListDto
{
	private long Id;
	public final long getId()
	{
		return Id;
	}
	public final void setId(long value)
	{
		Id = value;
	}
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}
	private String Email;
	public final String getEmail()
	{
		return Email;
	}
	public final void setEmail(String value)
	{
		Email = value;
	}
	private String Status;
	public final String getStatus()
	{
		return Status;
	}
	public final void setStatus(String value)
	{
		Status = value;
	}
	private Optional<LocalDateTime> StatusExpirationDate = Optional.empty();
	public final Optional<LocalDateTime> getStatusExpirationDate()
	{
		return StatusExpirationDate;
	}
	public final void setStatusExpirationDate(Optional<LocalDateTime> value)
	{
		StatusExpirationDate = value;
	}
	private BigDecimal MoneySpent = new BigDecimal(0);
	public final BigDecimal getMoneySpent()
	{
		return MoneySpent;
	}
	public final void setMoneySpent(BigDecimal value)
	{
		MoneySpent = value;
	}
}