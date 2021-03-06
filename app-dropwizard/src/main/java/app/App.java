package app;

import app.bundles.CorsBundle;
import app.bundles.HbnBundle;
import app.bundles.SwitchableSwaggerBundle;
import app.guice.module.HbnModule;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class App  extends Application<AppConfiguration> {

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}


	@Override
	public String getName() {
		return "PurchaseMovie-api";
	}

	@Override
	public void initialize(Bootstrap<AppConfiguration> bootstrap) {
		
		bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));

		bootstrap.addBundle(new MigrationsBundle<AppConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});

		final HbnBundle hibernate = new HbnBundle();
		
		bootstrap.addBundle(new SwitchableSwaggerBundle());
        bootstrap.addBundle(new CorsBundle());
		
		
        // register hbn bundle before guice to make sure factory initialized before guice context start
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig("app")
                .modules(new HbnModule(hibernate))
                .build());

	}

	@Override
	public void run(AppConfiguration configuration, Environment environment) throws Exception {

		//environment.jersey().register(BankTransferController.class);
		// environment.jersey().register(new ViewResource());

	}
}
