package io.angularpay.resources.adapters.outbound;

import io.angularpay.resources.domain.Resources;
import io.angularpay.resources.ports.outbound.PersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MongoAdapter implements PersistencePort {

    private final ResourcesRepository resourcesRepository;

    @Override
    public Resources createResource(Resources request) {
        request.setCreatedOn(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
        request.setLastModified(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
        return resourcesRepository.save(request);
    }

    @Override
    public Resources updateResource(Resources request) {
        request.setLastModified(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
        return resourcesRepository.save(request);
    }

    @Override
    public Optional<Resources> findResourceByReference(String reference) {
        return resourcesRepository.findByReference(reference);
    }

    @Override
    public void deleteResource(Resources resources) {
        resourcesRepository.delete(resources);
    }

    @Override
    public List<Resources> findResourceByCategory(String category) {
        return resourcesRepository.findByCategory(category);
    }

    @Override
    public List<Resources> findResourceByName(String name) {
        return resourcesRepository.findByName(name);
    }

    @Override
    public List<Resources> listResources() {
        return resourcesRepository.findAll();
    }

}
