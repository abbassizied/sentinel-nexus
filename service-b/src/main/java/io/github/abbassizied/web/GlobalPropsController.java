package io.github.abbassizied.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GlobalPropsController {
	@Value("${global.params.p1}")
	private String p1;

	@Value("${global.params.p2}")
	private String p2;

	@GetMapping("/props")
	public Map<String, String> getProperties() {
		return Map.of("p1", p1, "p2", p2);
	}

	@GetMapping("/config-maps")
	public Map<String, String> getConfigMaps() {
		return Map.of("p1", p1, "p2", p2);
	}
}
