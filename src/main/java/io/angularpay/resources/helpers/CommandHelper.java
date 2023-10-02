package io.angularpay.resources.helpers;

import io.angularpay.resources.adapters.outbound.MongoAdapter;
import io.angularpay.resources.domain.Resources;
import io.angularpay.resources.exceptions.CommandException;
import io.angularpay.resources.exceptions.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static io.angularpay.resources.exceptions.ErrorCode.REQUEST_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommandHelper {

    public static Resources getRequestByReferenceOrThrow(MongoAdapter mongoAdapter, String reference) {
        return mongoAdapter.findResourceByReference(reference).orElseThrow(
                () -> commandException(HttpStatus.NOT_FOUND, REQUEST_NOT_FOUND)
        );
    }

    private static CommandException commandException(HttpStatus status, ErrorCode errorCode) {
        return CommandException.builder()
                .status(status)
                .errorCode(errorCode)
                .message(errorCode.getDefaultMessage())
                .build();
    }

}
