package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

  @GetMapping(value = "/whiskies")
  public ResponseEntity<List<Whisky>> getAllWhiskies(
          @RequestParam(name = "year", required = false) Integer year){
      if (year != null){
          return new ResponseEntity<>(whiskyRepository.findWhiskyByYear(year), HttpStatus.OK);
      }
      return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping(value = "/whiskies/distilleries")
    public ResponseEntity<List<Whisky>> getWhiskiesByDistillery(
            @RequestParam(value = "age",required = false) Integer age, @RequestParam(name ="named",required = false) String named){
      if( age != null && named != null){
          return new ResponseEntity<>(whiskyRepository.findWhiskyByAgeAndDistilleryName(age,named), HttpStatus.OK);

      }
      return new ResponseEntity<>(whiskyRepository.findAll(),HttpStatus.OK);
  }

  @GetMapping(value = "/whiskies/regions")
    public ResponseEntity<List<Whisky>> getWhiskiesThatAreFromAParticularRegion(
            @RequestParam(name = "named") String named
  ){
      return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryRegion(named), HttpStatus.OK);
  }






}
