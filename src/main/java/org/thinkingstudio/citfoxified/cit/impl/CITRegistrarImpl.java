package org.thinkingstudio.citfoxified.cit.impl;

import com.google.auto.service.AutoService;
import net.minecraft.util.Identifier;
import org.thinkingstudio.citfoxified.cit.api.CITRegistrar;
import shcm.shsupercm.fabric.citresewn.api.CITConditionContainer;
import shcm.shsupercm.fabric.citresewn.api.CITDisposable;
import shcm.shsupercm.fabric.citresewn.api.CITGlobalProperties;
import shcm.shsupercm.fabric.citresewn.api.CITTypeContainer;
import shcm.shsupercm.fabric.citresewn.cit.builtin.conditions.core.FallbackCondition;
import shcm.shsupercm.fabric.citresewn.cit.builtin.conditions.core.WeightCondition;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@AutoService(CITRegistrar.class)
public class CITRegistrarImpl implements CITRegistrar {
    public static final String INBUILT_NAMESPACE = "citresewn";

    private static final List<CITDisposable> DISPOSABLES = new LinkedList<>();
    private static final Map<String, CITGlobalProperties> GLOBAL_PROPERTIES = new LinkedHashMap<>();
    private static final Map<Identifier, List<CITTypeContainer<?>>> TYPES = new LinkedHashMap<>();
    private static final Map<String, List<CITConditionContainer<?>>> CONDITIONS = new LinkedHashMap<>();

    @Override
    public void register(CITDisposable disposable) {
        DISPOSABLES.add(disposable);
    }

    @Override
    public void register(String namespace, CITGlobalProperties globalProperties) {
        GLOBAL_PROPERTIES.put(namespace, globalProperties);
    }

    @Override
    public void register(String namespace, CITTypeContainer<?> type) {
        TYPES.compute(Identifier.of(namespace, type.id), (key, types) -> {
            List<CITTypeContainer<?>> value = types == null ? new LinkedList<>() : types;
            value.add(type);
            return value;
        });
    }

    @Override
    public void register(String namespace, CITConditionContainer<?> condition) {
        CONDITIONS.compute(namespace, (key, conditions) -> {
            List<CITConditionContainer<?>> value = conditions == null ? new LinkedList<>() : conditions;
            value.add(condition);
            return value;
        });
    }

    public static List<CITDisposable> getDisposables() {
        return DISPOSABLES;
    }

    public static Map<String, CITGlobalProperties> getGlobalProperties() {
        return GLOBAL_PROPERTIES;
    }

    public static Map<Identifier, List<CITTypeContainer<?>>> getTypes() {
        return TYPES;
    }

    public static Map<String, List<CITConditionContainer<?>>> getConditions() {
        return CONDITIONS;
    }

    static {
        CITRegistrarImpl impl = new CITRegistrarImpl();

        impl.register(INBUILT_NAMESPACE, FallbackCondition.PROPERTIES);
        impl.register(INBUILT_NAMESPACE, FallbackCondition.CONTAINER);
        impl.register(INBUILT_NAMESPACE, WeightCondition.CONTAINER);
    }
}
