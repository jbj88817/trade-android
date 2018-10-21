package us.bojie.tradebo.utils;

import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static Map<String, String> buildOauthRequestFieldMap() {
        Map<String, String> res = new HashMap<>();
        res.put("grant_type", Constant.GRANT_TYPE);
        res.put("scope", Constant.SCOPE);
        res.put("client_id", Constant.CLIENT_ID);
        return res;
    }
}
