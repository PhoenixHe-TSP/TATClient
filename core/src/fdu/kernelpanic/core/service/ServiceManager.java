package fdu.kernelpanic.core.service;

import android.util.Log;
import fdu.kernelpanic.core.test.PlusService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 管理所有Service的初始化和启动
 */
public class ServiceManager extends TATService {
    private static final ServiceManager instance = new ServiceManager();

    private static final Class<? extends TATService>[] serviceClasses =
            new Class[]{ PlusService.class };

    public static ServiceManager get() {
        return instance;
    }

    private HashMap<Class<? extends TATService>, TATService> serviceMap = new HashMap<>();
    private ArrayList<TATService> services = new ArrayList<>();

    public <T> T service(Class<T> t) {
        return (T) serviceMap.get(t);
    }

    @Override
    protected void init() {
        for (Class<? extends TATService> service : serviceClasses) {
            try {
                TATService newInstance = service.newInstance();
                services.add(newInstance);
                serviceMap.put(service, newInstance);
            } catch (Exception e) {
                Log.e("ServiceManager", "Cannot construct service:" + service.getSimpleName(), e);
            }
        }
    }

    @Override
    protected void doStart() {
        for (TATService service : services)
            service.start();
    }

    @Override
    public void doStop() {
        for (TATService service : services)
            service.doStop();
    }
}
