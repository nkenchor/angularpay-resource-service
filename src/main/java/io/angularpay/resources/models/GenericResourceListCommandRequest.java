package io.angularpay.resources.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class GenericResourceListCommandRequest extends AccessControl {

    GenericResourceListCommandRequest(AuthenticatedUser authenticatedUser) {
        super(authenticatedUser);
    }
}
