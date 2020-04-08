package com.millysapp.controllers;

import com.millysapp.dtos.SkunkDto;
import com.millysapp.enums.DatabaseEnum;
import com.millysapp.exceptions.SkunkNotFoundException;
import com.millysapp.services.SkunkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {

    private final SkunkService skunkService;

    @Autowired
    public HomeController(SkunkService skunkService) {
        this.skunkService = skunkService;
    }

    @GetMapping(value = "/")
    public ModelAndView welcomePage() {
        var mav = new ModelAndView();

        mav.setViewName("welcome");
        return mav;
    }

    @GetMapping(value = "/index")
    public ModelAndView goHome(
            @RequestParam(
                    required = false,
                    value = "database",
                    defaultValue = "Postgres")String databaseName) {
        var mav = new ModelAndView();

        List<SkunkDto> skunkList = skunkService.findAll(databaseName);

        mav.addObject("datasource", databaseName);
        mav.addObject("skunks", skunkList);
        mav.setViewName("index");
        return mav;
    }

    @GetMapping(value = "/skunks")
    public ModelAndView addSkunk() {
        var mav = new ModelAndView();

        mav.addObject("action", "Save");
        mav.addObject("skunkDto", new SkunkDto());
        mav.setViewName("AddNewSkunk");
        return mav;
    }

    @PostMapping(value = "/skunks")
    public ModelAndView saveSkunk(
            @Valid
            @ModelAttribute("skunkDto") SkunkDto skunkDto, BindingResult bindingResult) {
        var mav = new ModelAndView();

        if(bindingResult.hasErrors()){
            mav.addObject("action", "Save");
            mav.addObject("skunkDto", skunkDto);
            mav.setViewName("AddNewSkunk");
            return mav;
        }

        skunkService.save(skunkDto);
        mav.setViewName("redirect:/index");
        return mav;
    }

    @GetMapping(value = "/skunks/{datasource}/{id}/")
    public ModelAndView editSkunk(
            @PathVariable("datasource") String datasource,
            @PathVariable("id") UUID skunkId) throws SkunkNotFoundException {
        var mav =  new ModelAndView();

        SkunkDto skunkDto = skunkService.findSkunkDtoById(skunkId, datasource);

        mav.addObject("skunkDto", skunkDto);
        mav.addObject("datasource", DatabaseEnum.getByName(datasource));
        mav.addObject("action", "Edit");
        mav.setViewName("AddNewSkunk");
        return mav;
    }

    @PostMapping(value = "/skunks/{id}/edit")
    public ModelAndView saveEditedSkunk(
            @PathVariable("id") UUID skunkId,
            @Valid
            @ModelAttribute("skunkDto") SkunkDto skunkDto, BindingResult bindingResult) {
        var mav = new ModelAndView();

        skunkDto.setSkunkId(skunkId);

        if(bindingResult.hasErrors()){
            mav.addObject("action", "Edit");
            mav.addObject("datasource", skunkDto.getChosenDatabase());
            mav.addObject("skunkDto", skunkDto);
            mav.setViewName("AddNewSkunk");
            return mav;
        }

        skunkService.edit(skunkDto);
        mav.setViewName("redirect:/index");
        return mav;

    }

    @GetMapping(value = "/skunks/{datasource}/{id}/delete")
    public ModelAndView deleteSkunk(
            @PathVariable("datasource") String datasource,
            @PathVariable("id") UUID skunkId) {

        var mav = new ModelAndView();
        //soft delete skunk
        System.out.println(datasource + "  " + skunkId);
        mav.setViewName("redirect:/index");
        return mav;
    }
}
