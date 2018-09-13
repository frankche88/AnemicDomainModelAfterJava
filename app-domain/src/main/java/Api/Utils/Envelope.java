package Api.Utils;

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
		TimeGenerated = LocalDateTime.UtcNow;
	}
}

public class Envelope extends Envelope<String>
{
	protected Envelope(String errorMessage)
	{
		super(null, errorMessage);
	}

	public static <T> Envelope<T> Ok(T result)
	{
		return new Envelope<T>(result, null);
	}

	public static Envelope Ok()
	{
		return new Envelope(null);
	}

	public static Envelope Error(String errorMessage)
	{
		return new Envelope(errorMessage);
	}
}