package com.github.mrllli.stateMachine.infra;

public interface Action<S, T, E, M extends EventMessage> {

    void doBiz(ActionContext<S, T, E, M> context);
}
