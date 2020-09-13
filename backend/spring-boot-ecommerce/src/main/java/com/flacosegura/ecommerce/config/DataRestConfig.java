package com.flacosegura.ecommerce.config;

import com.flacosegura.ecommerce.entity.Product;
import com.flacosegura.ecommerce.entity.ProductCategory;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer{
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        // disable PUT, POST, and DELETE
        HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        // disable HTTP methods for Product
        config.getExposureConfiguration()
            .forDomainType(Product.class)
            .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
            .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));

        // disable HTTP methods for ProductCategory
        config.getExposureConfiguration()
            .forDomainType(ProductCategory.class)
            .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
            .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }
}
