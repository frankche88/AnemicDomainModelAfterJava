package Api.Utils;

import Logic.Utils.*;

public class BaseController extends Controller
{
	private UnitOfWork _unitOfWork;

	public BaseController(UnitOfWork unitOfWork)
	{
		_unitOfWork = unitOfWork;
	}

//C# TO JAVA CONVERTER WARNING: There is no Java equivalent to C#'s shadowing via the 'new' keyword:
//ORIGINAL LINE: protected new IActionResult Ok()
	protected final IActionResult Ok()
	{
		_unitOfWork.Commit();
		return super.Ok(Envelope.Ok());
	}

	protected final <T> IActionResult Ok(T result)
	{
		_unitOfWork.Commit();
		return super.Ok(Envelope.Ok(result));
	}

	protected final IActionResult Error(String errorMessage)
	{
		return BadRequest(Envelope.Error(errorMessage));
	}
}