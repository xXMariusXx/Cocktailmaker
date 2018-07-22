package getraenkeServer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin("*")
public class ZutatenController {
    @RequestMapping("/zutaten")
    public ArrayList zutaten() {
        return Controller.gibZutatenliste();
    }
}
