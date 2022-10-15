package com.github.mrllli.stateMachine.infra;

public class StateMachineConfiguration<S, T, E, M extends EventMessage> {

    private final StateMachineTransitionConfig<S, T, E, M> transitionConfig;

    public StateMachineConfiguration(StateMachineTransitionConfig<S, T, E, M> transitionConfig) {
        this.transitionConfig = transitionConfig;
    }

    public StateMachineTransitionConfig<S, T, E, M> getTransitionConfig() {
        return transitionConfig;
    }
}
