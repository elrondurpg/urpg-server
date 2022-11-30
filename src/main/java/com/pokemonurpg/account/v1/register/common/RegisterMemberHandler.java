package com.pokemonurpg.account.v1.register.common;
import com.pokemonurpg.account.v1.register.common.internal.CreateMemberHandler;
import com.pokemonurpg.account.v1.register.common.internal.CreateMemberRequest;
import com.pokemonurpg.account.v1.register.common.internal.CreateMemberResponse;
import com.pokemonurpg.security.service.OAuthService;

public abstract class RegisterMemberHandler<
    Request extends RegisterMemberRequest, 
    Response extends RegisterMemberResponse,
    MemberRequest extends CreateMemberRequest,
    MemberResponse extends CreateMemberResponse> {

    protected OAuthService oAuthService;
    protected CreateMemberHandler<MemberRequest, MemberResponse> memberHandler;

    public RegisterMemberHandler(OAuthService oAuthService,
            CreateMemberHandler<MemberRequest, MemberResponse> memberHandler) {
        this.oAuthService = oAuthService;
        this.memberHandler = memberHandler;
    }

    public Response handle(Request request) {
        MemberRequest memberRequest = createMemberRequest(request);
        MemberResponse memberResponse = memberHandler.handle(memberRequest);
        return createResponse(memberResponse);
    }

    protected abstract MemberRequest createMemberRequest(Request request);
    protected abstract Response createResponse(MemberResponse memberResponse);
}
