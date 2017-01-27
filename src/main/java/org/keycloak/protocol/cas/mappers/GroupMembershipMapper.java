package org.keycloak.protocol.cas.mappers;

import org.keycloak.models.ProtocolMapperModel;
import org.keycloak.protocol.cas.CASLoginProtocol;
import org.keycloak.protocol.oidc.OIDCLoginProtocol;
import org.keycloak.protocol.oidc.mappers.OIDCAttributeMapperHelper;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupMembershipMapper extends AbstractCASProtocolMapper {
    private static final List<ProviderConfigProperty> configProperties = new ArrayList<ProviderConfigProperty>();

    static {
        OIDCAttributeMapperHelper.addTokenClaimNameConfig(configProperties);
        ProviderConfigProperty property1 = new ProviderConfigProperty();
        property1.setName("full.path");
        property1.setLabel("Full group path");
        property1.setType(ProviderConfigProperty.BOOLEAN_TYPE);
        property1.setDefaultValue("true");
        property1.setHelpText("Include full path to group i.e. /top/level1/level2, false will just specify the group name");
        configProperties.add(property1);
    }

    public static final String PROVIDER_ID = "cas-group-membership-mapper";


    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "Group Membership";
    }

    @Override
    public String getHelpText() {
        return "Map user group membership";
    }

    public static boolean useFullPath(ProtocolMapperModel mappingModel) {
        return "true".equals(mappingModel.getConfig().get("full.path"));
    }

    public static ProtocolMapperModel create(String name, String tokenClaimName,
                                             boolean consentRequired, String consentText, boolean fullPath) {
        ProtocolMapperModel mapper = new ProtocolMapperModel();
        mapper.setName(name);
        mapper.setProtocolMapper(PROVIDER_ID);
        mapper.setProtocol(CASLoginProtocol.LOGIN_PROTOCOL);
        mapper.setConsentRequired(consentRequired);
        mapper.setConsentText(consentText);
        Map<String, String> config = new HashMap<String, String>();
        config.put(OIDCAttributeMapperHelper.TOKEN_CLAIM_NAME, tokenClaimName);
        config.put("full.path", Boolean.toString(fullPath));
        mapper.setConfig(config);

        return mapper;
    }
}
