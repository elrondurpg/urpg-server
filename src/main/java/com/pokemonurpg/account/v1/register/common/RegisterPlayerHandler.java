package com.pokemonurpg.account.v1.register.common;
import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberHandler;
import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberRequest;
import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberResponse;
import com.pokemonurpg.security.service.OAuthService;

import javax.annotation.Resource;

public abstract class RegisterPlayerHandler<
    Request extends RegisterPlayerRequest, 
    Response extends RegisterPlayerResponse,
    MemberRequest extends CreateMemberRequest,
    MemberResponse extends CreateMemberResponse> {

    @Resource
    protected OAuthService oAuthService;

    @Resource
    protected CreateMemberHandler<MemberRequest, MemberResponse> memberHandler;

    public Response handle(Request request) {
        MemberRequest memberRequest = createMemberRequest(request);
        MemberResponse memberResponse = memberHandler.handle(memberRequest);
        return createResponse(memberResponse);
    }

    protected abstract MemberRequest createMemberRequest(Request request);
    protected abstract Response createResponse(MemberResponse memberResponse);
}
