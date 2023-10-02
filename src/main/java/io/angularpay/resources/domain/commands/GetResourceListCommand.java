package io.angularpay.resources.domain.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.angularpay.resources.adapters.outbound.MongoAdapter;
import io.angularpay.resources.domain.Resources;
import io.angularpay.resources.domain.Role;
import io.angularpay.resources.exceptions.ErrorObject;
import io.angularpay.resources.models.GenericResourceListCommandRequest;
import io.angularpay.resources.validation.DefaultConstraintValidator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GetResourceListCommand extends AbstractCommand<GenericResourceListCommandRequest, List<Resources>>
        implements LargeDataResponseCommand {

    private final MongoAdapter mongoAdapter;
    private final DefaultConstraintValidator validator;

    public GetResourceListCommand(
            ObjectMapper mapper,
            MongoAdapter mongoAdapter,
            DefaultConstraintValidator validator) {
        super("GetResourceListCommand", mapper);
        this.mongoAdapter = mongoAdapter;
        this.validator = validator;
    }

    @Override
    protected List<Resources> handle(GenericResourceListCommandRequest request) {
        return this.mongoAdapter.listResources();
    }

    @Override
    protected List<ErrorObject> validate(GenericResourceListCommandRequest request) {
        return this.validator.validate(request);
    }

    @Override
    protected List<Role> permittedRoles() {
        return Arrays.asList(
                Role.ROLE_RESOURCE_ADMIN,
                Role.ROLE_UNVERIFIED_USER,
                Role.ROLE_VERIFIED_USER,
                Role.ROLE_PLATFORM_ADMIN
        );
    }
}
