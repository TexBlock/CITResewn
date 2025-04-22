package org.thinkingstudio.citfoxified.cit.api;

import shcm.shsupercm.fabric.citresewn.api.CITConditionContainer;
import shcm.shsupercm.fabric.citresewn.api.CITDisposable;
import shcm.shsupercm.fabric.citresewn.api.CITGlobalProperties;
import shcm.shsupercm.fabric.citresewn.api.CITTypeContainer;

public interface CITRegistrar {
    void register(CITDisposable disposable);

    void register(String namespace, CITGlobalProperties globalProperties);

    void register(String namespace, CITTypeContainer<?> type);

    void register(String namespace, CITConditionContainer<?> condition);
}
