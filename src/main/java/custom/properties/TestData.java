package custom.properties;

import org.aeonbits.owner.ConfigFactory;

public class TestData {
    public static DriverProps driverProps = ConfigFactory.create(DriverProps.class);
    public static UrlProps urlProps = ConfigFactory.create(UrlProps.class);
}
