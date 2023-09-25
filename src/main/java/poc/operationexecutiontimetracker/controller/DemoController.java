package poc.operationexecutiontimetracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.operationexecutiontimetracker.domain.Sport;
import poc.operationexecutiontimetracker.lambda.LambdaTracker;
import poc.operationexecutiontimetracker.service.SportService;
import poc.operationexecutiontimetracker.util.TrackerContext;

import java.util.List;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
class DemoController {

    final SportService sportService;
    final LambdaTracker tracker;

    @GetMapping("/lambdaTracker")
    // todo: annotation replacement?
    List<Sport> lambdaTracker() {
//        TrackerContext.setTag("sport_without_cache");
        TrackerContext.setTag("cached_sport");



        return tracker.track("DemoController.demo", sportService::findAll);
    }

}
