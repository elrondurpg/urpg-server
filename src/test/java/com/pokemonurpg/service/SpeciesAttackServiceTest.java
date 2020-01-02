package com.pokemonurpg.service;

public class SpeciesAttackServiceTest {
/*
    private SpeciesAttackService speciesAttackService;
    private SpeciesAttackRepository speciesAttackRepository = mock(SpeciesAttackRepository.class);

    private static final Species EXISTING_SPECIES = new Species();
    private static final Integer EXISTING_SPECIES_DBID = 123;

    private static final Attack ATTACK1 = new Attack();
    private static final Integer ATTACK1_DBID = 456;

    private static final Species NEW_SPECIES = new Species();
    private static final Integer NEW_SPECIES_DBID = 321;

    private static final Attack ATTACK2 = new Attack();
    private static final Integer ATTACK2_DBID = 654;

    private static final SpeciesAttack SA_RECORD1 = new SpeciesAttack();
    private static final SpeciesAttack SA_RECORD2 = new SpeciesAttack();

    @Before
    public void initService() {
        initData();
        speciesAttackService = new SpeciesAttackService(speciesAttackRepository);
    }

    private void initData() {
        EXISTING_SPECIES.setDbid(EXISTING_SPECIES_DBID);
        EXISTING_SPECIES.setAttacks(Arrays.asList(SA_RECORD1));
        ATTACK1.setDbid(ATTACK1_DBID);

        NEW_SPECIES.setDbid(NEW_SPECIES_DBID);
        NEW_SPECIES.setAttacks(Arrays.asList(SA_RECORD2));
        ATTACK2.setDbid(ATTACK2_DBID);

        SA_RECORD1.setId(new SpeciesAttackKey(EXISTING_SPECIES_DBID, ATTACK1_DBID));
        SA_RECORD1.setAttack(ATTACK1);
        SA_RECORD1.setSpecies(EXISTING_SPECIES);
        SA_RECORD2.setId(new SpeciesAttackKey(NEW_SPECIES_DBID, ATTACK2_DBID));
        SA_RECORD2.setAttack(ATTACK2);
        SA_RECORD2.setSpecies(NEW_SPECIES);
    }

    @Test
    public void findAll() {
        when(speciesAttackRepository.findAll()).thenReturn(Arrays.asList(SA_RECORD1, SA_RECORD2));

        List<SpeciesAttack> allRecords = speciesAttackService.findAll();
        assertEquals(2, allRecords.size());
        assertEquals(EXISTING_SPECIES_DBID, allRecords.get(0).internalGetId().getSpeciesDbid());
        assertEquals(ATTACK1_DBID, allRecords.get(0).internalGetId().getAttackDbid());
        assertEquals(NEW_SPECIES_DBID, allRecords.get(1).internalGetId().getSpeciesDbid());
        assertEquals(ATTACK2_DBID, allRecords.get(1).internalGetId().getAttackDbid());
    }

    @Test
    public void findBySpeciesAttackKey() {
        when(speciesAttackRepository.findById(SA_RECORD1.internalGetId())).thenReturn(Optional.of(SA_RECORD1));

        Optional<SpeciesAttack> saOptional = speciesAttackService.findBySpeciesAttackKey(SA_RECORD1.internalGetId());
        assertTrue(saOptional.isPresent());
        assertEquals(EXISTING_SPECIES_DBID, saOptional.get().internalGetId().getSpeciesDbid());
        assertEquals(ATTACK1_DBID, saOptional.get().internalGetId().getAttackDbid());
    }

    @Test
    public void save() {
        speciesAttackService.save(SA_RECORD1);
        verify(speciesAttackRepository, times(1)).save(SA_RECORD1);
    }

    @Test
    public void saveFailsWhenRecordIsNull() {
        speciesAttackService.save(null);
        verify(speciesAttackRepository, times(0)).save(any(SpeciesAttack.class));
    }

    @Test
    public void saveFailsWhenRecordIsMissingInternalId() {
        SpeciesAttack record = new SpeciesAttack();
        speciesAttackService.save(record);
        verify(speciesAttackRepository, times(0)).save(any(SpeciesAttack.class));
    }

    @Test
    public void saveFailsWhenRecordIdIsMissingAttackDbid() {
        SpeciesAttack record = new SpeciesAttack();
        SpeciesAttackKey key = new SpeciesAttackKey();
        key.setSpeciesDbid(EXISTING_SPECIES_DBID);
        record.setId(key);

        speciesAttackService.save(record);
        verify(speciesAttackRepository, times(0)).save(any(SpeciesAttack.class));
    }

    @Test
    public void saveFailsWhenRecordIdIsMissingSpeciesDbid() {
        SpeciesAttack record = new SpeciesAttack();
        SpeciesAttackKey key = new SpeciesAttackKey();
        key.setAttackDbid(ATTACK1_DBID);
        record.setId(key);

        speciesAttackService.save(record);
        verify(speciesAttackRepository, times(0)).save(any(SpeciesAttack.class));
    }

    @Test
    public void saveListFromSpecies() {
        speciesAttackService.saveSpeciesAttacksFromSpecies(NEW_SPECIES, EXISTING_SPECIES);
        verify(speciesAttackRepository, times(1)).save(SA_RECORD2);
        verify(speciesAttackRepository, times(1)).delete(SA_RECORD1.secretGetSpecies().getDbid(), SA_RECORD1.getAttack().getDbid());
    }

    @Test
    public void saveListFromSpeciesUpdateExistingAttack() {
        speciesAttackService.saveSpeciesAttacksFromSpecies(NEW_SPECIES, EXISTING_SPECIES);
        verify(speciesAttackRepository, times(1)).save(SA_RECORD2);
        verify(speciesAttackRepository, times(1)).delete(SA_RECORD1.secretGetSpecies().getDbid(), SA_RECORD1.getAttack().getDbid());
    }

    @Test
    public void getAttacksMappedByDbid() {
        HashMap<Integer, SpeciesAttack> records = speciesAttackService.getAttacksMappedByDbid(EXISTING_SPECIES);
        assertEquals(1, records.size());
        assertTrue(records.containsKey(ATTACK1_DBID));
        assertEquals(EXISTING_SPECIES_DBID, records.get(ATTACK1_DBID).internalGetId().getSpeciesDbid());
    }

    @Test
    public void delete() {
    }*/
}