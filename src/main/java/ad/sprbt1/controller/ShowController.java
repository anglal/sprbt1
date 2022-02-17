package ad.sprbt1.controller;

import ad.sprbt1.service.CrvrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
//@RequestMapping("/info-services")
public class ShowController {

    @Autowired
    private CrvrsService crvrsService;

    @GetMapping("/")
    public String show(Model model){
        model.addAttribute("data", this.crvrsService.getCurrentDataList());
        return "show";
    }
}
