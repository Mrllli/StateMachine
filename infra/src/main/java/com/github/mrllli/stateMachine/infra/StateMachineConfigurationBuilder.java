package com.github.mrllli.stateMachine.infra;

public class StateMachineConfigurationBuilder<S, T, E, M extends EventMessage>
        implements Builder<StateMachineConfiguration<S, T, E, M>> {

    private final StateMachineConfigurator<S, T, E, M> stateMachineConfigurator;

    public StateMachineConfigurationBuilder(StateMachineConfigurator<S, T, E, M> stateMachineConfigurator) {
        this.stateMachineConfigurator = stateMachineConfigurator;
    }

    @Override
    public StateMachineConfiguration<S, T, E, M> build() {

        StateMachineTransitionConfigurator<S, T, E, M> stateMachineTransitionConfigurator =
                new StateMachineTransitionConfigurator<>();

        this.stateMachineConfigurator.configure(stateMachineTransitionConfigurator);

        StateMachineTransitionConfig<S, T, E, M> transitionConfig = stateMachineTransitionConfigurator.build();

        return new StateMachineConfiguration<>(transitionConfig);
    }
}
