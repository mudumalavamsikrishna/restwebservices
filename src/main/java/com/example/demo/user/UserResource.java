package com.example.demo.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserRepository service;

	@GetMapping("/users")
	public List<User> retriveAllUsers() {

		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<Optional<User>> retriveUser(@PathVariable int id) {

		Optional<User> user = service.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("id," + id);

		Resource<Optional<User>> resource = new Resource<Optional<User>>(user);

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;

	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {

		Optional<User> user = service.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("id," + id);

		service.deleteById(id);

		return ResponseEntity.ok().build();

	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
