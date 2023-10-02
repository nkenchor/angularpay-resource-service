package io.angularpay.resources.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetResourceByNameCommandRequest extends AccessControl {

    @NotEmpty
    private String name;

    GetResourceByNameCommandRequest(AuthenticatedUser authenticatedUser) {
        super(authenticatedUser);
    }
}
