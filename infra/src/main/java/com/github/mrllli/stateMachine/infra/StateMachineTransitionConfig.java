package com.github.mrllli.stateMachine.infra;

import java.util.List;

public class StateMachineTransitionConfig<S, T, E, M extends EventMessage> {

    private final List<Transition<S, T, E, M>> transitionList;

    public StateMachineTransitionConfig(List<Transition<S, T, E, M>> transitionList) {
        this.transitionList = transitionList;
    }

    public List<Transition<S, T, E, M>> getTransitionList() {
        return transitionList;
    }
}
