package com.github.mrllli.stateMachine.infra;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class StateMachine<S, T, E, M extends EventMessage> {

    private static final Logger log = LoggerFactory.getLogger(StateMachine.class);

    private final StateMachineTransitionConfig<S, T, E, M> stateMachineTransitionConfig;

    private Map<MapKey<S,E>, Transition<S, T, E, M>> transitionMap;

    private String stateMachineName;

    public StateMachine(StateMachineConfigurator<S, T, E, M> stateMachineConfigurator) {
        StateMachineConfiguration<S, T, E, M> stateMachineConfiguration =
                new StateMachineConfigurationBuilder<>(stateMachineConfigurator).build();
        this.stateMachineTransitionConfig = stateMachineConfiguration.getTransitionConfig();
        initTransitionConfig();
        initStateMachineName();
        log.info("init stateMachine finished... stateMachineName:{}", stateMachineName);
    }

    public void run(S source, E event, M msg) {
        preBizCheck(source, event, msg);
        MapKey<S, E> mapKey = new MapKey<>(source, event);
        Preconditions.checkArgument(ObjectUtils.isNotEmpty(transitionMap.get(mapKey)),
                "stateMachine not find transition source:{}, event:{}", source, event);
        Action<S, T, E, M> action = transitionMap.get(mapKey).getAction();
        T target = transitionMap.get(mapKey).getTarget();
        Preconditions.checkArgument(ObjectUtils.isNotEmpty(action),
                "stateMachine action is null source:{}, event:{}", source, event);
        Preconditions.checkArgument(ObjectUtils.isNotEmpty(target),
                "stateMachine target is null source:{}, event:{}", source, event);
        DefaultActionContext<S, T, E, M> actionContext = new DefaultActionContext<>(source, target, event, msg);
        action.doBiz(actionContext);
    }

    private void initTransitionConfig() {
        this.transitionMap = this.stateMachineTransitionConfig.getTransitionList().stream().collect(
                Collectors.toMap(transition -> new MapKey<>(transition.getSource(),
                        transition.getEvent()), transition -> transition));
    }

    private void initStateMachineName() {
        this.stateMachineName = this.getClass().getSimpleName();
    }

    public abstract void preBizCheck(S source, E event, M msg);

    private static class MapKey<S, E> {
        private S s;
        private E e;

        public MapKey(S s, E e) {
            this.s = s;
            this.e = e;
        }

        public S getS() {
            return s;
        }

        public void setS(S s) {
            this.s = s;
        }

        public E getE() {
            return e;
        }

        public void setE(E e) {
            this.e = e;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MapKey<?, ?> mapKey = (MapKey<?, ?>) o;
            return s.equals(mapKey.s) && e.equals(mapKey.e);
        }

        @Override
        public int hashCode() {
            return Objects.hash(s, e);
        }
    }
}
