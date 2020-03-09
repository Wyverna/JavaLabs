package com.example.javalab;

import com.example.javalab.Bean.BeanClass;
import com.example.javalab.model.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class Javalab3Sem2Application {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
    public static void main(String[] args) {

        ApplicationContext context=new ClassPathXmlApplicationContext("static/beans.xml");
        Worker worker2=context.getBean("worker",Worker.class);
        System.out.println("First Name: "+worker2.getFirstName());
        BeanClass beanWorkerbyName=context.getBean("beanWorkerByname",BeanClass.class);
        System.out.println("Birthday: "+beanWorkerbyName.getWorker().getBirthday());
        BeanClass beanWorkerByType=context.getBean("beanWorkerByType",BeanClass.class);
        System.out.println(beanWorkerByType.getWorker().getLastName());
        beanWorkerByType.printWorker();
        BeanClass beanWorkerConstructor = context.getBean("beanWorkerByConstructor",BeanClass.class);
        System.out.println("Technology: "+beanWorkerConstructor.getWorker().getTechnologies());
        ((ClassPathXmlApplicationContext) context).close();
        SpringApplication.run(Javalab3Sem2Application.class, args);
    }
}
