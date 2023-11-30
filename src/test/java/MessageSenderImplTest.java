import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentCaptor;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class MessageSenderImplTest {

    private GeoService geoService;
    private LocalizationService localizationService;
    private MessageSenderImpl messageSender;

    @BeforeEach
    void setUp() {
        geoService = mock(GeoService.class);
        localizationService = mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    void testSendWithRussianIP() {

        ArgumentCaptor<Country> countryCaptor = ArgumentCaptor.forClass(Country.class);

        when(geoService.byIp("192.168.0.1")).thenReturn(new Location("Москва", Country.RUSSIA, "Тверская", 1));
        when(localizationService.locale(countryCaptor.capture())).thenReturn("Привет, Россия!");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "192.168.0.1");

        String result = messageSender.send(headers);
        assertEquals("Привет, Россия!", result);

        verify(geoService).byIp("192.168.0.1");
        assertEquals(Country.RUSSIA, countryCaptor.getValue());

    }

    @Test
    void testSendWithAmericanIP() {

        ArgumentCaptor<Country> countryCaptor = ArgumentCaptor.forClass(Country.class);

        when(geoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));
        when(localizationService.locale(countryCaptor.capture())).thenReturn("Hello, USA!");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        String result = messageSender.send(headers);
        assertEquals("Hello, USA!", result);

        verify(geoService).byIp("96.44.183.149");
        assertEquals(Country.USA, countryCaptor.getValue());
    }
}
