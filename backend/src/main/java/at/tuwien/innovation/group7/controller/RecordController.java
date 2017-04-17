package at.tuwien.innovation.group7.controller;

import at.tuwien.innovation.group7.model.Record;
import at.tuwien.innovation.group7.repository.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/record")
@CrossOrigin
public class RecordController {

    private final Cache cache;

    @Autowired
    public RecordController(Cache cache) {
        this.cache = cache;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<Record> getAllIdentifiers() {
        return cache.getRecords();
    }
}
