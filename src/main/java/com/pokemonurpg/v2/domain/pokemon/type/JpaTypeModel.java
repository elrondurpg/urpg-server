package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.pokemonurpg.v2.domain.pokemon.type.TypeConstants.MAX_TYPE_NAME_LENGTH;

@Entity
@Table(name = "type")
@Getter
@Setter
public class JpaTypeModel implements Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 6)
    private Integer dbid;

    @Column(nullable = false, unique = true, length = MAX_TYPE_NAME_LENGTH)
    private String name;
}
