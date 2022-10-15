package com.github.mrllli.stateMachine.infra;

public class DefaultActionContext<S, T, E, M extends EventMessage> implements ActionContext<S, T, E, M> {

    private final S s;

    private final T t;

    private final E e;

    private final M m;

    public DefaultActionContext(S s, T t, E e, M m) {
        this.s = s;
        this.t = t;
        this.e = e;
        this.m = m;
    }

    @Override
    public S source() {
        return null;
    }

    @Override
    public T target() {
        return null;
    }

    @Override
    public E event() {
        return null;
    }

    @Override
    public M eventMessage() {
        return null;
    }
}
