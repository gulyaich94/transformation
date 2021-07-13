package com.gulyaich.transformationparser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gulyaich.transformationparser.service.TransformationFacade;

@RestController
@RequestMapping("/transform")
public class TransformationController {

    private final TransformationFacade transformationFacade;

    public TransformationController(final TransformationFacade transformationFacade) {
        this.transformationFacade = transformationFacade;
    }

    @PostMapping("/{fileName}")
    public ResponseEntity<String> generate(@PathVariable final String fileName) {
        transformationFacade.doTransformation(fileName);
        return ResponseEntity.ok("Done");
    }

    @GetMapping("/echo")
    public ResponseEntity<String> echo() {
        return ResponseEntity.ok("I hear you");
    }
}
