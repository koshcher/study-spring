package rk.gamification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
public class UserStatsController {

    @Autowired
    private GameService gameService;

    @GetMapping("/{userId}")
    public GameStats getUserStats(@PathVariable Long userId) {

        return gameService.retrieveUserStats(userId);
    }
}
