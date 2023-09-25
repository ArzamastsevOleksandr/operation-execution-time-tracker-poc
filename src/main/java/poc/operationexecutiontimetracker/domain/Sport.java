package poc.operationexecutiontimetracker.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "sport")
@Getter
@Setter
//@Accessors(fluent = true)
public class Sport {

    @Id
    private Long id;

    private String name;
    private SportType type;
    @CreatedDate
    private LocalDateTime createdAt;

}
