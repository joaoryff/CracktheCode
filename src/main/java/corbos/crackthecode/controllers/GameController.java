package corbos.crackthecode.controllers;

import corbos.crackthecode.data.GameDao;
import corbos.crackthecode.models.Game;
import corbos.crackthecode.service.CrackRules;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GameController {
    private final GameDao dao;
    CrackRules crack = new CrackRules();

    public GameController(GameDao dao) {
        this.dao = dao;
    }

    @GetMapping ("/game")
    public List<Game> all() {
        return dao.getAll();
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> findById(@PathVariable int id) {
       Game result = dao.findById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    // For test, return the object
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Game Begin() {
//        Game game = new Game();
//        return dao.createGame(game);
//    }

    // Create a new Game and display msg
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public String Begin() {
        Game game = new Game();
        dao.createGame(game);
        return crack.BeginMsg(game.getGameId());
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ToDo create(@RequestBody ToDo todo) {
//        return dao.add(todo);
//    }






}
