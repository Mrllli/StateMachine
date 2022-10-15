package com.github.mrllli.stateMachine.infra;

public class Transition<S, T, E, M extends EventMessage> {

    private final S source;

    private final T target;

    private final E event;

    private final Action<S, T, E, M> action;

    public Transition(S source, T target, E event, Action<S, T, E, M> action) {
        this.source = source;
        this.target = target;
        this.event = event;
        this.action = action;
    }

    public S getSource() {
        return source;
    }

    public T getTarget() {
        return target;
    }

    public E getEvent() {
        return event;
    }

    public Action<S, T, E, M> getAction() {
        return action;
    }
}
