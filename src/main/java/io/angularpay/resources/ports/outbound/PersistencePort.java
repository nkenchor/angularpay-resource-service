package io.angularpay.resources.ports.outbound;

import io.angularpay.resources.domain.Resources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PersistencePort {
    Resources createResource(Resources resources);
    Resources updateResource(Resources resources);
    Optional<Resources> findResourceByReference(String reference);
    List<Resources> findResourceByName(String name);
    List<Resources> listResources();
    void deleteResource(Resources resources);
    List<Resources> findResourceByCategory(String category);
}
