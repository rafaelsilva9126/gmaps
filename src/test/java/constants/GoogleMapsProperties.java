package constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import builder.YAMLPropertySourceFactory;

@Configuration
@ConfigurationProperties(prefix= "yaml")
@PropertySource(factory = YAMLPropertySourceFactory.class, value="gmapswebelements.yaml")
public class GoogleMapsProperties implements IProperties  {

	@Override
	public ConfigurableEnvironment loadProperties() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		try {
			context.register(GoogleMapsProperties.class);
			context.refresh();
			return context.getEnvironment();
		} catch (Exception e) {
			System.out.println("Error while trying to load configuration from Properties");
		} finally {
			context.close();
		}
	return null;
	}
}
