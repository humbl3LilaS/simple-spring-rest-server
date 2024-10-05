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

import com.edelweiss.placeholder.domain.Posts;
import com.edelweiss.placeholder.domain.Todos;
import com.edelweiss.placeholder.domain.Users;


@Configuration
public class StartupConfig
{

    // map csv resources to field
    @Value("${file.users.input}")
    private String usersFileInput;

    @Value("${file.todos.input}")
    private String todosFileInput;

    @Value("${file.posts.input}")
    private String postsFileInput;

    private FlatFileItemReader generateFileReader(String name, String filePath, String[] objectMapper,
                                                  Class<? extends Object> classMapper)
    {
        return new FlatFileItemReaderBuilder<>()
                .name(name)
                .resource(new ClassPathResource(filePath))
                .delimited()
                .names(objectMapper)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>()
                {
                    {
                        setTargetType(classMapper);
                    }
                }).build();
    }


    private  <T> JdbcBatchItemWriter<T> createWriter(DataSource dataSource, String sql) {
        return new JdbcBatchItemWriterBuilder<T>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(sql)
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Users> usersWriter(DataSource dataSource) {
        String usersSql = "INSERT INTO users (id, name, user_name, email) VALUES (:id, :name, :userName, :email)";
        return createWriter(dataSource, usersSql);
    }

    @Bean
    public JdbcBatchItemWriter<Todos> todosWriter(DataSource dataSource) {
        String todosSql = "INSERT INTO todos (id, user_id, title, completed) VALUES (:id, :userId, :title, :completed)";
        return createWriter(dataSource, todosSql);
    }

    @Bean
    public JdbcBatchItemWriter<Posts> postsWriter(DataSource dataSource) {
        String postsSql = "INSERT INTO posts (id, user_id, title, body) VALUES (:id, :userId, :title, :body)";
        return createWriter(dataSource, postsSql);
    }

    @Bean
    public Job importJob(JobRepository jobRepository, Step step1, Step step2, Step step3)
    {
        return new JobBuilder("importJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager manager,
                      JdbcBatchItemWriter<Users> usersWriter)
    {
        return new StepBuilder("step1", jobRepository)
                .<Users, Users>chunk(5, manager)
                .reader(generateFileReader(
                        "usersReader",
                        usersFileInput,
                        new String[]{"id", "name", "userName", "email"},
                        Users.class))
                .writer(usersWriter)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager manager,
                      JdbcBatchItemWriter<Todos> todosWriter)
    {
        return new StepBuilder("step2", jobRepository)
                .<Todos, Todos>chunk(5, manager)
                .reader(generateFileReader(
                        "todosReader",
                        todosFileInput,
                        new String[]{"id", "userId", "title", "completed"},
                        Todos.class))
                .writer(todosWriter)
                .build();
    }

    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager manager,
                      JdbcBatchItemWriter<Posts> postsWriter)
    {
        return new StepBuilder("step3", jobRepository)
                .<Posts, Posts>chunk(5, manager)
                .reader(generateFileReader(
                        "postsReader",
                        postsFileInput,
                        new String[]{"id", "userId", "title", "body"},
                        Posts.class))
                .writer(postsWriter)
                .build();
    }
}
