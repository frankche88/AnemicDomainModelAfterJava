package app.purchases.api.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import app.customers.domain.entity.Customer;
import app.movies.domain.entity.Movie;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/api/purchases/")
@Api(value = "/api/purchases")
//@PermitAll
public class PurchaseController {

	@Inject
	private app.movies.domain.repository.MovieRepository _movieRepository;

	@Inject
	private app.customers.domain.repository.CustomerRepository _customerRepository;

//	@Path("{id}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@UnitOfWork
//	@ApiOperation(value = "Get customer by id ", httpMethod = "GET", response = CustomerDto.class, responseContainer = "Object")
//	public final Response Get(@PathParam("id") long id) {
//		
//		Customer customer = _customerRepository.getById(id);
//		if (customer == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		
//		List<PurchasedMovieDto> purchasedMovies = customer.getPurchasedMovies().stream().map(this::purchasedMoviesMapperPurchasedMovieDto).collect(Collectors.toList());
//
//		CustomerDto dto = new CustomerDto();
//		
//		dto.setId(customer.getId());
//		
//		dto.setName(customer.getName().getValue());
//		dto.setEmail(customer.getEmail().getValue()); 
//		dto.setMoneySpent(customer.getMoneySpent().getValue());
//		dto.setStatus(customer.getStatus().getType().toString());
//		dto.setStatusExpirationDate(Optional.of(customer.getStatus().getExpirationDate().getDate()));
//		dto.setPurchasedMovies(purchasedMovies);
//		
//		
//
//		return Response.ok(dto).build();
//	}
//	
//	private PurchasedMovieDto purchasedMoviesMapperPurchasedMovieDto(PurchasedMovie x) {
//		
//		PurchasedMovieDto tempVar = new PurchasedMovieDto();
//		tempVar.setPrice(x.getPrice().getValue());
//		tempVar.setExpirationDate(Optional.of(x.getExpirationDate().getDate()));
//		tempVar.setPurchaseDate(x.getPurchaseDate());
//		MovieDto tempVar2 = new MovieDto();
//		tempVar2.setId(x.getMovie().getId());
//		tempVar2.setName(x.getMovie().getName());
//		tempVar.setMovie(tempVar2);
//		
//		return tempVar;
//		
//	}
	

	@POST
	@Path("{id}/movies")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	@ApiOperation(value = "Purchase Movie", httpMethod = "POST")
	public final Response PurchaseMovie(@PathParam("id") long id, long movieId) {
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

}
