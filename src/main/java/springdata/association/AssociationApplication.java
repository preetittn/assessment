package springdata.association;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import springdata.association.Service.AuthorService;

@SpringBootApplication
public class AssociationApplication {

	public static void main(String[] args)
	{
		ApplicationContext applicationContext=SpringApplication.run(AssociationApplication.class, args);
		AuthorService authorService = applicationContext.getBean(AuthorService.class);

	}

}
