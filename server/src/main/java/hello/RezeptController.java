package hello;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rezept")
public class RezeptController {
    public RezeptController() {
    }

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    List<Zutat> getRezept(@RequestParam(value = "id") int id){
        Controller c = new Controller();
    return c.gibRezeptdetailsDurchId(id);

    }

    @PostMapping(value = "/find", produces = "application/json")
    public @ResponseBody Object findRezepte(@RequestBody Zutat[] obj){
        Controller c = new Controller();
        return c.gibRezepteNamenIDfuerZutaten(obj);

    }

}
