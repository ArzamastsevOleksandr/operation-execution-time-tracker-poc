package poc.operationexecutiontimetracker.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poc.operationexecutiontimetracker.cfg.OperationGroups;

@Configuration
public class TrackerCfg {

    @Bean
    OperationGroups operationGroups() {
        return new OperationGroups();
//                 .withGroup(new Group())
    }
}
