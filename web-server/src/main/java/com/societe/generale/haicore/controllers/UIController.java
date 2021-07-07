package com.societe.generale.haicore.controllers;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/")
public class UIController {
    private static final Logger LOG = LoggerFactory.getLogger(UIController.class);

    @GetMapping(value = {"/",}, produces = "text/html")
    public String propertiesPage() throws IOException {
        return IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("webjars/index.html"), "UTF-8");
    }
}
