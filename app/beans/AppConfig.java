package beans;

import models.Account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Order;



@Configuration
@ImportResource("classpath:application-context.xml")
public class AppConfig {

	private @Value("${mongodb.hostname}")
	String hostname;
	private @Value("${mongodb.port}")
	String port;
	private @Value("${mongodb.dbname}")
	String dbname;

	public @Bean
	MongoFactoryBean mongo() {
		MongoFactoryBean mongo = new MongoFactoryBean();
		mongo.setHost(hostname);
		mongo.setPort(Integer.parseInt(port));
		return mongo;
	}

	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(mongo().getObject(), dbname);
	}

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate template= new MongoTemplate(mongoDbFactory());
		template.indexOps(Account.class).ensureIndex(new Index("email",Order.ASCENDING).unique());
		return template;
	}
	
	@Bean
	public AccountService accountService(){
		AccountService accountService = new AccountService();
		return accountService;
	}
	
	@Bean
	public PostService postService(){
		PostService postService = new PostService();
		return postService;
	}
}
