package io.angularpay.resources.ports.inbound;

import io.angularpay.resources.domain.Resources;
import io.angularpay.resources.models.GenericReferenceResponse;
import io.angularpay.resources.models.GenericResourceApiModel;

import java.util.List;
import java.util.Map;

public interface RestApiPort {

    GenericReferenceResponse create(GenericResourceApiModel genericResourceApiModel, Map<String, String> headers);
    void update(String resourceReference, GenericResourceApiModel genericResourceApiModel, Map<String, String> headers);
    Resources getByReference(String resourceReference, Map<String, String> headers);
    void deleteResource(String resourceReference, Map<String, String> headers);
    List<Resources> getByName(String name, Map<String, String> headers);
    List<Resources> getByCategory(String category, Map<String, String> headers);
    List<Resources> listResources(Map<String, String> headers);
}
