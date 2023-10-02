package io.angularpay.resources.domain.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.angularpay.resources.adapters.outbound.MongoAdapter;
import io.angularpay.resources.domain.Role;
import io.angularpay.resources.domain.Resources;
import io.angularpay.resources.exceptions.ErrorObject;
import io.angularpay.resources.models.UpdateResourceCommandRequest;
import io.angularpay.resources.validation.DefaultConstraintValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static io.angularpay.resources.helpers.CommandHelper.getRequestByReferenceOrThrow;

@Slf4j
@Service
public class UpdateResourceCommand extends AbstractCommand<UpdateResourceCommandRequest, Void> {

    private final DefaultConstraintValidator validator;
    private final MongoAdapter mongoAdapter;

    public UpdateResourceCommand(
            ObjectMapper mapper,
            DefaultConstraintValidator validator,
            MongoAdapter mongoAdapter) {
        super("UpdateResourceCommand", mapper);
        this.validator = validator;
        this.mongoAdapter = mongoAdapter;
    }

    @Override
    protected Void handle(UpdateResourceCommandRequest request) {
        Resources found = getRequestByReferenceOrThrow(this.mongoAdapter, request.getReference());

        Resources resources = found.toBuilder()
                .name(request.getGenericResourceApiModel().getName())
                .type(request.getGenericResourceApiModel().getType())
                .base64ImageString(request.getGenericResourceApiModel().getBase64ImageString())
                .category(request.getGenericResourceApiModel().getCategory())
                .build();

        this.mongoAdapter.updateResource(resources);
        return null;
    }

    @Override
    protected List<ErrorObject> validate(UpdateResourceCommandRequest request) {
        return this.validator.validate(request);
    }

    @Override
    protected List<Role> permittedRoles() {
        return Arrays.asList(Role.ROLE_RESOURCE_ADMIN, Role.ROLE_PLATFORM_ADMIN);
    }

}
