package com.pokemonurpg.configuration.v1.gym.knownelitefourmember.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.KnownEliteFourMemberViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;

import javax.persistence.*;

@Entity
@JsonView(value = { KnownEliteFourMemberViews.Id.class })
public class KnownEliteFourMember extends NamedConfigurationModel {
}
