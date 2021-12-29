package com.project.springdockerpostgres.controller;

import com.project.springdockerpostgres.kafka.Producer;
import com.project.springdockerpostgres.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.springdockerpostgres.repository.ProductRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
//@RequestMapping("api")
public class ServiceApi implements ErrorController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Producer producer;

    @RequestMapping("/")
    public String testApi(){
        return "HELLO WORLD";
    }

    @RequestMapping("/test")
    public String testAp2(){
        return "HELLO BOSS";
    }

    @PostMapping("/message")
    public ResponseEntity<String> testApi3(@RequestBody String message){
        System.out.println("WOI ini " + message);
        producer.sendMessage(message);
        return ResponseEntity.ok().body("Ini pesan nya : " + message);
    }

    @PostMapping("/addProduct")
    public Product addStudent(@RequestBody Product product){
        return productRepository.save(product);
    }

    @RequestMapping("/product")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @RequestMapping("/produk/{id}")
    public Product getProductById(@PathVariable("id") int id){
        return productRepository.findById(id).get();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById2(@PathVariable(value = "id")Integer id){
        Product product = productRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student not found"+id));
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProductById2(@PathVariable(value = "id")Integer id){
        Product product = productRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student not found"+id));
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}
