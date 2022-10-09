package kopo.poly.controller;

import kopo.poly.dto.CorpDTO;
import kopo.poly.service.IMemberService;
import kopo.poly.service.impl.CorpService;
import lombok.extern.slf4j.Slf4j;
import org.bson.json.JsonObject;
import org.json.JSONException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller
@Slf4j
public class CorpController {

    @Resource(name = "CorpService")
    CorpService corpService;



    @ResponseBody
    @GetMapping("/insertCorp")
    public String insertCorp() {


        log.info(this.getClass().getName() + "CorpController :start");

        corpService.insertCorp();


        log.info(this.getClass().getName() + "CorpController :end");
        return "ok";
    }


    @GetMapping("/getCorp")
    public String getCorp(ModelMap model) throws Exception {


        log.info(this.getClass().getName() + "CorpController :start");

       ArrayList<CorpDTO> rlist =  corpService.getCorp() ;
       
       Iterator<CorpDTO> it = rlist.iterator();

       while (it.hasNext()){
           System.out.println("it.next().getCorpName() = " + it.next().getCorpName());
       }
        model.addAttribute("result",rlist);

        log.info(this.getClass().getName() + "CorpController :end");
        return "/corp/corpList";
    }


    @GetMapping("/getFilterCorp")
    public String getFilterCorp(ModelMap model) throws Exception {


        log.info(this.getClass().getName() + "CorpController :start");

        ArrayList<CorpDTO> rlist =  corpService.getCorp() ;

        Iterator<CorpDTO> it = rlist.iterator();

        while (it.hasNext()){
            System.out.println("it.next().getCorpName() = " + it.next().getCorpName());
        }

        ArrayList<CorpDTO> filterList = new ArrayList<>();

        rlist.stream()
                        .filter(p -> p.getAmount() > 1)
                                .forEach( p -> {
                                    filterList.add(p);
                                });


        model.addAttribute("result", filterList);

        log.info(this.getClass().getName() + "CorpController :end");
        return "/corp/corpList";
    }

}
