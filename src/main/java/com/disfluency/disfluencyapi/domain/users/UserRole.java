package com.disfluency.disfluencyapi.domain.users;

import com.disfluency.disfluencyapi.dto.users.UserRoleDTO;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "@type")
@Document(collection = "userRole")
public interface UserRole {

    UserRoleDTO toUserRoleDTO();

    void setFcmToken(String fcmToken);

    String getFcmToken();

    String getId();

    String getFcmTokenTherapist();
}
