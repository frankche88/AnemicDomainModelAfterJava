package Api.Utils;

import Newtonsoft.Json.*;

public class ExceptionHandler
{
	private RequestDelegate _next;

	public ExceptionHandler(RequestDelegate next)
	{
		_next = next;
	}

//C# TO JAVA CONVERTER TODO TASK: There is no equivalent in Java to the 'async' keyword:
//ORIGINAL LINE: public async Task Invoke(HttpContext context)
	public final Task Invoke(HttpContext context)
	{
		try
		{
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to 'await' in Java:
			await _next(context);
		}
		catch (RuntimeException ex)
		{
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to 'await' in Java:
			await HandleExceptionAsync(context, ex);
		}
	}

	private Task HandleExceptionAsync(HttpContext context, RuntimeException exception)
	{
		// Log exception here
		String result = JsonConvert.SerializeObject(Envelope.Error(exception.getMessage()));
		context.Response.ContentType = "application/json";
		context.Response.StatusCode = HttpStatusCode.InternalServerError.getValue();
		return context.Response.WriteAsync(result);
	}
}