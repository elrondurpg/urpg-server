package com.pokemonurpg.configuration.v1.elitefourmembers;

import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourMemberRepository;
import com.pokemonurpg.entities.v1.EliteFourMember;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import com.pokemonurpg.configuration.v1.members.MemberRequest;
import com.pokemonurpg.entities.v1.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EliteFourMemberService implements NamedObjectService<EliteFourMember> {

    @Resource
    private EliteFourMemberRepository eliteFourMemberRepository;

    public List<String> findAllNames() {
        return eliteFourMemberRepository.findAllNames();
    }

    public EliteFourMember findByDbid(int dbid) {
        return eliteFourMemberRepository.findByDbid(dbid);
    }

    public EliteFourMember findByName(String name) {
        EliteFourMember eliteFourMember = findByNameExact(name);
        if (eliteFourMember == null && name != null) {
            return eliteFourMemberRepository.findFirstByNameStartingWith(name);
        }
        else return eliteFourMember;
    }

    @Override
    public EliteFourMember findByNameExact(String name) {
        return eliteFourMemberRepository.findByName(name);
    }

    public EliteFourMember create(String name) {
        EliteFourMember eliteFourMember = eliteFourMemberRepository.findByName(name);
        if (eliteFourMember == null) {
            eliteFourMember = new EliteFourMember(name);
            eliteFourMemberRepository.save(eliteFourMember);
        }
        return eliteFourMember;
    }

    public EliteFourMember create(EliteFourMemberRequest input) {
        EliteFourMember eliteFourMember = new EliteFourMember(input);
        eliteFourMemberRepository.save(eliteFourMember);
        return eliteFourMember;
    }

    public EliteFourMember update(EliteFourMemberRequest input, int dbid) {
        EliteFourMember eliteFourMember = eliteFourMemberRepository.findByDbid(dbid);
        if (eliteFourMember != null) {
            eliteFourMember.update(input);
            eliteFourMemberRepository.save(eliteFourMember);
        }
        return eliteFourMember;
    }

    public void rename(MemberRequest input, Member member) {
        EliteFourMember eliteFourMember = findByNameExact(member.getName());
        if (eliteFourMember != null) {
            eliteFourMember.setName(input.getName());
            eliteFourMemberRepository.save(eliteFourMember);
        }
    }
}
