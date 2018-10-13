package us.bojie.tradebo;

import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static Map<String, String> buildOauthRequestFieldMap() {
        Map<String, String> res = new HashMap<>();
        res.put("grant_type", "password");
        res.put("scope", Constant.SCOPE);
        res.put("client_id", Constant.CLIENT_ID);
        res.put("password", "");
        res.put("username", "");
        return res;
    }
}
