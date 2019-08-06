package com.ditrasystems.comspringboot.Famille;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FamilleController  {

  @Autowired
  FamilleService familleService;

  @PostMapping("/famille")
  public ResponseEntity<?> create(@RequestParam String name){
    return familleService.create(name);
  }

  @PutMapping("/famille/{id}")
  public ResponseEntity<?> edit(@PathVariable long id,@RequestParam String name){
    return familleService.edit(id,name);
  }

  @DeleteMapping("/famille/{id}")
  public ResponseEntity<?> delete(@PathVariable long id) {
    return familleService.delete(id);
  }

  @GetMapping("/famille/{id}")
  public ResponseEntity<?> getById(@PathVariable long id){
    return familleService.getById(id);
  }

  @GetMapping("/familles")
  public ResponseEntity<?> getAll(){
    return familleService.getAll();
  }

  @GetMapping("/famille/{name}")
  public ResponseEntity<?> getByCode(@PathVariable String name){
    return familleService.getByCode(name);
  }

  }
