package app.customers.api.controller;

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

import app.commons.api.utils.Envelope;
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
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/api/customers/")
@Api(value = "/api/customers")
//@PermitAll
public class CustomerController {

	@Inject
	private app.customers.domain.repository.CustomerRepository _customerRepository;

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@ApiOperation(value = "Get customer by id", httpMethod = "GET", response = CustomerDto.class, responseContainer = "Object")
	public final Response Get(@PathParam("id") long id) {
		
		Customer customer = _customerRepository.getById(id);
		if (customer == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		List<PurchasedMovieDto> purchasedMovies = customer.getPurchasedMovies().stream().map(this::purchasedMoviesMapperPurchasedMovieDto).collect(Collectors.toList());

		CustomerDto dto = new CustomerDto();
		
		dto.setId(customer.getId());
		
		dto.setName(customer.getName().getValue());
		dto.setEmail(customer.getEmail().getValue()); 
		dto.setMoneySpent(customer.getMoneySpent().getValue());
		dto.setStatus(customer.getStatus().getType().toString());
		dto.setStatusExpirationDate(Optional.of(customer.getStatus().getExpirationDate().getDate()));
		dto.setPurchasedMovies(purchasedMovies);
		
		

		return Response.ok(dto).build();
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
			//return Response.status(Status.BAD_REQUEST).entity("Email is already in use: " + item.getEmail()).build();
			
			Envelope<String> error = new Envelope<String>("Bad Customer email", "Email is already in use: " + item.getEmail());

			return Response.status(Status.BAD_REQUEST).entity(error).build();
			
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
			//return Response.status(Status.BAD_REQUEST).entity("Invalid customer id: " + id).build();
			
			Envelope<String> error = new Envelope<String>("Customer Not update", "Invalid customer promote id: " + id);

			return Response.status(Status.BAD_REQUEST).entity(error).build();
			
		}

		customer.setName(customerNameOrError);

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
			//return Response.status(Status.BAD_REQUEST).entity("Invalid customer id: " + id).build();
			
			Envelope<String> error = new Envelope<String>("Customer Not found", "Invalid customer id: " + id);

			return Response.status(Status.BAD_REQUEST).entity(error).build();
			

		}

		boolean promotionCheck = customer.canPromote();
		if (!promotionCheck) {
			//return Response.status(Status.BAD_REQUEST).entity("error al promocionar customer id: " + id).build();
			
			Envelope<String> error = new Envelope<String>("Customer Not promote", "Invalid customer promote id: " + id);

			return Response.status(Status.BAD_REQUEST).entity(error).build();
			
		}

		customer.promote();

		return Response.ok().build();
	}

}
