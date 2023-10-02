package io.angularpay.resources.domain.commands;

public interface ResourceReferenceCommand<T, R> {

    R map(T referenceResponse);
}
