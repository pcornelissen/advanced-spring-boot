package com.packtpub.yummy.config;

import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DemoWebConfig implements WebMvcConfigurer {

    /**
     * Helps with configuring HandlerMappings path matching options such as trailing slash match,
     * suffix registration, path matcher and path helper.
     * Configured path matcher and path helper instances are shared for:
     * <ul>
     * <li>RequestMappings</li>
     * <li>ViewControllerMappings</li>
     * <li>ResourcesMappings</li>
     * </ul>
     *
     * @param configurer
     * @since 4.0.3
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true);
        configurer.setUseSuffixPatternMatch(true);
    }

    /**
     * Configure content negotiation options.
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    /**
     * Configure asynchronous request handling options.
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    }

    /**
     * Configure a handler to delegate unhandled requests by forwarding to the
     * Servlet container's "default" servlet. A common use case for this is when
     * the {@link DispatcherServlet} is mapped to "/" thus overriding the
     * Servlet container's default handling of static resources.
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    }

    /**
     * Add {@link Converter}s and {@link Formatter}s in addition to the ones
     * registered by default.
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
    }

    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations. Interceptors can be registered to apply
     * to all requests or be limited to a subset of URL patterns.
     * <p><strong>Note</strong> that interceptors registered here only apply to
     * controllers and not to resource handler requests. To intercept requests for
     * static resources either declare a
     * {@link MappedInterceptor MappedInterceptor}
     * bean or switch to advanced configuration mode by extending
     * {@link WebMvcConfigurationSupport
     * WebMvcConfigurationSupport} and then override {@code resourceHandlerMapping}.
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    /**
     * Add handlers to serve static resources such as images, js, and, css
     * files from specific locations under web application root, the classpath,
     * and others.
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js")
                .addResourceLocations("WEB_INF/secrets/js")
        .setCachePeriod(222);
    }

    /**
     * Configure cross origin requests processing.
     *
     * @param registry
     * @since 4.2
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    }

    /**
     * Configure simple automated controllers pre-configured with the response
     * status code and/or a view to render the response body. This is useful in
     * cases where there is no need for custom controller logic -- e.g. render a
     * home page, perform simple site URL redirects, return a 404 status with
     * HTML content, a 204 with no content, and more.
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    /**
     * Configure view resolvers to translate String-based view names returned from
     * controllers into concrete {@link View}
     * implementations to perform rendering with.
     *
     * @param registry
     * @since 4.1
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    }

    /**
     * Add resolvers to support custom controller method argument types.
     * <p>This does not override the built-in support for resolving handler
     * method arguments. To customize the built-in support for argument
     * resolution, configure {@link RequestMappingHandlerAdapter} directly.
     *
     * @param argumentResolvers initially an empty list
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    }

    /**
     * Add handlers to support custom controller method return value types.
     * <p>Using this option does not override the built-in support for handling
     * return values. To customize the built-in support for handling return
     * values, configure RequestMappingHandlerAdapter directly.
     *
     * @param returnValueHandlers initially an empty list
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    /**
     * Configure the {@link HttpMessageConverter}s to use for reading or writing
     * to the body of the request or response. If no converters are added, a
     * default list of converters is registered.
     * <p><strong>Note</strong> that adding converters to the list, turns off
     * default converter registration. To simply add a converter without impacting
     * default registration, consider using the method
     * {@link #extendMessageConverters(List)} instead.
     *
     * @param converters initially an empty list of converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    /**
     * A hook for extending or modifying the list of converters after it has been
     * configured. This may be useful for example to allow default converters to
     * be registered and then insert a custom converter through this method.
     *
     * @param converters the list of configured converters to extend.
     * @since 4.1.3
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    /**
     * Configure exception resolvers.
     * <p>The given list starts out empty. If it is left empty, the framework
     * configures a default set of resolvers, see
     * {@link WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List)}.
     * Or if any exception resolvers are added to the list, then the application
     * effectively takes over and must provide, fully initialized, exception
     * resolvers.
     * <p>Alternatively you can use
     * {@link #extendHandlerExceptionResolvers(List)} which allows you to extend
     * or modify the list of exception resolvers configured by default.
     *
     * @param exceptionResolvers initially an empty list
     * @see #extendHandlerExceptionResolvers(List)
     * @see WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List)
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    /**
     * Extending or modify the list of exception resolvers configured by default.
     * This can be useful for inserting a custom exception resolver without
     * interfering with default ones.
     *
     * @param exceptionResolvers the list of configured resolvers to extend
     * @see WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List)
     * @since 4.3
     */
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    /**
     * Provide a custom {@link Validator} instead of the one created by default.
     * The default implementation, assuming JSR-303 is on the classpath, is:
     * {@link OptionalValidatorFactoryBean}.
     * Leave the return value as {@code null} to keep the default.
     */
    @Override
    public Validator getValidator() {
        return null;
    }

    /**
     * Provide a custom {@link MessageCodesResolver} for building message codes
     * from data binding and validation error codes. Leave the return value as
     * {@code null} to keep the default.
     */
    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
