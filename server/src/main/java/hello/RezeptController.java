package hello;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rezept")
@CrossOrigin("*")
public class RezeptController {
    public RezeptController() {
    }

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody Rezept getRezept(@RequestParam(value = "id") int id) {
        Controller c = new Controller();
        return c.gibRezeptFuerId(id);
    }

    @PostMapping(value = "/find", produces = "application/json")
    public @ResponseBody Object findRezepte(@RequestBody Zutat[] obj) {
        Controller c = new Controller();
        return c.gibRezepteNamenIDfuerZutaten(obj);
    }
}