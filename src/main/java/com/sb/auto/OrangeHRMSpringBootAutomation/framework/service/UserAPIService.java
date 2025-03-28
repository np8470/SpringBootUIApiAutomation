package com.sb.auto.OrangeHRMSpringBootAutomation.framework.service;

import com.sb.auto.OrangeHRMSpringBootAutomation.framework.constant.ApiConstants;
import com.sb.auto.OrangeHRMSpringBootAutomation.framework.util.RestAssuredUtils;
import com.sb.auto.OrangeHRMSpringBootAutomation.models.User;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAPIService {

    @Autowired
    private RestAssuredUtils restAssuredUtils;

    public Response getUser(String userId)
    {
        //return restAssuredUtils.getRequest("/users/" + userId);
        return restAssuredUtils.getRequest(ApiConstants.FORWARD_SLASH + ApiConstants.USERS + ApiConstants.FORWARD_SLASH + userId);
    }

    public Response createUser(User user)
    {
        //return restAssuredUtils.postRequest("/users", user);
        return restAssuredUtils.postRequest(ApiConstants.FORWARD_SLASH + ApiConstants.USERS, user);
    }

    public Response updateUser(String userId, User user)
    {
        //return restAssuredUtils.putRequest("/users/" + userId, user);
        return restAssuredUtils.putRequest(ApiConstants.FORWARD_SLASH + ApiConstants.USERS + ApiConstants.FORWARD_SLASH + userId, user);
    }

    public Response deleteUser(String userId)
    {
        //return restAssuredUtils.deleteRequest("/users/" + userId);
        return restAssuredUtils.deleteRequest(ApiConstants.FORWARD_SLASH + ApiConstants.USERS + ApiConstants.FORWARD_SLASH + userId);
    }
}
