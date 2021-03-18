package com.octaspring.config;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.octaspring.dao.CategoryInterface;
import com.octaspring.dao.CourseActivityInterface;
import com.octaspring.dao.CourseInterface;
import com.octaspring.dao.LangInterface;
import com.octaspring.dao.LevelInterface;
import com.octaspring.dao.RoleInterface;
import com.octaspring.dao.SubcategoryInterface;
import com.octaspring.dao.UserPersonInterface;
import com.octaspring.service.CategoryService;
import com.octaspring.service.CourseActivityService;
import com.octaspring.service.CourseService;
import com.octaspring.service.LangService;
import com.octaspring.service.LevelService;
import com.octaspring.service.RoleService;
import com.octaspring.service.SubcategoryService;
import com.octaspring.service.UserPersonService;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.octaspring.controller")
public class WebAppConfig implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;
    
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
		
	}
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		return templateResolver;
	}
	
	@Bean 
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(this.templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		
		templateEngine.addDialect(new LayoutDialect());
		templateEngine.addDialect(new SpringSecurityDialect());

		return templateEngine;
	}
	
	@Bean 
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(this.templateEngine());
		return viewResolver;
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/cursos_en?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		//subida de arhivos
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000000); //Numero de bytes maximo
		
		
		return multipartResolver;
	}
	
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/assets/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/assets/css/");
		registry.addResourceHandler("/uploads/**").addResourceLocations("WEB-INF/uploads/");
	}
	
	@Bean
	public LangInterface getLang() {
		return new LangService(this.getDataSource());
	}
	
	@Bean
	public UserPersonInterface getUserPerson() {
		return new UserPersonService(this.getDataSource());
	}

	@Bean
	public CategoryInterface getCategory() {
		return new CategoryService(this.getDataSource());
	}
	@Bean
	public RoleInterface getRole() {
		return new RoleService(this.getDataSource());
	}
	@Bean
	public LevelInterface getLevel() {
		return new LevelService(this.getDataSource());
	}
	@Bean
	public SubcategoryInterface getSubcategory() {
		return new SubcategoryService(this.getDataSource());
	}
	@Bean
	public CourseInterface getCourse() {
		return (CourseInterface) new CourseService(this.getDataSource());

	}
	@Bean
	public CourseActivityInterface getCourseActivity() {
		return (CourseActivityInterface) new CourseActivityService(this.getDataSource());

	}
}
