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

import java.util.Arrays;
import java.util.Comparator;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class YummyApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(YummyApplication.class, args);

        System.out.println("#######################");
        System.out.println("##### WebBeans!    ####");
        System.out.println("#######################");

        String[] definitionNames = context.getBeanDefinitionNames();
        Arrays.stream(definitionNames)
                .filter(s -> context.getBean(s).getClass().getCanonicalName() != null)
                .filter(s -> !context.getBean(s).getClass().getCanonicalName().contains(".autoconfigure."))
                .filter(s -> context.getBean(s).getClass().getCanonicalName().contains(".web.")
                        || context.getBean(s).getClass().getCanonicalName().contains("thymeleaf"))
                .sorted(Comparator.comparing(o -> context.getBean(o).getClass().getCanonicalName()))
                .forEachOrdered(s -> System.out.println("\nBean: " + s + "\n--\t"
                        + clean(context.getBean(s).getClass().getCanonicalName())));
        System.out.println("");
        System.out.println("#################################");
        System.out.println("##### Web Autoconfiguration! ####");
        System.out.println("#################################");

        definitionNames = context.getBeanDefinitionNames();
        Arrays.stream(definitionNames)
                .filter(s -> context.getBean(s).getClass().getCanonicalName() != null)
                .filter(s -> context.getBean(s).getClass().getCanonicalName().contains(".autoconfigure."))
                .filter(s -> context.getBean(s).getClass().getCanonicalName().contains(".web.")
                        || context.getBean(s).getClass().getCanonicalName().contains("thymeleaf"))
                .sorted(Comparator.comparing(o -> context.getBean(o).getClass().getCanonicalName()))
                .forEachOrdered(s -> System.out.println("\nBean: " + s + "\n--\t"
                        + clean(context.getBean(s).getClass().getCanonicalName())));

        System.out.println();
        System.out.println("#######################");
        System.out.println("##### Initialized! ####");
        System.out.println("#######################");
        System.out.println(" go to: http://localhost:8080");
    }

    private static String clean(String canonicalName) {
        //get rid of CGLib garbage like $$EnhancerBySpringCGLIB$$cedd9792 in the classnames
        return canonicalName.replaceAll("\\$\\$.*", "");
    }

    @Configuration
    public static class Cfg {
        @Bean
        @Primary
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
