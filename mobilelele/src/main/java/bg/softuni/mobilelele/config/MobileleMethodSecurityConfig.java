﻿package bg.softuni.mobilelele.config;

import bg.softuni.mobilelele.services.offer.IOfferService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MobileleMethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    private final IOfferService offerService;

    public MobileleMethodSecurityConfig(IOfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new MobileleSecurityExpressionHandler(this.offerService);
    }
}