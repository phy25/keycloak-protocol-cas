package org.keycloak.protocol.cas.mappers;

import org.keycloak.protocol.oidc.mappers.OIDCAttributeMapperHelper;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.ArrayList;
import java.util.List;

public class HardcodedClaim extends AbstractCASProtocolMapper {
    private static final List<ProviderConfigProperty> configProperties = new ArrayList<ProviderConfigProperty>();

    public static final String CLAIM_VALUE = "claim.value";

    static {
        OIDCAttributeMapperHelper.addTokenClaimNameConfig(configProperties);

        ProviderConfigProperty property = new ProviderConfigProperty();
        property.setName(CLAIM_VALUE);
        property.setLabel("Claim value");
        property.setType(ProviderConfigProperty.STRING_TYPE);
        property.setHelpText("Value of the claim you want to hard code.  'true' and 'false can be used for boolean values.");
        configProperties.add(property);

        OIDCAttributeMapperHelper.addJsonTypeConfig(configProperties);
    }

    public static final String PROVIDER_ID = "cas-hardcoded-claim-mapper";


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
        return "Hardcoded claim";
    }

    @Override
    public String getHelpText() {
        return "Hardcode a claim into the token.";
    }

}
