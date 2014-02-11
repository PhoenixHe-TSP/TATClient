package fdu.kernelpanic.core.test;

import android.util.Log;
import fdu.kernelpanic.core.service.TATService;

/**
 * Created by Phoenix on 14-2-12.
 */
public class PlusService extends TATService {
    @Override
    protected void init() {
        Log.d("PlusService", "init");
    }

    @Override
    protected void doStart() {
        Log.d("PlusService", "doStart");
    }

    @Override
    public void doStop() {
        Log.d("PlusService", "doStop");
    }

    public int plus(int a, int b) {
        return a + b;
    }
}
