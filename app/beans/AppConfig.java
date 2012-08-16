package beans;

import models.Account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Order;

import builders.AccountBuilder;
import builders.PostBuilder;



@Configuration
@ImportResource("classpath:application-context.xml")
public class AppConfig {

	private @Value("${mongodb.hostname}")
	String hostname;
	private @Value("${mongodb.port}")
	String port;
	private @Value("${mongodb.dbname}")
	String dbname;

	/*
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
	*/
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		
		AbstractApplicationContext ctx= new ClassPathXmlApplicationContext(new String []{"application-context.xml"});
		MongoTemplate template= (MongoTemplate)ctx.getBean("mongoTemplate");
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
	
	@Bean 
	public AccountBuilder accountBuilder(){
		AccountBuilder accountBuilder = new AccountBuilder();
		return accountBuilder;
	}
	
	@Bean 
	public PostBuilder postBuilder(){
		PostBuilder postBuilder = new PostBuilder();
		return postBuilder;
	}
	
	@Bean
	public EventService eventService(){
		EventService eventService = new EventService();
		return eventService;
	}
	
	
}
