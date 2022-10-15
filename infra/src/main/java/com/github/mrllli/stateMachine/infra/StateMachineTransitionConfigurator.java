package com.github.mrllli.stateMachine.infra;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class StateMachineTransitionConfigurator<S, T, E, M extends EventMessage>
        implements Builder<StateMachineTransitionConfig<S, T, E, M>> {

    private List<TransitionConfigurator<S, T, E, M>> transitionConfiguratorList = Lists.newArrayList();

    public TransitionConfigurator<S, T, E, M> withTransition() {
        DefaultTransitionConfigurator<S, T, E, M> transitionConfigurator = new DefaultTransitionConfigurator<>(this);
        transitionConfiguratorList.add(transitionConfigurator);
        return transitionConfigurator;
    }

    @Override
    public StateMachineTransitionConfig<S, T, E, M> build() {

        List<Transition<S, T, E, M>> collect =
                transitionConfiguratorList.stream().map(TransitionConfigurator::build).collect(Collectors.toList());

        return new StateMachineTransitionConfig<>(collect);
    }
}
