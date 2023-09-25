package poc.operationexecutiontimetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
class OperationExecutionTimeTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationExecutionTimeTrackerApplication.class, args);
    }

}
