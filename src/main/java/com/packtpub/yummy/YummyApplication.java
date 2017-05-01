package com.packtpub.yummy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class YummyApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(YummyApplication.class, args);
        System.out.println("#######################");
        System.out.println("##### Initialized! ####");
        System.out.println("#######################");
    }

    @Configuration
    public static class Cfg {
        @Bean @Primary
        public ObjectMapper objectMapper(@Qualifier("_halObjectMapper") ObjectMapper objectMapper) {
            return objectMapper;
        }
        @Bean
        public HalObjectMapperConfigurer halObjectMapperConfigurer() {
            return new HalObjectMapperConfigurer();
        }

        /**
         * {@link BeanPostProcessor} to apply any {@link Jackson2ObjectMapperBuilder}
         * configuration to the HAL {@link ObjectMapper}.
         */
         class HalObjectMapperConfigurer
                implements BeanPostProcessor, BeanFactoryAware {

            private BeanFactory beanFactory;

            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName)
                    throws BeansException {
                if (bean instanceof ObjectMapper && "_halObjectMapper".equals(beanName)) {
                    postProcessHalObjectMapper((ObjectMapper) bean);
                }
                return bean;
            }

            private void postProcessHalObjectMapper(ObjectMapper objectMapper) {
                try {
                    Jackson2ObjectMapperBuilder builder = this.beanFactory
                            .getBean(Jackson2ObjectMapperBuilder.class);
                    builder.configure(objectMapper);
                } catch (NoSuchBeanDefinitionException ex) {
                    // No Jackson configuration required
                }
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName)
                    throws BeansException {
                return bean;
            }

            @Override
            public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
                this.beanFactory = beanFactory;
            }

        }

    }

}
