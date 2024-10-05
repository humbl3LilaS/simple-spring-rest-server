package com.edelweiss.placeholder.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.edelweiss.placeholder.domain.Todos;
import com.edelweiss.placeholder.domain.Users;



@Configuration
public class StartupConfig {
    
    // map csv resources to field
    @Value("${file.users.input}")
    private String usersFileInput;

    @Value("${file.todos.input}")
    private String todosFileInput;

    @Bean
    public FlatFileItemReader usersReader() {
        return new FlatFileItemReaderBuilder<>()
                .name("usersReader")
                .resource(new ClassPathResource(usersFileInput))
                .delimited()
                .names(new String[] { "id", "name", "userName", "email" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                    {
                        setTargetType(Users.class);
                    }
                }).build();
    }

    @Bean
    public FlatFileItemReader todosReader() {
        return new FlatFileItemReaderBuilder<>()
                .name("todosReader")
                .resource(new ClassPathResource(todosFileInput))
                .delimited()
                .names(new String[] { "id", "userId", "title", "completed" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                    {
                        setTargetType(Todos.class);
                    }
                }).build();
    }

    @Bean
    JdbcBatchItemWriter<Users> usersWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Users>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO users (id, name, user_name, email) VALUES (:id, :name, :userName, :email)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    JdbcBatchItemWriter<Todos> todosWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Todos>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO todos (id, user_id, title, completed) VALUES (:id, :userId, :title, :completed)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importJob(JobRepository jobRepository, Step step1, Step step2) {
        return new JobBuilder("importJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager manager,JdbcBatchItemWriter<Users> usersWriter) 
    {
        return new StepBuilder("step1", jobRepository)
                .<Users, Users>chunk(5, manager)
                .reader(usersReader())
                .writer(usersWriter)
                .build();
    }
    
    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager manager, JdbcBatchItemWriter<Todos> todosWriter) 
    {
        return new StepBuilder("step2", jobRepository)    
                .<Todos, Todos>chunk(5, manager)
                .reader(todosReader())
                .writer(todosWriter)
                .build();
    }
}
