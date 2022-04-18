package custom.properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:url.properties"
})

public interface UrlProps extends Config {
    @Config.Key("base.url.yandexlink")
    String baseUrlYandexLink();
}
