package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public class Mapper {
    public static Class<? extends PropInterface> getProperty() {
        String env = System.getProperty("env");

        if (env == null || env.equals("null") || env.equals("test")) {
            return PropInterface.class;
        } else {
            throw new RuntimeException("Invalid value for system property 'env'! xpected one of:[null, 'test']");
        }
    }

    public static final PropInterface PROP = ConfigFactory.create(getProperty());

    @Config.LoadPolicy(Config.LoadType.MERGE)
    @Config.Sources({"system:properties", "classpath:variables.values"})
    public interface PropInterface extends Config {
        @Key("url")
        String getURL();

        @Key("sessionId")
        String getSessionID();

        @Key("userToken")
        String getToken();

        @Key("Instamart_session")
        String getInstamartSession();
    }
}
