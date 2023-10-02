package io.angularpay.resources.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateResourceCommandRequest extends AccessControl {

    @NotNull
    @Valid
    private GenericResourceApiModel genericResourceApiModel;

    CreateResourceCommandRequest(AuthenticatedUser authenticatedUser) {
        super(authenticatedUser);
    }
}
