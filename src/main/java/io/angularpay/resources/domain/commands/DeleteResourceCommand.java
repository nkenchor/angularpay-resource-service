package io.angularpay.resources.domain.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.angularpay.resources.adapters.outbound.MongoAdapter;
import io.angularpay.resources.domain.Resources;
import io.angularpay.resources.domain.Role;
import io.angularpay.resources.exceptions.ErrorObject;
import io.angularpay.resources.models.GenericResourceReferenceCommandRequest;
import io.angularpay.resources.validation.DefaultConstraintValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static io.angularpay.resources.helpers.CommandHelper.getRequestByReferenceOrThrow;

@Slf4j
@Service
public class DeleteResourceCommand extends AbstractCommand<GenericResourceReferenceCommandRequest, Void> {

    private final DefaultConstraintValidator validator;
    private final MongoAdapter mongoAdapter;

    public DeleteResourceCommand(
            ObjectMapper mapper,
            DefaultConstraintValidator validator,
            MongoAdapter mongoAdapter) {
        super("DeleteResourceCommand", mapper);
        this.validator = validator;
        this.mongoAdapter = mongoAdapter;
    }

    @Override
    protected Void handle(GenericResourceReferenceCommandRequest request) {
        Resources found = getRequestByReferenceOrThrow(this.mongoAdapter, request.getReference());
        this.mongoAdapter.deleteResource(found);
        return null;
    }

    @Override
    protected List<ErrorObject> validate(GenericResourceReferenceCommandRequest request) {
        return this.validator.validate(request);
    }

    @Override
    protected List<Role> permittedRoles() {
        return Arrays.asList(Role.ROLE_RESOURCE_ADMIN, Role.ROLE_PLATFORM_ADMIN);
    }

}
