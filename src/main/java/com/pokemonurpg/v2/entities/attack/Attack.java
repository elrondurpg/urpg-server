package com.pokemonurpg.v2.entities.attack;

import com.pokemonurpg.v2.entities.NamedEntity;
import com.pokemonurpg.v2.entities.pokemon.Type;

public interface Attack extends NamedEntity {

    Type getType();
    void setType(Type type);

    String getDescription();
    void setDescription(String description);

    Integer getPower();
    void setPower(Integer power);

    Integer getAccuracy();
    void setAccuracy(Integer accuracy);

    Integer getPp();
    void setPp(Integer pp);

    DamageType getDamageType();
    void setDamageType(DamageType damageType);

    TargetType getTargetType();
    void setTargetType(TargetType targetType);

    boolean isContact();
    void setContact(boolean contact);

    boolean isSnatchable();
    void setSnatchable(boolean snatchable);

    boolean isBlockedBySubstitute();
    void setBlockedBySubstitute(boolean blockedBySubstitute);

    boolean isAffectedBySheerForce();
    void setAffectedBySheerForce(boolean affectedBySheerForce);

    boolean isAffectedByMagicCoat();
    void setAffectedByMagicCoat(boolean affectedByMagicCoat);



    /*@OneToOne
    @JoinColumn(name = "category")
    private AttackCategory category;

    @OneToOne
    @JoinColumn(name = "target")
    private AttackTargetType target;

    @Column
    private Boolean contact;

    @Column
    private Boolean snatch;

    @Column
    private Boolean substitute;

    @Column(name = "sheer_force")
    private Boolean sheerForce;

    @Column(name = "magic_coat")
    private Boolean magicCoat;

    @OneToOne
    @JoinColumn(name = "rse_contest_move_type")
    private RseContestMoveType rseContestMoveType;

    @OneToOne
    @JoinColumn(name = "rse_contest_attribute")
    private ContestAttribute rseContestAttribute;

    @OneToOne
    @JoinColumn(name = "oras_contest_move_type")
    private OrasContestMoveType orasContestMoveType;

    @OneToOne
    @JoinColumn(name = "oras_contest_attribute")
    private ContestAttribute orasContestAttribute;

    @OneToOne
    @JoinColumn(name = "tm_hm_dbid")
    private Item tm;

    @OneToMany(mappedBy = "attack")
    private Set<SpeciesAttack> pokemon;

    @OneToMany(mappedBy = "firstAttack")
    private Set<ContestCombo> contestCombos;*/

}
