package com.examen02.clientesMongodb.model;

import java.time.LocalDate;
import java.util.Date;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Cliente")
public class Cliente {
	@Id
	private int cliente_id;
	
	private String nombre_usuario;
	private String contrasena;
	private String nombre;
	private String apellidos;
	private String correo_electronico;
	private int edad;
	private double estatura;
	private double peso;
	private double imc;
	private double geb;
	private double eta;
	private LocalDate fecha_creacion;
	private LocalDate fecha_actualizacion;
	
	public int getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getEstatura() {
		return estatura;
	}
	public void setEstatura(double estatura) {
		this.estatura = estatura;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getImc() {
		return imc;
	}
	public void setImc(double imc) {
		this.imc = imc;
	}
	public double getGeb() {
		return geb;
	}
	public void setGeb(double geb) {
		this.geb = geb;
	}
	public double getEta() {
		return eta;
	}
	public void setEta(double eta) {
		this.eta = eta;
	}
	public LocalDate getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(LocalDate fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public LocalDate getFecha_actualizacion() {
		return fecha_actualizacion;
	}
	public void setFecha_actualizacion(LocalDate fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	
}
