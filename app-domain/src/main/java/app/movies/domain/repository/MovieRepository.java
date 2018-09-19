package app.movies.domain.repository;

import java.util.List;

import app.movies.domain.entity.Movie;

public interface MovieRepository {

	List<Movie> getList();

	Movie getById(long movieId);

}