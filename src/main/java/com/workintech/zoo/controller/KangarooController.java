package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class KangarooController {
    private Map<Long, Kangaroo> kangaroos = new HashMap<>();
    private Long nextId = 1L;

    @GetMapping("/kangaroos")
    public List<Kangaroo> getKangaroos() {
        return new ArrayList<>(kangaroos.values());
    }

    @GetMapping("/kangaroos/{id}")
    public Kangaroo getKangarooById(@PathVariable Long id) {
        if(!kangaroos.containsKey(id)) {
            throw new ZooException("ID bulunamadi : " + id, HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping("/kangaroos")
    public ResponseEntity<Kangaroo> createKangaroo(@RequestBody Kangaroo kangaroo){
        kangaroo.setId(nextId++);
        this.kangaroos.put(kangaroo.getId(), kangaroo);
        return new ResponseEntity<>(kangaroo, HttpStatus.CREATED);
    }

    @PutMapping("/kangaroos/{id}")
    public ResponseEntity<Kangaroo> updateKangaroo(@PathVariable Long id, @RequestBody Kangaroo updatedKangroo){
        if(!kangaroos.containsKey(id)) {
            throw new ZooException("Belirtilen id li kanguru bulunamadi : " + id, HttpStatus.NOT_FOUND );
        }
        updatedKangroo.setId(id);
        kangaroos.put(id, updatedKangroo);
        return new ResponseEntity<>(updatedKangroo, HttpStatus.OK);
    }

    @DeleteMapping("/kangaroos/{id}")
    public ResponseEntity<Void> deleteKangaroo(@PathVariable Long id){
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Belirtilen id li kanguru bulunamadi : " + id, HttpStatus.NOT_FOUND );
        }
        kangaroos.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
