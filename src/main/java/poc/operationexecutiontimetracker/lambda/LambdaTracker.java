package poc.operationexecutiontimetracker.lambda;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import poc.operationexecutiontimetracker.domain.Stats;
import poc.operationexecutiontimetracker.domain.StatsRepository;
import poc.operationexecutiontimetracker.util.TrackerContext;

import java.util.UUID;
import java.util.concurrent.Callable;

@Slf4j
@Component
@RequiredArgsConstructor
public class LambdaTracker {

    final StatsRepository statsRepository;

    public void track(String operationName, Runnable action) {
        UUID operationUuid = operationUuid();
        Boolean clearContextAfter = clearContext();

        long startNano = System.nanoTime();
        action.run();
        long timeTakenNano = System.nanoTime() - startNano;

        log.info("[operation=%s, timeTakenNano=%s]".formatted(operationName, timeTakenNano));

        clearContextIfRequired(clearContextAfter);

        statsRepository.save(new Stats()
                .operationName(operationName)
                .timeTakenNanos(timeTakenNano)
                .operationUuid(operationUuid));
    }

    @SneakyThrows
    public <T> T track(String operationName, Callable<T> callable) {
        UUID operationUuid = operationUuid();
        Boolean clearContextAfter = clearContext();
        String tag = TrackerContext.tag();

        long startNano = System.nanoTime();
        T result = callable.call();
        long timeTakenNano = System.nanoTime() - startNano;

        log.info("[operation=%s, timeTakenNano=%s]".formatted(operationName, timeTakenNano));

        clearContextIfRequired(clearContextAfter);

        statsRepository.save(new Stats()
                .operationName(operationName)
                .timeTakenNanos(timeTakenNano)
                .operationUuid(operationUuid)
                .tag(tag));

        return result;
    }

    private Boolean clearContext() {
        Boolean clearContext = TrackerContext.clearContext();
        if (clearContext) {
            TrackerContext.setClearContext(false);
        }
        return clearContext;
    }

    private void clearContextIfRequired(boolean clearContextAfter) {
        if (clearContextAfter) {
            TrackerContext.clear();
        }
    }

    private UUID operationUuid() {
        UUID operationUuid = TrackerContext.operationId();
        if (operationUuid == null) {
            operationUuid = UUID.randomUUID();
            TrackerContext.setOperationId(operationUuid);
        }
        return operationUuid;
    }

}
