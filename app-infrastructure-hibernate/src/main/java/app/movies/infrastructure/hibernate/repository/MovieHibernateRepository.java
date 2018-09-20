package app.movies.infrastructure.hibernate.repository;

import java.util.List;

import app.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import app.movies.domain.entity.Movie;
import app.movies.domain.repository.MovieRepository;

public class MovieHibernateRepository extends BaseHibernateRepository<Movie> implements MovieRepository {

	/* (non-Javadoc)
	 * @see app.movies.infrastructure.hibernate.repository.MovieRepository#getList()
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public final List<Movie> getList() {
		return (List<Movie>) getSession().createCriteria(Movie.class).list();
	}

    @Override
    public Movie getById(long movieId) {
        return getSession().get(Movie.class, movieId);
    }
}