package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
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
public class KoalaController {

    private Map<Long, Koala> koalas = new HashMap<>();
    private Long nextKoalaId = 1L;

    @GetMapping("/koalas")
    public List<Koala> getKoalas() {
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/koalas/{id}")
    public Koala getKoalaById(@PathVariable Long id) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Belirtilen ID'ye sahip koala bulunamadı: " + id, HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    @PostMapping("/koalas")
    public ResponseEntity<Koala> createKoala(@RequestBody Koala koala) {
        koala.setId(nextKoalaId++);
        this.koalas.put(koala.getId(), koala);
        return new ResponseEntity<>(koala, HttpStatus.CREATED);
    }

    @PutMapping("/koalas/{id}")
    public ResponseEntity<Koala> updateKoala(@PathVariable Long id, @RequestBody Koala updatedKoala) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Belirtilen ID'ye sahip koala bulunamadı: " + id, HttpStatus.NOT_FOUND);
        }
        updatedKoala.setId(id);
        koalas.put(id, updatedKoala);
        return new ResponseEntity<>(updatedKoala, HttpStatus.OK);
    }

    @DeleteMapping("/koalas/{id}")
    public ResponseEntity<Void> deleteKoala(@PathVariable Long id) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Belirtilen ID'ye sahip koala bulunamadı: " + id, HttpStatus.NOT_FOUND);
        }
        koalas.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}