import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {

    @ParameterizedTest
    @EnumSource(Country.class)
    public void testLocale(Country country) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected;

        switch (country) {
            case RUSSIA:
                expected = "Добро пожаловать";
                break;
            default:
                expected = "Welcome";
                break;
        }

        String actual = localizationService.locale(country);
        assertEquals(expected, actual);
    }
}