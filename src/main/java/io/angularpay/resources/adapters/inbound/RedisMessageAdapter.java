package io.angularpay.resources.adapters.inbound;

import io.angularpay.resources.domain.commands.PlatformConfigurationsConverterCommand;
import io.angularpay.resources.models.platform.PlatformConfigurationIdentifier;
import io.angularpay.resources.ports.inbound.InboundMessagingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.angularpay.resources.models.platform.PlatformConfigurationSource.TOPIC;

@Service
@RequiredArgsConstructor
public class RedisMessageAdapter implements InboundMessagingPort {

    private final PlatformConfigurationsConverterCommand converterCommand;

    @Override
    public void onMessage(String message, PlatformConfigurationIdentifier identifier) {
        this.converterCommand.execute(message, identifier, TOPIC);
    }
}
