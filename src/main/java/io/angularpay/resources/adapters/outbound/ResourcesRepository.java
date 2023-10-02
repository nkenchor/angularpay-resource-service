package io.angularpay.resources.adapters.outbound;

import io.angularpay.resources.domain.Resources;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ResourcesRepository extends MongoRepository<Resources, String> {

    Optional<Resources> findByReference(String reference);
    List<Resources> findByName(String name);
    List<Resources> findByCategory(String category);
}
