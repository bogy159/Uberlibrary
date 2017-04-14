package at.tuwien.innovation.group7.controller;

import at.tuwien.innovation.group7.model.Header;
import at.tuwien.innovation.group7.repository.InMemoryIdentifiersCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/identifier")
public class IdentifierController {

    private final InMemoryIdentifiersCache inMemoryIdentifiersCache;

    @Autowired
    public IdentifierController(InMemoryIdentifiersCache inMemoryIdentifiersCache) {
        this.inMemoryIdentifiersCache = inMemoryIdentifiersCache;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<Header> getAllIdentifiers() {
        return inMemoryIdentifiersCache.getIdentifiers();
    }
}
