package us.bojie.tradebo.utils;

import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static Map<String, String> buildOauthRequestFieldMap() {
        Map<String, String> res = new HashMap<>();
        res.put("grant_type", Constants.GRANT_TYPE);
        res.put("scope", Constants.SCOPE);
        res.put("client_id", Constants.CLIENT_ID);
        return res;
    }
}
