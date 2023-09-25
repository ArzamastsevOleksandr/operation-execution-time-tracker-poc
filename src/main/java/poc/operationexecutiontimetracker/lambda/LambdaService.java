package poc.operationexecutiontimetracker.lambda;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.stream.LongStream;

import static poc.operationexecutiontimetracker.util.Wait.sleepMillis;

@Service
@RequiredArgsConstructor
class LambdaService {

    final LambdaTracker tracker;

    @SneakyThrows
    void outerOperation() {
        tracker.track("LdService.outerOperation", () -> {
            sleepMillis(500);
//            long count = LongStream.rangeClosed(0, streamRange).boxed().map(i -> 2).count();
            innerOperation(0);
            System.err.println("LdService.outerOperation finished");
        });
    }

    @SneakyThrows
    private void innerOperation(long count) {
        tracker.track("LdService.innerOperation", () -> {
//            long innerCount = LongStream.rangeClosed(0, streamRange * streamRange).boxed().map(i -> 2).count();
            sleepMillis(1000);
            System.err.println("LdService.innerOperation finished");
        });
    }
}
