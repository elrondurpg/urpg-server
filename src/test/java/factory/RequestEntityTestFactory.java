package factory;

import com.pokemonurpg.object.Species;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class RequestEntityTestFactory {

    public static HttpEntity<Species> createSpeciesRequestEntity(Species species) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");

        return new HttpEntity<Species>(species, headers);
    }

}
