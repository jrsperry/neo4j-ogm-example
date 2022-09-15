package jrsperry.neo4jexamples;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OgmExampleApplication {

	@Value("${neo4j.uri:#{null}}")
	private String uri;

	@Value("${neo4j.user:neo4j}")
	private String username;

	@Value("${neo4j.password:password}")
	private String password;

	@Bean
	public org.neo4j.ogm.config.Configuration configuration() {
		org.neo4j.ogm.config.Configuration.Builder builder = new org.neo4j.ogm.config.Configuration.Builder()
				.uri(uri)
				.credentials(username, password)
				.autoIndex("none")
				.verifyConnection(true);
		return builder.build();
	}

	@Bean
	public SessionFactory sessionFactory() {
		return new SessionFactory(configuration(), "jrsperry.neo4jexamples");
	}

	public static void main(String[] args) {
		SpringApplication.run(OgmExampleApplication.class, args);
	}

}
