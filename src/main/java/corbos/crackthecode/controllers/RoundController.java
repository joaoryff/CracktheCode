package corbos.crackthecode.controllers;

import corbos.crackthecode.data.RoundDao;
import corbos.crackthecode.models.Game;
import corbos.crackthecode.models.Round;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guess")
public class RoundController {

    private final RoundDao dao;


    public RoundController(RoundDao dao) {
        this.dao = dao;
    }

    @PostMapping("/{guess}/{gameId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Round guess(@PathVariable String guess, @PathVariable int gameId) {
//        return dao.makeGuess(guess, gameid);
        String result ="Wrong";
        Round round = new Round(gameId,guess, result);
        return dao.createRound(round);
    }

    @GetMapping("/round/{id}")
    public ResponseEntity<Round> findById(@PathVariable int id) {
        Round result = dao.findById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
}
