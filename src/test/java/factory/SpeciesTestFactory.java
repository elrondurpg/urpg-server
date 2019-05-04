package factory;

import com.pokemonurpg.object.Species;

public class SpeciesTestFactory {

    public static Species createSpecies() {
        Species species = new Species();
        species.setDbid(1);
        species.setName("Pikachu");
        species.setHp(300);
        return species;
    }

    public static Species createSpecies(String name) {
        Species species = createSpecies();
        species.setName(name);
        return species;
    }

}
