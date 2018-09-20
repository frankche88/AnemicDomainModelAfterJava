package app.bundles;

import java.io.InputStream;

import com.google.common.collect.ImmutableList;

import app.AppConfiguration;
import app.customer.infrastructure.hibernate.repository.CustomerHibernateRepository;
import app.movies.infrastructure.hibernate.repository.MovieHibernateRepository;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;

public class HbnBundle extends HibernateBundle<AppConfiguration> {

	
	public HbnBundle() {
		super(ImmutableList.of(), new SessionFactoryFactory());
	}

	@Override
	public PooledDataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
		return configuration.getDataSourceFactory();

	}
	
	protected void configure(org.hibernate.cfg.Configuration configuration) {
		

		
		InputStream inputMovie = MovieHibernateRepository.class.getClassLoader().getResourceAsStream("hibernate/Movie.hbm.xml");
		
		InputStream inputPurchasedMovie = MovieHibernateRepository.class.getClassLoader().getResourceAsStream("hibernate/PurchasedMovies.hbm.xml");
		
		InputStream inputCustomer = CustomerHibernateRepository.class.getClassLoader().getResourceAsStream("hibernate/Customer.hbm.xml");
		
		configuration.addInputStream(inputMovie);
		configuration.addInputStream(inputPurchasedMovie);
		configuration.addInputStream(inputCustomer);
		
    }

}
