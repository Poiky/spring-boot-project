package name.guolanren.component;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * @author guolanren
 * @date 2019-02-13
 */
public class MyPing implements IPing {
    @Override
    public boolean isAlive(Server server) {
        return true;
    }
}
