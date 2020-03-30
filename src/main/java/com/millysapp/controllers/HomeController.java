package com.millysapp.controllers;

import com.millysapp.model.Skunk;
import com.millysapp.services.SkunkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "/getSkunks")
    @ResponseBody
    public ResponseEntity<List<Skunk>> getSkunks() {

        List<Skunk> skunkList = skunkService.findAll();
        return new ResponseEntity<List<Skunk>>(skunkList, HttpStatus.OK);
    }

    @GetMapping(value = "/index")
    public ModelAndView goHome() {
        var mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}
