package poc.operationexecutiontimetracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import poc.operationexecutiontimetracker.domain.Sport;
import poc.operationexecutiontimetracker.domain.SportRepository;
import poc.operationexecutiontimetracker.lambda.LambdaTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SportService {

    final SportRepository sportRepository;
    final LambdaTracker tracker;

    public List<Sport> findAll() {
        return tracker.track("SportService.findAll", () -> {
            List<Sport> sports = new ArrayList<>();

            Optional<Sport> soccer = tracker.track("sportRepository.findById",
                    () -> sportRepository.findById(1L));

            Optional<Sport> basketball = tracker.track("sportRepository.findById",
                    () -> sportRepository.findById(2L));

            Sport golf = tracker.track("sportRepository.findByName",
                    () -> sportRepository.findByName("Golf"));

            Sport formula1 = tracker.track("sportRepository.findByName",
                    () -> sportRepository.findByName("Formula 1"));

            sports.add(golf);
            sports.add(formula1);
            soccer.ifPresent(sports::add);
            basketball.ifPresent(sports::add);

            return sports;
        });
    }

}
