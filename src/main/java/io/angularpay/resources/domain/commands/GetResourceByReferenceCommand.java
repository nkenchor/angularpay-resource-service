package io.angularpay.resources.domain.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.angularpay.resources.adapters.outbound.MongoAdapter;
import io.angularpay.resources.domain.Role;
import io.angularpay.resources.domain.Resources;
import io.angularpay.resources.exceptions.CommandException;
import io.angularpay.resources.exceptions.ErrorObject;
import io.angularpay.resources.models.GenericResourceReferenceCommandRequest;
import io.angularpay.resources.validation.DefaultConstraintValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static io.angularpay.resources.exceptions.ErrorCode.REQUEST_NOT_FOUND;

@Service
public class GetResourceByReferenceCommand extends AbstractCommand<GenericResourceReferenceCommandRequest, Resources>
        implements LargeDataResponseCommand {

    private final MongoAdapter mongoAdapter;
    private final DefaultConstraintValidator validator;

    public GetResourceByReferenceCommand(
            ObjectMapper mapper,
            MongoAdapter mongoAdapter,
            DefaultConstraintValidator validator) {
        super("GetResourceByReferenceCommand", mapper);
        this.mongoAdapter = mongoAdapter;
        this.validator = validator;
    }

    @Override
    protected Resources handle(GenericResourceReferenceCommandRequest request) {
        return this.mongoAdapter.findResourceByReference(request.getReference())
                .orElseThrow(() -> CommandException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorCode(REQUEST_NOT_FOUND)
                        .message(REQUEST_NOT_FOUND.getDefaultMessage())
                        .build());
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
