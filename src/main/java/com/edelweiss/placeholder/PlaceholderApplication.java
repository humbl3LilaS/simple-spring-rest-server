package com.edelweiss.placeholder;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.edelweiss.placeholder.domain.Comments;
import com.edelweiss.placeholder.domain.Posts;
import com.edelweiss.placeholder.domain.Todos;
import com.edelweiss.placeholder.domain.Users;
import com.edelweiss.placeholder.repository.CommentsRepository;
import com.edelweiss.placeholder.repository.PostsRepository;
import com.edelweiss.placeholder.repository.TodosRepository;
import com.edelweiss.placeholder.repository.UserRepository;
import com.github.javafaker.Faker;

import jakarta.persistence.criteria.CriteriaBuilder.In;


@SpringBootApplication
public class PlaceholderApplication {

	private final Faker faker = new Faker();

	public static void main(String[] args) {
		SpringApplication.run(PlaceholderApplication.class, args);
	}

	@Bean
	public ApplicationRunner loadUsersData(UserRepository repo) {
		return args -> {
			repo.deleteAll();
			for (int i = 1; i <= 10; i++) {
				String name = faker.name().fullName();
				String userName = faker.funnyName().name();
				String email = faker.bothify("?????##@gmail.com");
				repo.save(new Users(i, name, userName, email));
			}
		};
	}
	

	@Bean
	public ApplicationRunner loadTodosData(TodosRepository repo) {
		return args -> {
			repo.deleteAll();
			for (int i = 1; i <= 200; i++) {
				String title = faker.lorem().paragraph(2);
				Integer userId = faker.random().nextInt(10) + 1;
				Boolean completed = faker.bool().bool();
				repo.save(new Todos(i, userId, title, completed));
			}
		};
	}

	@Bean
	public ApplicationRunner loadPostsData(PostsRepository repo) {
		return args -> {
			repo.deleteAll();
			for (int i = 1; i <= 100; i++) {
				String title = faker.leagueOfLegends().quote();
				Integer userId = faker.random().nextInt(10) + 1;
				String body = faker.lorem().paragraph(2);
				repo.save(new Posts(i, userId, title, body));
			}
		};
	}

	@Bean
	public ApplicationRunner loadCommentsData(CommentsRepository repo) {
		return args -> {
			repo.deleteAll();
			for (int i = 1; i <= 300; i++) {
				Integer postId = faker.random().nextInt(100) + 1;
				Integer userId = faker.random().nextInt(10) +1;
				String body = faker.lorem().paragraph(2);
				repo.save(new Comments(i, postId, userId, body));
			}
		};
	}
}
