package bg.softuni.mobilelele.config;

import bg.softuni.mobilelele.services.offer.IOfferService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class MobileleSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final IOfferService offerService;

    public MobileleSecurityExpressionHandler(IOfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        var securityExpressionRoot = new OwnerSecurityExpressionRoot(authentication, this.offerService);

        securityExpressionRoot.setPermissionEvaluator(getPermissionEvaluator());
        securityExpressionRoot.setTrustResolver(new AuthenticationTrustResolverImpl());
        securityExpressionRoot.setRoleHierarchy(getRoleHierarchy());

        return securityExpressionRoot;
    }
}
