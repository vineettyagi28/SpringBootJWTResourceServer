/**
 * 
 */
package com.vineet.resource.rest;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vineet
 *
 */
@RestController
@RequestMapping("/foo")
public class WebController {

	@GetMapping
	public String readFoo() {
		return "read foo " + UUID.randomUUID().toString();
	}

	@PreAuthorize("hasAuthority('FOO_WRITE')")
	@PostMapping
	public String writeFoo() {
		return "write foo " + UUID.randomUUID().toString();
	}
}	