package com.practica.micrometer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class PrometheusController {
	
	private Counter contador;
	
	public PrometheusController(MeterRegistry registry) {
		this.contador = Counter.builder("invocaciones.ejemplo").
				description("Invocaciones Ejemplo").
				register(registry);
	}
	
	@GetMapping(path="/ejemplo")
	public String getEjemplo() {
		contador.increment();
		return "Esto es un ejemplo";
	}

}
