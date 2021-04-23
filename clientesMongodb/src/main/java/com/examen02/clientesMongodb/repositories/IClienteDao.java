package com.examen02.clientesMongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.examen02.clientesMongodb.model.Cliente;

@Repository
public interface IClienteDao extends MongoRepository<Cliente, Integer>{

}
