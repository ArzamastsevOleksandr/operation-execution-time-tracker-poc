package poc.operationexecutiontimetracker.util;

import lombok.SneakyThrows;

public interface Wait {

    @SneakyThrows
    static void sleepMillis(long millis) {
        Thread.sleep(millis);
    }

}
