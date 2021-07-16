package com.gulyaich.transformationparser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gulyaich.transformationparser.model.TransformationResult;
import com.gulyaich.transformationparser.service.TransformationFacade;

@RestController
@RequestMapping("/transform")
public class TransformationController {

    private final TransformationFacade transformationFacade;

    public TransformationController(final TransformationFacade transformationFacade) {
        this.transformationFacade = transformationFacade;
    }

    @PostMapping("/{type}/{fileName}")
    public ResponseEntity<TransformationResult> generate(@PathVariable final String type,
                                                         @PathVariable final String fileName) {
        final TransformationResult result = transformationFacade.doTransformation(fileName, type);
        return ResponseEntity.ok(result);
    }
}
