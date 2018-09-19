package app.commons.api.utils;

import java.time.*;

public class Envelope<T>
{
	private T Result;
	public final T getResult()
	{
		return Result;
	}
	private String ErrorMessage;
	public final String getErrorMessage()
	{
		return ErrorMessage;
	}
	private LocalDateTime TimeGenerated = LocalDateTime.MIN;
	public final LocalDateTime getTimeGenerated()
	{
		return TimeGenerated;
	}

	protected Envelope(T result, String errorMessage)
	{
		Result = result;
		ErrorMessage = errorMessage;
		TimeGenerated = LocalDateTime.now();
	}
}
