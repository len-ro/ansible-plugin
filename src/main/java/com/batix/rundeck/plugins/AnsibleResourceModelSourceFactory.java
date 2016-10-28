package com.batix.rundeck.plugins;

import com.batix.rundeck.core.AnsibleDescribable;
import com.dtolabs.rundeck.core.common.Framework;
import com.dtolabs.rundeck.core.plugins.Plugin;
import com.dtolabs.rundeck.core.plugins.configuration.ConfigurationException;
import com.dtolabs.rundeck.core.plugins.configuration.Description;
import com.dtolabs.rundeck.core.resources.ResourceModelSource;
import com.dtolabs.rundeck.core.resources.ResourceModelSourceFactory;
import com.dtolabs.rundeck.plugins.ServiceNameConstants;
import com.dtolabs.rundeck.plugins.util.DescriptionBuilder;

import java.util.Properties;

@Plugin(name = AnsibleResourceModelSourceFactory.SERVICE_PROVIDER_NAME, service = ServiceNameConstants.ResourceModelSource)
public class AnsibleResourceModelSourceFactory implements ResourceModelSourceFactory, AnsibleDescribable {
    public static final String SERVICE_PROVIDER_NAME = "com.batix.rundeck.plugins.AnsibleResourceModelSourceFactory";

    public static Description DESC = null;

    private Framework framework;

    public AnsibleResourceModelSourceFactory(final Framework framework) {
        this.framework = framework;
    }

    static {
        DescriptionBuilder builder = DescriptionBuilder.builder();
        builder.name(SERVICE_PROVIDER_NAME);
        builder.title("Ansible Resource Model Source");
        builder.description("Imports nodes from Ansible's inventory.");

        builder.property(INVENTORY_PROP);
        builder.property(GATHER_FACTS_PROP);
        builder.property(IGNORE_ERRORS_PROP);
        builder.property(LIMIT_PROP);
        builder.property(IGNORE_TAGS_PREFIX_PROP);
        builder.property(EXTRA_TAG_PROP);
        builder.property(SSH_AUTH_TYPE_PROP);
        builder.property(SSH_USER_PROP);
        builder.property(SSH_PASSWORD_PROP);
        builder.property(SSH_KEY_FILE_PROP); 
        builder.property(SSH_TIMEOUT_PROP);
        builder.property(BECOME_PROP);
        builder.property(BECOME_AUTH_TYPE_PROP);
        builder.property(BECOME_USER_PROP);
        builder.property(BECOME_PASSWORD_PROP);
        builder.mapping(ANSIBLE_INVENTORY,PROJ_PROP_PREFIX + ANSIBLE_INVENTORY);
        builder.frameworkMapping(ANSIBLE_INVENTORY,FWK_PROP_PREFIX + ANSIBLE_INVENTORY);

        DESC=builder.build();
    }

  @Override
  public ResourceModelSource createResourceModelSource(Properties configuration) throws ConfigurationException {
    AnsibleResourceModelSource ansibleResourceModelSource = new AnsibleResourceModelSource(framework);
    ansibleResourceModelSource.configure(configuration);
    return ansibleResourceModelSource;
  }

  @Override
  public Description getDescription() {
        return DESC;
  }
}