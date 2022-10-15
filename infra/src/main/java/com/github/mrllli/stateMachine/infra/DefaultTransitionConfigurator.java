package com.github.mrllli.stateMachine.infra;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.ObjectUtils;

public class DefaultTransitionConfigurator<S, T, E, M extends EventMessage>
        implements TransitionConfigurator<S, T, E, M> {

    private S source;

    private T target;

    private E event;

    private Action<S, T, E, M> action;

    private StateMachineTransitionConfigurator<S, T, E, M> stateMachineTransitionConfigurator;

    public DefaultTransitionConfigurator(
            StateMachineTransitionConfigurator<S, T, E, M> stateMachineTransitionConfigurator) {
        this.stateMachineTransitionConfigurator = stateMachineTransitionConfigurator;
    }

    @Override
    public TransitionConfigurator<S, T, E, M> source(S s) {
        this.source = s;
        return this;
    }

    @Override
    public TransitionConfigurator<S, T, E, M> target(T t) {
        this.target = t;
        return this;
    }

    @Override
    public TransitionConfigurator<S, T, E, M> event(E e) {
        this.event = e;
        return this;
    }

    @Override
    public TransitionConfigurator<S, T, E, M> action(Action<S, T, E, M> action) {
        this.action = action;
        return this;
    }

    @Override
    public Transition<S, T, E, M> build() {

        Preconditions.checkArgument(ObjectUtils.isNotEmpty(source), "stateMachine source is empty");
        Preconditions.checkArgument(ObjectUtils.isNotEmpty(target), "stateMachine target is empty");
        Preconditions.checkArgument(ObjectUtils.isNotEmpty(event), "stateMachine event is empty");
        Preconditions.checkArgument(ObjectUtils.isNotEmpty(action), "stateMachine action is empty");

        return new Transition<>(source, target, event, action);
    }

    @Override
    public StateMachineTransitionConfigurator<S, T, E, M> and() {
        return stateMachineTransitionConfigurator;
    }
}
