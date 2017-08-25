package ru.andreymarkelov.atlas.plugins.copytoclipboard.manager.impl;

import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import ru.andreymarkelov.atlas.plugins.copytoclipboard.manager.PluginData;

public class PluginDataImpl implements PluginData {
    private static final String PLUGIN_KEY = "CopyToClipboard";

    private final PluginSettings pluginSettings;

    public PluginDataImpl(PluginSettingsFactory pluginSettingsFactory) {
        this.pluginSettings = pluginSettingsFactory.createSettingsForKey(PLUGIN_KEY);
    }

    @Override
    public String getCopyPattern(FieldConfig config) {
        Object obj = getPluginSettings().get(getKey(config));
        return obj != null ? obj.toString() : "";
    }

    @Override
    public void setCopyPattern(FieldConfig config, String copyPattern) {
        getPluginSettings().put(getKey(config), copyPattern);
    }

    private String getKey(FieldConfig config) {
        return config.getFieldId().concat("_").concat(config.getId().toString()).concat("_").concat("config");
    }

    private synchronized PluginSettings getPluginSettings() {
        return pluginSettings;
    }
}
