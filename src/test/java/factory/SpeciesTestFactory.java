package factory;

import com.pokemonurpg.object.Species;

public class SpeciesTestFactory {

    public static Species createSpecies() {
        return new Species(1, 1, "Pikachu", 1, 1,
                "TestClass", 300, 123, 321, 456, 321,
                134, 13.0, 10.0, true, false, "10000",
                1, 2, 3, 4, "40000", "Pikachu-Display",
                "Pikachu-Form");
    }

    public static Species createSpecies(String name) {
        Species species = createSpecies();
        species.setName(name);
        return species;
    }

}
