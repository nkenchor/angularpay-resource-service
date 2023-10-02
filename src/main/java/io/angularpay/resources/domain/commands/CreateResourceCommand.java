package io.angularpay.resources.domain.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.angularpay.resources.adapters.outbound.MongoAdapter;
import io.angularpay.resources.domain.Resources;
import io.angularpay.resources.domain.Role;
import io.angularpay.resources.exceptions.ErrorObject;
import io.angularpay.resources.models.CreateResourceCommandRequest;
import io.angularpay.resources.models.ResourceReferenceResponse;
import io.angularpay.resources.validation.DefaultConstraintValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CreateResourceCommand extends AbstractCommand<CreateResourceCommandRequest, ResourceReferenceResponse> {

    private final DefaultConstraintValidator validator;
    private final MongoAdapter mongoAdapter;

    public CreateResourceCommand(
            ObjectMapper mapper,
            DefaultConstraintValidator validator,
            MongoAdapter mongoAdapter) {
        super("CreateResourceCommand", mapper);
        this.validator = validator;
        this.mongoAdapter = mongoAdapter;
    }

    @Override
    protected ResourceReferenceResponse handle(CreateResourceCommandRequest request) {
        Resources resources = Resources.builder()
                .reference(UUID.randomUUID().toString())
                .name(request.getGenericResourceApiModel().getName())
                .type(request.getGenericResourceApiModel().getType())
                .base64ImageString(request.getGenericResourceApiModel().getBase64ImageString())
                .category(request.getGenericResourceApiModel().getCategory())
                .build();

        Resources response = this.mongoAdapter.createResource(resources);
        return new ResourceReferenceResponse(response.getReference());
    }

    @Override
    protected List<ErrorObject> validate(CreateResourceCommandRequest request) {
        return this.validator.validate(request);
    }

    @Override
    protected List<Role> permittedRoles() {
        return Arrays.asList(Role.ROLE_RESOURCE_ADMIN, Role.ROLE_PLATFORM_ADMIN);
    }

}
