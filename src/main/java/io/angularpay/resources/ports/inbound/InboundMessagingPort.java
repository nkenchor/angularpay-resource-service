package io.angularpay.resources.ports.inbound;

import io.angularpay.resources.models.platform.PlatformConfigurationIdentifier;

public interface InboundMessagingPort {
    void onMessage(String message, PlatformConfigurationIdentifier identifier);
}
