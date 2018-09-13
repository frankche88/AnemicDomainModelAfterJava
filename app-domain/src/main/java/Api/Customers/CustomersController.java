package Api.Customers;

import Api.Utils.*;
import CSharpFunctionalExtensions.*;
import Logic.Customers.*;
import Logic.Movies.*;
import Logic.Utils.*;
import java.util.*;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [Route("api/[controller]")] public class CustomersController : BaseController
public class CustomersController extends BaseController
{
	private MovieRepository _movieRepository;
	private CustomerRepository _customerRepository;

	public CustomersController(UnitOfWork unitOfWork, MovieRepository movieRepository, CustomerRepository customerRepository)
	{
		super(unitOfWork);
		_customerRepository = customerRepository;
		_movieRepository = movieRepository;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [HttpGet][Route("{id}")] public IActionResult Get(long id)
	public final IActionResult Get(long id)
	{
		Customer customer = _customerRepository.GetById(id);
		if (customer == null)
		{
			return NotFound();
		}

		PurchasedMovieDto tempVar = new PurchasedMovieDto();
		tempVar.setPrice(x.Price);
		tempVar.setExpirationDate(x.ExpirationDate);
		tempVar.setPurchaseDate(x.PurchaseDate);
		MovieDto tempVar2 = new MovieDto();
		tempVar2.setId(x.Movie.Id);
		tempVar2.setName(x.Movie.Name);
		tempVar.setMovie(tempVar2);
		CustomerDto dto = new CustomerDto {Id = customer.Id, Name = customer.Name.Value, Email = customer.Email.Value, MoneySpent = customer.MoneySpent, Status = customer.Status.Type.toString(), StatusExpirationDate = customer.Status.ExpirationDate, PurchasedMovies = customer.PurchasedMovies.Select(x -> tempVar).ToList()};

		return Ok(dto);
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [HttpGet] public IActionResult GetList()
	public final IActionResult GetList()
	{
		IReadOnlyList<Customer> customers = _customerRepository.GetList();

		CustomerInListDto tempVar = new CustomerInListDto();
		tempVar.setId(x.Id);
		tempVar.setName(x.Name.Value);
		tempVar.setEmail(x.Email.Value);
		tempVar.setMoneySpent(x.MoneySpent);
		tempVar.setStatus(x.Status.Type.toString());
		tempVar.setStatusExpirationDate(x.Status.ExpirationDate);
		ArrayList<CustomerInListDto> dtos = customers.Select(x -> tempVar).ToList();

		return Ok(dtos);
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [HttpPost] public IActionResult Create([FromBody] CreateCustomerDto item)
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [HttpPost] public IActionResult Create([FromBody] CreateCustomerDto item)
	public final IActionResult Create(CreateCustomerDto item)
	{
		Result<CustomerName> customerNameOrError = CustomerName.Create(item.getName());
		Result<Email> emailOrError = Email.Create(item.getEmail());

		Result result = Result.Combine(customerNameOrError, emailOrError);
		if (result.IsFailure)
		{
			return Error(result.Error);
		}

		if (_customerRepository.GetByEmail(emailOrError.Value) != null)
		{
			return Error("Email is already in use: " + item.getEmail());
		}

		Customer customer = new Customer(customerNameOrError.Value, emailOrError.Value);
		_customerRepository.Add(customer);

		return Ok();
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [HttpPut][Route("{id}")] public IActionResult Update(long id, [FromBody] UpdateCustomerDto item)
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [HttpPut][Route("{id}")] public IActionResult Update(long id, [FromBody] UpdateCustomerDto item)
	public final IActionResult Update(long id, UpdateCustomerDto item)
	{
		Result<CustomerName> customerNameOrError = CustomerName.Create(item.getName());
		if (customerNameOrError.IsFailure)
		{
			return Error(customerNameOrError.Error);
		}

		Customer customer = _customerRepository.GetById(id);
		if (customer == null)
		{
			return Error("Invalid customer id: " + id);
		}

		customer.Name = customerNameOrError.Value;

		return Ok();
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [HttpPost][Route("{id}/movies")] public IActionResult PurchaseMovie(long id, [FromBody] long movieId)
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [HttpPost][Route("{id}/movies")] public IActionResult PurchaseMovie(long id, [FromBody] long movieId)
	public final IActionResult PurchaseMovie(long id, long movieId)
	{
		Movie movie = _movieRepository.GetById(movieId);
		if (movie == null)
		{
			return Error("Invalid movie id: " + movieId);
		}

		Customer customer = _customerRepository.GetById(id);
		if (customer == null)
		{
			return Error("Invalid customer id: " + id);
		}

		if (customer.HasPurchasedMovie(movie))
		{
			return Error("The movie is already purchased: " + movie.Name);
		}

		customer.PurchaseMovie(movie);

		return Ok();
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [HttpPost][Route("{id}/promotion")] public IActionResult PromoteCustomer(long id)
	public final IActionResult PromoteCustomer(long id)
	{
		Customer customer = _customerRepository.GetById(id);
		if (customer == null)
		{
			return Error("Invalid customer id: " + id);
		}

		Result promotionCheck = customer.CanPromote();
		if (promotionCheck.IsFailure)
		{
			return Error(promotionCheck.Error);
		}

		customer.Promote();

		return Ok();
	}
}