package app.bundles;

import app.AppConfiguration;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class SwitchableSwaggerBundle extends SwaggerBundle<AppConfiguration> {

    @Override
    protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(AppConfiguration configuration) {
        return configuration.getSwaggerBundleConfiguration();
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        super.run(configuration, environment);
    }
}
