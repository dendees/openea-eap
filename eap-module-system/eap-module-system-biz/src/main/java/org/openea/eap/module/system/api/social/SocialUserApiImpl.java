package org.openea.eap.module.system.api.social;

import org.openea.eap.module.system.api.social.dto.SocialUserBindReqDTO;
import org.openea.eap.module.system.api.social.dto.SocialUserUnbindReqDTO;
import org.openea.eap.module.system.service.social.SocialUserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 社交用户的 API 实现类
 *
 */
@Service
@Validated
public class SocialUserApiImpl implements SocialUserApi {

    @Resource
    private SocialUserService socialUserService;

    @Override
    public String getAuthorizeUrl(Integer type, String redirectUri) {
        return socialUserService.getAuthorizeUrl(type, redirectUri);
    }

    @Override
    public void bindSocialUser(SocialUserBindReqDTO reqDTO) {
        socialUserService.bindSocialUser(reqDTO);
    }

    @Override
    public void unbindSocialUser(SocialUserUnbindReqDTO reqDTO) {
        socialUserService.unbindSocialUser(reqDTO.getUserId(), reqDTO.getUserType(),
                reqDTO.getType(), reqDTO.getUnionId());
    }

    @Override
    public Long getBindUserId(Integer userType, Integer type, String code, String state) {
       return socialUserService.getBindUserId(userType, type, code, state);
    }

}
