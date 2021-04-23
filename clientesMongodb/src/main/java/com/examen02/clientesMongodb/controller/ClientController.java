package com.examen02.clientesMongodb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.examen02.clientesMongodb.model.Cliente;
import com.examen02.clientesMongodb.repositories.IClienteDao;


@RestController
@RequestMapping("/apiCliente")
public class ClientController {

	@Autowired
	private IClienteDao repository;
	
	
	private boolean existeId(int id) {
		try{
			Cliente c=repository.findById(id).get();
			if(c==null)
				return false;
			return true;
			}catch(Exception e) {
				return false;
			}
		}
		
	
	private  int obtenerNuevoID() {
		List<Cliente> cl =repository.findAll();
		int mayor=0;
		for(Cliente cliente:cl) {
			if(cliente.getCliente_id()>mayor)
				mayor=cliente.getCliente_id();
		}
		return mayor+1;
	}
	 
	private boolean existeNombreUsuario(String nombreUsuario) {
		List<Cliente> cl =repository.findAll();
		for(Cliente cliente:cl) {
			if(cliente.getNombre_usuario().equalsIgnoreCase(nombreUsuario))
				return true;
		}
		return false;
	}
	private boolean existeCorreoElectronico(String correoElectronico) {
		
		List<Cliente> cl =repository.findAll();
		for(Cliente cliente:cl) {
			if(cliente.getCorreo_electronico().equalsIgnoreCase(correoElectronico))
				return true;
		}
		return false;
	}
	
	@PostMapping("/POST/NutriNet/Cliente")
	public ResponseEntity<Map<String,String>> create(@Validated @RequestBody Cliente c) {
		 HashMap<String, String> map = new HashMap<>();
		if(existeCorreoElectronico(c.getCorreo_electronico())) {
			map.put("Cve_error", "-2");
		    map.put("Cve_mensaje","correo electrónico ya existente");
	return	new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
		}else if(existeNombreUsuario(c.getNombre_usuario())) {
			map.put("Cve_error", "-3");
		    map.put("Cve_mensaje","nombre de usuario ya existente");
		    return	new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
		}
		
		c.setCliente_id(obtenerNuevoID());
	    repository.save(c);
	    map.put("Cve_error", "0");
		map.put("Cve_mensaje","Ingresado correctamente");
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
	    
	}
	
	@GetMapping("/GET/NutriNet/Cliente/{id}")
	public ResponseEntity<Map<String,Object>>cargarPorId(@PathVariable String id) {
		HashMap<String, Object> map = new HashMap<>();
		List<Cliente> cl = new ArrayList<>();
		
		
		if(id.equalsIgnoreCase("todos")) {
			cl=repository.findAll();
			 map.put("Cve_error", "0");
				map.put("Cve_mensaje","registros obtenidos correctamente");
				map.put("Registros", cl);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}else 
			try {
			if(existeId(Integer.parseInt(id))) {
				int idd=Integer.parseInt(id);
				cl.add(repository.findById(idd).get());
				 map.put("Cve_error", "0");
				map.put("Cve_mensaje","registro obtenido correctamente");
				map.put("Registro", cl);
				return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
			}
				 map.put("Cve_error", "-1");
					map.put("Cve_mensaje","id no existente, intente con otro");
				return	new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
			}catch(Exception e) {
				 map.put("Cve_error", "-4");
					map.put("Cve_mensaje","formato inválido para id, intente otro");
					return	new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
			}
			
			
		}
	
	
	@PutMapping("/PUT/NutriNet/Cliente/{id}")
	public ResponseEntity<Map<String,String>> editarUsuario(@PathVariable int id,@Validated @RequestBody Cliente c){
		 HashMap<String, String> map = new HashMap<>();
		try {
		 if(existeId(id)) {
		
		Cliente c2=repository.findById(id).get();
		c2.setEstatura(c.getEstatura());
		c2.setPeso(c.getPeso());
		repository.save(c2);
		map.put("Cve_error", "0");
		map.put("Cve_mensaje","Editado correctamente");
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		}
			
			map.put("Cve_error", "-1");
			map.put("Cve_mensaje","id no existente, intente con otro");
		return	new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
			
			
		}catch(Exception e) {
			map.put("Cve_error", "-4");
			map.put("Cve_mensaje","formato inválido para id, intente otro");
			return	new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
}
