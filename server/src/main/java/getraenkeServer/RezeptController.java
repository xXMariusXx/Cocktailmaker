package getraenkeServer;

import getraenkeServer.model.Rezept;
import getraenkeServer.model.findRezeptRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rezept")
@CrossOrigin("*")
public class RezeptController {
    public RezeptController() {
    }

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    Rezept getRezept(@RequestParam(value = "id") int id) {
        Controller c = new Controller();
        return c.gibRezeptFuerId(id);
    }

    @PostMapping(value = "/find", produces = "application/json")
    public @ResponseBody
    List<Rezept> findRezepte(@RequestBody  findRezeptRequest obj) {
        Controller c = new Controller();
        return c.gibRezepteNamenIDfuerZutaten(obj.getSearchType(), obj.getZutaten());
    }
}