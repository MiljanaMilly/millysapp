package com.millysapp.controllers;

import com.millysapp.enums.DatabaseEnum;
import com.millysapp.model.Skunk;
import com.millysapp.services.SkunkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private SkunkService skunkService;


    @GetMapping(value = "/")
    public ModelAndView welcomePage() {
        var mav = new ModelAndView();

        mav.setViewName("welcome");
        return mav;
    }


    @GetMapping(value = "/index")
    public ModelAndView goHome(@RequestParam(required = false, defaultValue = "T(com.millysapp.enums.DatabaseEnum).POSTGRES_DB", value = "database") String databaseName) {
        var mav = new ModelAndView();

        List<Skunk> skunkList = skunkService.findAll(databaseName);
        for(Skunk skunk : skunkList) {
            System.out.println(skunk.getName());
        }

        mav.addObject("skunks", skunkList);
        mav.setViewName("index");
        return mav;
    }

    @GetMapping(value = "/addNewSkunk")
    public ModelAndView editSkunkPage() {
        var mav = new ModelAndView();

        mav.setViewName("AddNewSkunk");
        return mav;
    }

    @GetMapping(value = "/getMSkunks")
    @ResponseBody
    public ResponseEntity<List<Skunk>> getMSkunks() {

        List<Skunk> skunkList = skunkService.findMAll();
        return new ResponseEntity<List<Skunk>>(skunkList, HttpStatus.OK);
    }

    @GetMapping(value = "/getPSkunks")
    @ResponseBody
    public ResponseEntity<List<Skunk>> getPSkunks() {

        List<Skunk> skunkList = skunkService.findPAll();
        return new ResponseEntity<List<Skunk>>(skunkList, HttpStatus.OK);
    }


}
