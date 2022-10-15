package com.github.mrllli.stateMachine.infra;

public interface StateMachineConfigurator<S, T, E, M extends EventMessage> {

    void configure(StateMachineTransitionConfigurator<S, T, E, M> configurator);
}
