package com.github.mrllli.stateMachine.infra;

public interface TransitionConfigurator<S, T, E, M extends EventMessage>
        extends Builder<Transition<S, T, E, M>>, And<StateMachineTransitionConfigurator<S, T, E, M>> {

    TransitionConfigurator<S, T, E, M> source(S s);

    TransitionConfigurator<S, T, E, M> target(T t);

    TransitionConfigurator<S, T, E, M> event(E e);

    TransitionConfigurator<S, T, E, M> action(Action<S, T, E, M> action);

    Transition<S, T, E, M> build();

    StateMachineTransitionConfigurator<S, T, E, M> and();
}
