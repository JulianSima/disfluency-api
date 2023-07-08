package com.disfluency.disfluencyapi.domain.users;

import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "@type")
public interface UserRole {

    UserRoleDTO toUserRoleDTO();

}
