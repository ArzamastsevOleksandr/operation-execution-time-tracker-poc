package poc.operationexecutiontimetracker.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "stats")
@Getter
@Setter
@Accessors(fluent = true)
public class Stats {

    @Id
    private Long id;

    private String operationName;
    private Long timeTakenNanos;
    private UUID operationUuid;
    private String tag;
    @CreatedDate
    private LocalDateTime createdAt;

}
