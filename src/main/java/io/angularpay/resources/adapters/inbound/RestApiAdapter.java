package io.angularpay.resources.adapters.inbound;

import io.angularpay.resources.domain.Resources;
import io.angularpay.resources.domain.commands.*;
import io.angularpay.resources.models.*;
import io.angularpay.resources.ports.inbound.RestApiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static io.angularpay.resources.helpers.Helper.fromHeaders;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class RestApiAdapter implements RestApiPort {

    private final CreateResourceCommand createResourceCommand;
    private final UpdateResourceCommand updateResourceCommand;
    private final GetResourceByReferenceCommand getResourceByReferenceCommand;
    private final DeleteResourceCommand deleteResourceCommand;
    private final GetResourcesByNameCommand getResourcesByNameCommand;
    private final GetResourcesByCategoryCommand getResourcesByCategoryCommand;
    private final GetResourceListCommand getResourceListCommand;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public GenericReferenceResponse create(
            @RequestBody GenericResourceApiModel genericResourceApiModel,
            @RequestHeader Map<String, String> headers) {
        AuthenticatedUser authenticatedUser = fromHeaders(headers);
        CreateResourceCommandRequest createResourceCommandRequest = CreateResourceCommandRequest.builder()
                .genericResourceApiModel(genericResourceApiModel)
                .authenticatedUser(authenticatedUser)
                .build();
        return this.createResourceCommand.execute(createResourceCommandRequest);
    }

    @PutMapping("/{resourceReference}")
    @Override
    public void update(
            @PathVariable String resourceReference,
            @RequestBody GenericResourceApiModel genericResourceApiModel,
            @RequestHeader Map<String, String> headers) {
        AuthenticatedUser authenticatedUser = fromHeaders(headers);
        UpdateResourceCommandRequest updateResourceCommandRequest = UpdateResourceCommandRequest.builder()
                .reference(resourceReference)
                .genericResourceApiModel(genericResourceApiModel)
                .authenticatedUser(authenticatedUser)
                .build();
        this.updateResourceCommand.execute(updateResourceCommandRequest);
    }

    @GetMapping("/{resourceReference}")
    @ResponseBody
    @Override
    public Resources getByReference(
            @PathVariable String resourceReference,
            @RequestHeader Map<String, String> headers) {
        AuthenticatedUser authenticatedUser = fromHeaders(headers);
        GenericResourceReferenceCommandRequest genericResourceReferenceCommandRequest = GenericResourceReferenceCommandRequest.builder()
                .reference(resourceReference)
                .authenticatedUser(authenticatedUser)
                .build();
        return this.getResourceByReferenceCommand.execute(genericResourceReferenceCommandRequest);
    }

    @DeleteMapping("/{resourceReference}")
    @Override
    public void deleteResource(
            @PathVariable String resourceReference,
            @RequestHeader Map<String, String> headers) {
        AuthenticatedUser authenticatedUser = fromHeaders(headers);
        GenericResourceReferenceCommandRequest genericResourceReferenceCommandRequest = GenericResourceReferenceCommandRequest.builder()
                .reference(resourceReference)
                .authenticatedUser(authenticatedUser)
                .build();
        this.deleteResourceCommand.execute(genericResourceReferenceCommandRequest);
    }

    @GetMapping("/name/{name}")
    @ResponseBody
    @Override
    public List<Resources> getByName(
            @PathVariable String name,
            @RequestHeader Map<String, String> headers) {
        AuthenticatedUser authenticatedUser = fromHeaders(headers);
        GetResourceByNameCommandRequest getResourceByNameCommandRequest = GetResourceByNameCommandRequest.builder()
                .name(name)
                .authenticatedUser(authenticatedUser)
                .build();
        return this.getResourcesByNameCommand.execute(getResourceByNameCommandRequest);
    }

    @GetMapping("/category/{category}")
    @ResponseBody
    @Override
    public List<Resources> getByCategory(
            @PathVariable String category,
            @RequestHeader Map<String, String> headers) {
        AuthenticatedUser authenticatedUser = fromHeaders(headers);
        GetResourceByCategoryCommandRequest getResourceByCategoryCommandRequest = GetResourceByCategoryCommandRequest.builder()
                .category(category)
                .authenticatedUser(authenticatedUser)
                .build();
        return this.getResourcesByCategoryCommand.execute(getResourceByCategoryCommandRequest);
    }

    @GetMapping
    @ResponseBody
    @Override
    public List<Resources> listResources(@RequestHeader Map<String, String> headers) {
        AuthenticatedUser authenticatedUser = fromHeaders(headers);
        GenericResourceListCommandRequest genericResourceListCommandRequest = GenericResourceListCommandRequest.builder()
                .authenticatedUser(authenticatedUser)
                .build();
        return this.getResourceListCommand.execute(genericResourceListCommandRequest);
    }

}
