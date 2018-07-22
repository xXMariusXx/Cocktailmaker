package cocktailServer;

import cocktailServer.model.Rezept;
import cocktailServer.model.FindRezeptRequest;
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
        return Controller.gibRezeptFuerId(id);
    }

    @PostMapping(value = "/find", produces = "application/json")
    public @ResponseBody
    List<Rezept> findRezepte(@RequestBody FindRezeptRequest obj) {
        return Controller.gibRezepteNamenIDfuerZutaten(obj.getSearchType(), obj.getZutaten());
    }
}