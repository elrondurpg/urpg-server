package com.pokemonurpg.repository;

import com.pokemonurpg.object.trainer.LogRecord;
import com.pokemonurpg.object.trainer.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LogRecordRepository extends JpaRepository<LogRecord, Integer> {
    @Query("select log from LogRecord log where log.timestamp > :date and log.member = :member order by log.timestamp")
    List<LogRecord> findLogsSinceDateForMember(@Param("date") Date date, @Param("member") Member member);

    //@Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
    //User findUserByStatusAndNameNamedParams(
    //  @Param("status") Integer status,
    //  @Param("name") String name);

    /*@Query("select x.username from Member x")
    List<Object> findAllNames();
    LogRecord findByDbid(int dbid);
    Member findByUsername(String name);
    Member findByDiscordId(String discordId);
    List<Member> findByUsernameStartingWith(String username);*/
}
