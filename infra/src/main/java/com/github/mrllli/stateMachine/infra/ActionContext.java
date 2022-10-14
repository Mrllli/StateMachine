package com.github.mrllli.stateMachine.infra;

public interface ActionContext<S, T, E, M extends EventMessage> {
    S source();

    T target();

    E event();

    M eventMessage();
}
