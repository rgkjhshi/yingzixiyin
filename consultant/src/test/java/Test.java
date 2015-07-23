import com.google.common.collect.Maps;

import java.util.Date;
import java.util.Map;

/**
 * @author song.shi
 * @date 2015-07-14
 */

public class Test {
    public static void main(String[] args) {
        Map<String ,Object> map = Maps.newHashMap();
        map.put("name", "test");
        map.put("date", new Date());

        System.out.println("obj=" + null);
    }
}
