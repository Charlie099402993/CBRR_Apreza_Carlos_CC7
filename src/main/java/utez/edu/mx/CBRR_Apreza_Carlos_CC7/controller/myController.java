package utez.edu.mx.CBRR_Apreza_Carlos_CC7.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.controller.dto.CrearPedido;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.model.Pedido;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.model.Producto;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.service.myService;
@RestController
@RequestMapping("/controlador")
@CrossOrigin("*")
public class myController {
    @Autowired
    myService myService;

    @PostMapping("/crearPedido")
    public ResponseEntity<Pedido> crearPedido(@Valid @RequestBody CrearPedido payload){
        myService.crearPedido(payload);
        return new ResponseEntity<>(myService.crearPedido(payload), HttpStatus.OK);
    }
    @PatchMapping("/cancelarPedido{id}")
    public ResponseEntity<Pedido>cancelarPedido(@PathVariable @Valid CrearPedido payload){
       myService.cancelarPedido(payload);
       return ResponseEntity.status(200).build();
    }

    @PatchMapping("/procesarPedido{id}")
     public ResponseEntity<Pedido> procesarPedido(@PathVariable @Valid CrearPedido payload){
        myService.procesarPedido(payload);
        return ResponseEntity.status(200).build();
    }
    @PatchMapping("/desactivarProducto")
    public  ResponseEntity <Producto> desactivarProducto(@PathVariable @Valid Long id){
        myService.desactivarProducto(id);
    return ResponseEntity.status(200).build();
    }




}
