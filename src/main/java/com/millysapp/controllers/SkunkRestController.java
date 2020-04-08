package com.millysapp.controllers;

import com.millysapp.services.SkunkService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class SkunkRestController {

    private SkunkService skunkService;

    @Autowired
    public SkunkRestController(SkunkService skunkService) {
        this.skunkService = skunkService;
    }

    @GetMapping(value = "/skunks/dataSources/{skunkId}")
    public ResponseEntity<List<String>> getDataSources(@PathVariable("skunkId")UUID skunkId) {

        //search for skunkId in both databases
        List<String> dataSources = skunkService.findDatasourcesBySkunkId(skunkId);

        return new ResponseEntity(dataSources, HttpStatus.OK);

    }
    @PostMapping(value = "/skunks/dataSources/{skunkId}")
    public ResponseEntity<String> deleteFromDataSources(@PathVariable("skunkId")UUID skunkId, @RequestParam("dataSources[]")String[] dataSources) throws NotFoundException {

        //delete from each datasource
        skunkService.deleteSkunkAtDatasources(skunkId, dataSources);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
