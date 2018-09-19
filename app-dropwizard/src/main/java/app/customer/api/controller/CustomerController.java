package app.customer.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import app.customer.application.dto.CreateCustomerDto;
import app.customer.application.dto.CustomerDto;
import app.customer.application.dto.CustomerInListDto;
import app.customer.application.dto.PurchasedMovieDto;
import app.customer.application.dto.UpdateCustomerDto;
import app.customers.domain.entity.Customer;
import app.customers.domain.entity.CustomerName;
import app.customers.domain.entity.Email;
import app.customers.domain.entity.PurchasedMovie;
import app.movie.application.dto.MovieDto;
import app.movies.domain.entity.Movie;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/api/customers/")
@Api(value = "/api/customers")
//@PermitAll
public class CustomerController {

	@Inject
	private app.movies.domain.repository.MovieRepository _movieRepository;

	@Inject
	private app.customers.domain.repository.CustomerRepository _customerRepository;

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@ApiOperation(value = "Get costomer by id", httpMethod = "GET", response = CustomerDto.class, responseContainer = "Object")
	public final Response Get(@PathParam("id") long id) {
		
		/*
		
		
		var dto = new CustomerDto
            {
                Id = customer.Id,
                Name = customer.Name.Value,
                Email = customer.Email.Value,
                MoneySpent = customer.MoneySpent,
                Status = customer.Status.Type.ToString(),
                StatusExpirationDate = customer.Status.ExpirationDate,
                PurchasedMovies = customer.PurchasedMovies.Select(x => new PurchasedMovieDto
                {
                    Price = x.Price,
                    ExpirationDate = x.ExpirationDate,
                    PurchaseDate = x.PurchaseDate,
                    Movie = new MovieDto
                    {
                        Id = x.Movie.Id,
                        Name = x.Movie.Name
                    }
                }).ToList()
            };
		
		*/
		
		
		Customer customer = _customerRepository.getById(id);
		if (customer == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		List<PurchasedMovieDto> purchasedMovies = customer.getPurchasedMovies().stream().map(this::purchasedMoviesMapperPurchasedMovieDto).collect(Collectors.toList());

		
		CustomerDto dto = new CustomerDto {Id = customer.Id, Name = customer.Name.Value, Email = customer.Email.Value, MoneySpent = customer.MoneySpent, Status = customer.Status.Type.toString(), StatusExpirationDate = customer.Status.ExpirationDate, PurchasedMovies = customer.PurchasedMovies.Select(x -> tempVar).ToList()};

		return Ok(dto);
	}
	
	private PurchasedMovieDto purchasedMoviesMapperPurchasedMovieDto(PurchasedMovie x) {
		
		PurchasedMovieDto tempVar = new PurchasedMovieDto();
		tempVar.setPrice(x.getPrice().getValue());
		tempVar.setExpirationDate(Optional.of(x.getExpirationDate().getDate()));
		tempVar.setPurchaseDate(x.getPurchaseDate());
		MovieDto tempVar2 = new MovieDto();
		tempVar2.setId(x.getMovie().getId());
		tempVar2.setName(x.getMovie().getName());
		tempVar.setMovie(tempVar2);
		
		return tempVar;
		
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@ApiOperation(value = "List customers", httpMethod = "GET", response = CustomerInListDto.class, responseContainer = "List")
	public final Response GetList() {
		List<Customer> customers = _customerRepository.getList();
		
		/*
		 
		 List<CustomerInListDto> dtos = customers.Select(x => new CustomerInListDto
            {
                Id = x.Id,
                Name = x.Name.Value,
                Email = x.Email.Value,
                MoneySpent = x.MoneySpent,
                Status = x.Status.Type.ToString(),
                StatusExpirationDate = x.Status.ExpirationDate
            }).ToList();
		 
		 */
		
		List<CustomerInListDto> dtos = customers.stream().map(this::customerMappToCustomerInListDto).collect(Collectors.toList());

		return Response.ok(dtos).build();
	}
	
	private CustomerInListDto customerMappToCustomerInListDto(Customer x) {
		CustomerInListDto tempVar = new CustomerInListDto();
		tempVar.setId(x.getId());
		tempVar.setName(x.getName().getValue());
		tempVar.setEmail(x.getEmail().getValue());
		tempVar.setMoneySpent(x.getMoneySpent().getValue());
		tempVar.setStatus(x.getStatus().getType().toString());
		tempVar.setStatusExpirationDate(Optional.of(x.getStatus().getExpirationDate().getDate()));
		
		return tempVar;
		
	}
	
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@ApiOperation(value = "Create customer", httpMethod = "POST")
	public final Response Create(CreateCustomerDto item) {
		CustomerName customerNameOrError = CustomerName.Create(item.getName());
		Email emailOrError = Email.Create(item.getEmail());

		if (_customerRepository.getByEmail(emailOrError) != null) {
			return Response.status(Status.BAD_REQUEST).entity("Email is already in use: " + item.getEmail()).build();
		}

		Customer customer = new Customer(customerNameOrError, emailOrError);
		_customerRepository.save(customer);

		return Response.ok().build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@ApiOperation(value = "update customer", httpMethod = "PUT")
	public final Response Update(@PathParam("id") long id, UpdateCustomerDto item) {
		CustomerName customerNameOrError = CustomerName.Create(item.getName());

		Customer customer = _customerRepository.getById(id);
		if (customer == null) {
			return Response.status(Status.BAD_REQUEST).entity("Invalid customer id: " + id).build();
		}

		customer.setName(customerNameOrError);

		return Response.ok().build();
	}

	@POST
	@Path("{id}/movies")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@ApiOperation(value = "Purchase Movie", httpMethod = "POST")
	public final Response PurchaseMovie(long id, long movieId) {
		Movie movie = _movieRepository.getById(movieId);
		if (movie == null) {
			return Response.status(Status.BAD_REQUEST).entity("Invalid movie id: " + movieId).build();
		}

		Customer customer = _customerRepository.getById(id);
		if (customer == null) {

			return Response.status(Status.BAD_REQUEST).entity("Invalid customer id: " + id).build();
		}

		if (customer.hasPurchasedMovie(movie)) {
			return Response.status(Status.BAD_REQUEST).entity("The movie is already purchased: " + movie.getName())
					.build();
		}

		customer.purchaseMovie(movie);

		return Response.ok().build();
	}

	@POST
	@Path("{id}/promotion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@ApiOperation(value = "Promote Customer", httpMethod = "POST")
	public final Response PromoteCustomer(long id) {
		Customer customer = _customerRepository.getById(id);
		if (customer == null) {
			return Response.status(Status.BAD_REQUEST).entity("Invalid customer id: " + id).build();

		}

		boolean promotionCheck = customer.canPromote();
		if (!promotionCheck) {
			return Response.status(Status.BAD_REQUEST).entity("error al promocionar customer id: " + id).build();
		}

		customer.promote();

		return Response.ok().build();
	}

}
