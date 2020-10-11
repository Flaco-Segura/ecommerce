package com.flacosegura.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import com.flacosegura.ecommerce.entity.Country;
import com.flacosegura.ecommerce.entity.Product;
import com.flacosegura.ecommerce.entity.ProductCategory;
import com.flacosegura.ecommerce.entity.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer{
    
    private EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        // disable PUT, POST, and DELETE
        HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        // disable HTTP methods for Product, ProductCategory, Country and State
        disableHttpMethods(config, unsupportedActions, Product.class);
        disableHttpMethods(config, unsupportedActions, ProductCategory.class);
        disableHttpMethods(config, unsupportedActions, Country.class);
        disableHttpMethods(config, unsupportedActions, State.class);

        
        // call an internal helper method
        exposeIds(config);

    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class<?>> entityClasses = new ArrayList<>();

        for (EntityType<?> entityType : entities) {
            entityClasses.add(entityType.getJavaType());
        }

        Class<?>[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

    @SuppressWarnings("rawtypes")
    private void disableHttpMethods(RepositoryRestConfiguration config, HttpMethod[] unsupportedActions, Class domainType) {
        config.getExposureConfiguration()
            .forDomainType(domainType)
            .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
            .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }
}
