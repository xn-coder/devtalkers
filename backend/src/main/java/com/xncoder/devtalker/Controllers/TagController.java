package com.xncoder.devtalker.Controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xncoder.devtalker.Models.Tag;
import com.xncoder.devtalker.Services.TagService;

@RestController
@RequestMapping("/api")
public class TagController {
	
	@Autowired
	private TagService ts;
	
	@PostMapping("/tag/add")
	public ResponseEntity<?> addTag(@RequestBody Tag tag) {
		HashMap<String, String> data = new HashMap<String, String>();
		try {
			data.putAll(ts.saveTag(tag));
			data.put("status", "success");
			data.put("message", "Tag added successfully!");
		} catch (Exception e) {
			data.put("status", "error");
			data.put("message", "Tag name already present!");
		}
		return ResponseEntity.ok(data);
	}
	
	@GetMapping("/tag/get")
	public ResponseEntity<?> getTag() {
		return ResponseEntity.ok(ts.getTags());
	}
	
	@GetMapping("/tag/getname")
	public ResponseEntity<?> getTagName() {
		return ResponseEntity.ok(ts.getTagName());
	}
	

}
