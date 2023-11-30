import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {

    @ParameterizedTest
    @ValueSource(strings = {GeoServiceImpl.MOSCOW_IP, GeoServiceImpl.NEW_YORK_IP})

    public void testByIp(String ip) {
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location location = geoService.byIp(ip);

        switch (ip) {
            case GeoServiceImpl.MOSCOW_IP:
                assertEquals("Moscow", location.getCity(), "City mismatch for Moscow");
                assertEquals(Country.RUSSIA, location.getCountry(), "Country mismatch for Moscow");
                assertEquals("Lenina", location.getStreet(), "Street mismatch for Moscow");
                assertEquals(15, location.getBuilding(), "Building mismatch for Moscow");
                break;
            case GeoServiceImpl.NEW_YORK_IP:
                assertEquals("New York", location.getCity(), "City mismatch for New York");
                assertEquals(Country.USA, location.getCountry(), "Country mismatch for New York");
                assertEquals("10th Avenue", location.getStreet(), "Street mismatch for New York");
                assertEquals(32, location.getBuilding(), "Building mismatch for New York");
                break;
            default:
                assertNull(location, "Unexpected IP: " + ip);
        }
    }
}