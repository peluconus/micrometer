package com.practica.micrometer.actuators;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
@RestControllerEndpoint(id = "ejemplo")
public class EjemploActuator {
	
	private Counter counter;
	
	public EjemploActuator(MeterRegistry registry) {
		this.counter = Counter.builder("invocaciones.custom").
				description("Invocaciones Custom Endpoint").
				register(registry);
	}
	
	@GetMapping("/")
	public ResponseEntity<String> getEjemplo(){
		counter.increment();
		return new ResponseEntity<> ("Ejemplo de un custom endpoint", HttpStatus.OK);
	}
}
