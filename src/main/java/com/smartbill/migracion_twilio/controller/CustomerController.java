package com.smartbill.migracion_twilio.controller;

import com.cloudinary.Cloudinary;
import com.smartbill.migracion_twilio.dto.CustomerDTO;
import com.smartbill.migracion_twilio.model.Customer;
import com.smartbill.migracion_twilio.service.IConsultService;
import com.smartbill.migracion_twilio.service.ICustomerService;
import com.smartbill.migracion_twilio.service.IMediaFileService;
import com.smartbill.migracion_twilio.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/customers") // Ruta base para las solicitudes a este controlador
@RequiredArgsConstructor // Genera un constructor para inyección de dependencias
public class CustomerController {

    // Dependencias inyectadas
    private final ICustomerService customerService;
    private final IConsultService service; // Servicio para consultas de clientes
    private final MapperUtil mapperUtil; // Utilidad para convertir entre entidades y DTOs
    private final IMediaFileService mfService; // Servicio para manejar archivos multimedia
    private final Cloudinary cloudinary; // Servicio de Cloudinary

    @GetMapping // Maneja solicitudes GET en /customers
    public ResponseEntity<List<CustomerDTO>> findAll() {
        // Convierte la lista de entidades a DTOs y la devuelve como respuesta
        List<CustomerDTO> list = mapperUtil.mapList(service.findAll(), CustomerDTO.class);
        return ResponseEntity.ok(list); // Devuelve la lista de clientes
    }

    @GetMapping("/{id}") // Maneja solicitudes GET en /customers/{id}
    public ResponseEntity<CustomerDTO> findById(@PathVariable("id") Integer id) {
        // Busca el cliente por ID y lo convierte a DTO
        Customer obj = service.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, CustomerDTO.class)); // Devuelve el cliente encontrado
    }

    @PostMapping // Maneja solicitudes POST en /customers
    public ResponseEntity<String> save(@Valid @RequestBody CustomerDTO dto) {
        // Imprime el DTO recibido en la consola
        System.out.println("Received CustomerDTO: " + dto);

        // Verifica si el número de documento ya existe
        if (customerService.existsByDocumentNumber(dto.getDocumentNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El documento ya se encuentra registrado!"); // Devuelve 409 Conflict si ya existe
        }

        // Convierte el DTO a entidad y la guarda en la base de datos
        Customer obj = service.save(mapperUtil.map(dto, Customer.class));

        // Crea una URI para el nuevo recurso
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdCustomer())
                .toUri();

        return ResponseEntity.created(location).build(); // Devuelve una respuesta 201 Created
    }


    @PutMapping("/{id}") // Maneja solicitudes PUT en /customers/{id}
    public ResponseEntity<CustomerDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CustomerDTO dto) {
        // Establece el ID en el DTO y actualiza el cliente
        dto.setIdCustomer(id);
        Customer obj = service.update(id, mapperUtil.map(dto, Customer.class));

        return ResponseEntity.ok(mapperUtil.map(obj, CustomerDTO.class)); // Devuelve el cliente actualizado
    }

    @DeleteMapping("/{id}") // Maneja solicitudes DELETE en /customers/{id}
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        // Elimina el cliente por ID
        service.delete(id);
        return ResponseEntity.noContent().build(); // Devuelve una respuesta 204 No Content
    }

    @GetMapping("/hateoas/{id}") // Maneja solicitudes GET en /customers/hateoas/{id}
    public EntityModel<CustomerDTO> findByIdHateoas(@PathVariable("id") Integer id) {
        // Convierte el cliente encontrado a un EntityModel
        EntityModel<CustomerDTO> resource = EntityModel.of(mapperUtil.map(service.findById(id), CustomerDTO.class));

        // Genera un enlace informativo hacia el recurso
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("customer-self-info")); // Agrega el enlace al EntityModel

        return resource; // Devuelve el EntityModel con el enlace
    }
}
