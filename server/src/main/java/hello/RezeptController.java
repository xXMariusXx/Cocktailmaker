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
    List<String> getRezept(@RequestParam(value = "id") int id){
        Controller c = new Controller();
    return c.gibRezeptdetailsDurchId(id);

    }

    @PostMapping("/find")
    public @ResponseBody Object findRezepte(@RequestBody Zutat[] obj){
        Controller c = new Controller();
        return c.gibRezepteNamenIDfuerZutaten(obj);

    }

}
