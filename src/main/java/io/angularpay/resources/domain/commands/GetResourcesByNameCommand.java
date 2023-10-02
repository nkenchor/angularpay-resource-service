package io.angularpay.resources.domain.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.angularpay.resources.adapters.outbound.MongoAdapter;
import io.angularpay.resources.domain.Resources;
import io.angularpay.resources.domain.Role;
import io.angularpay.resources.exceptions.ErrorObject;
import io.angularpay.resources.models.GetResourceByNameCommandRequest;
import io.angularpay.resources.validation.DefaultConstraintValidator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GetResourcesByNameCommand extends AbstractCommand<GetResourceByNameCommandRequest, List<Resources>>
        implements LargeDataResponseCommand {

    private final MongoAdapter mongoAdapter;
    private final DefaultConstraintValidator validator;

    public GetResourcesByNameCommand(
            ObjectMapper mapper,
            MongoAdapter mongoAdapter,
            DefaultConstraintValidator validator) {
        super("GetResourcesByNameCommand", mapper);
        this.mongoAdapter = mongoAdapter;
        this.validator = validator;
    }

    @Override
    protected List<Resources> handle(GetResourceByNameCommandRequest request) {
        return this.mongoAdapter.findResourceByName(request.getName());
    }

    @Override
    protected List<ErrorObject> validate(GetResourceByNameCommandRequest request) {
        return this.validator.validate(request);
    }

    @Override
    protected List<Role> permittedRoles() {
        return Arrays.asList(Role.ROLE_RESOURCE_ADMIN, Role.ROLE_PLATFORM_ADMIN);
    }
}
