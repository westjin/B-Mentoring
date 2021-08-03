package net.itdaa.cau.study.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoadAddrApiController {

    ResponseEntity<?> entity = null;

    @ApiOperation(value="value", notes="notes")
    @GetMapping(value="/test")
    public ResponseEntity<?> test() {

        entity = new ResponseEntity<String>("Okay", HttpStatus.OK);

        return entity;
    }


}
