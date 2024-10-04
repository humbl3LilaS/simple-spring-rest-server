package com.edelweiss.placeholder;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.edelweiss.placeholder.domain.Todos;
import com.edelweiss.placeholder.domain.Users;
import com.edelweiss.placeholder.repository.TodosRepository;
import com.edelweiss.placeholder.repository.UserRepository;


import jakarta.transaction.Transactional;

@SpringBootApplication
public class PlaceholderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaceholderApplication.class, args);
	}

	@Bean
	@Transactional
	ApplicationRunner loadUsersData(UserRepository repo) {
		return args -> {
			repo.deleteAll();
			repo.save(new Users(1, "Yamashita Shirakawa", "Edelweiss", "edelweiss@gmail.com"));
			repo.save(new Users(2, "Erirka Sumika", "SumiChawnn", "sumigod@gmail.com"));
			repo.save(new Users(3, "Tomioka Giyuu", "LonlyGiyuu", "sabishigariya@gmail.com"));
		};
	}

	@Bean
	@Transactional
	ApplicationRunner loadTodosData(TodosRepository repo) {
		return args ->{
			repo.deleteAll();;
			repo.save(new Todos(1, 1, "Todo Nextjs", false));
			repo.save(new Todos(2, 2, "Todo Astro", false));
		};
	}
}
