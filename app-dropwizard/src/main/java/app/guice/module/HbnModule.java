package app.guice.module;

import org.hibernate.SessionFactory;

import com.google.inject.AbstractModule;

import app.bundles.HbnBundle;
import app.customer.infrastructure.hibernate.repository.CustomerHibernateRepository;
import app.customers.domain.repository.CustomerRepository;
import app.movies.domain.repository.MovieRepository;
import app.movies.infrastructure.hibernate.repository.MovieHibernateRepository;

public class HbnModule extends AbstractModule {

	private final HbnBundle hbnBundle;

    public HbnModule(HbnBundle hbnBundle) {
        this.hbnBundle = hbnBundle;
    }

    @Override
    protected void configure() {
        bind(SessionFactory.class).toInstance(hbnBundle.getSessionFactory());
        
        bind(MovieRepository.class).to(MovieHibernateRepository.class);
        
        bind(CustomerRepository.class).to(CustomerHibernateRepository.class);
        
    }

}
