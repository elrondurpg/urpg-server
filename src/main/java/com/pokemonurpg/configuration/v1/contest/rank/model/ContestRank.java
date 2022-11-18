package com.pokemonurpg.configuration.v1.contest.rank.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.contest.rank.ContestRankViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;

import javax.persistence.*;

@Table(name = "contest_rank")
@Entity
@JsonView(value = { ContestRankViews.Id.class })
public class ContestRank extends NamedConfigurationModel {

}
