package poc.operationexecutiontimetracker.lambda;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
class LambdaDemo implements CommandLineRunner {

    final LambdaTracker tracker;
    final LambdaService lambdaService;
    final OkHttpClient httpClient;

    @Value("${server.port}")
    int serverPort;

    @Override
    public void run(String... args) {
        Request request = new Request.Builder()
                .url("http://localhost:%s/demo/lambdaTracker".formatted(serverPort))
                .get()
                .build();

        Runnable executeCall = () -> executeCall(request);

        for (int i = 1; i <= 300; ++i) {
            List<Thread> threads = IntStream.rangeClosed(1, 10)
                    .mapToObj(j -> new Thread(executeCall))
                    .toList();

            threads.forEach(Thread::start);
            threads.forEach(this::join);

            System.out.println("Requests made: " + i * 10);
        }
//        lambdaService.outerOperation();
//        lambdaService.outerOperation();
    }

    private void join(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeCall(Request request) {
        try {
            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.err.println("WHAT TA HELL");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
