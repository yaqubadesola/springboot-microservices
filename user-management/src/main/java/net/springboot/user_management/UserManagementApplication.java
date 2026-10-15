package net.springboot.user_management;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot REST API Documentation",
				description = "Spring Boot REST API Documentation by YAQUB",
				version = "v1.0",
				contact = @Contact(
						name = "Raheem Adesola Yaqub",
						email = "yaqub.adesola@gmail.com",
						url = "www.boldlinks.com.ng"
				),
				license = @License(
						name = "Apache 2.0",
						url = "www.boldlinks.com.ng/licence"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User Management Documentation",
				url = "www.boldlinks.com.ng/user-management.html"
		)
)
public class UserManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserManagementApplication.class, args);
//		Student student = new Student(1, "Raheem Yaqub");
//		System.out.println(student.getId() + " : " + student.getName());
	}


}
