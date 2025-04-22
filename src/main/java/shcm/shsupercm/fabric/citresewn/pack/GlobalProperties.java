package shcm.shsupercm.fabric.citresewn.pack;

import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import org.thinkingstudio.citfoxified.cit.impl.CITRegistrarImpl;
import shcm.shsupercm.fabric.citresewn.CITResewn;
import shcm.shsupercm.fabric.citresewn.pack.format.PropertyGroup;
import shcm.shsupercm.fabric.citresewn.pack.format.PropertyKey;
import shcm.shsupercm.fabric.citresewn.pack.format.PropertyValue;
import shcm.shsupercm.fabric.citresewn.api.CITGlobalProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Property group representation of the global cit.properties file.
 * @see CITGlobalProperties
 * @see PackParser#loadGlobalProperties(ResourceManager, GlobalProperties)
 */
public class GlobalProperties extends PropertyGroup {
    public GlobalProperties() {
        super("global_properties", Identifier.of("citresewn", "global_properties"));
    }

    @Override
    public String getExtension() {
        return ".properties";
    }

    @Override
    public PropertyGroup load(String packName, Identifier identifier, InputStream is) throws IOException, InvalidIdentifierException {
        PropertyGroup group = PropertyGroup.tryParseGroup(packName, identifier, is);
        if (group != null)
            for (Map.Entry<PropertyKey, Set<PropertyValue>> entry : group.properties.entrySet())
                this.properties.computeIfAbsent(entry.getKey(), key -> new LinkedHashSet<>()).addAll(entry.getValue());

        return this;
    }

    /**
     * Calls all {@link CITGlobalProperties} handler entrypoints for every global property they're associated with.<br>
     * Global properties are matched to their entrypoints by mod id and it's the handler responsibility to filter the properties.
     *
     * @see CITGlobalProperties
     */
    public void callHandlers() {
        CITRegistrarImpl.getGlobalProperties().forEach((namespace, globalProperties) -> {
            for (Map.Entry<PropertyKey, Set<PropertyValue>> entry : properties.entrySet())
                if (entry.getKey().namespace().equals(namespace)) {
                    PropertyValue lastValue = null;
                    for (PropertyValue value : entry.getValue())
                        lastValue = value;

                    try {
                        globalProperties.globalProperty(entry.getKey().path(), lastValue);
                    } catch (Exception e) {
                        CITResewn.logErrorLoading(lastValue == null ? "Errored while disposing global properties" : "Errored while parsing global properties: Line " + lastValue.position() + " of " + lastValue.propertiesIdentifier() + " in " + lastValue.packName());
                        e.printStackTrace();
                    }
                }
        });
    }
}
