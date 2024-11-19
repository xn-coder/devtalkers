package com.xncoder.devtalker.Services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xncoder.devtalker.DTO.TagDTO;
import com.xncoder.devtalker.Models.Tag;
import com.xncoder.devtalker.Repositories.TagRepo;

@Service
public class TagService {
	
	@Autowired
	private TagRepo tr;
	
	public HashMap<String, String> saveTag(Tag tag) throws SQLIntegrityConstraintViolationException {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", tag.getName());
		data.put("description", tag.getDescription());
		tr.save(tag);
		return data;
	}
	
	public ArrayList<TagDTO> getTags() {
		ArrayList<TagDTO> data = new ArrayList<>();
		for(Tag tag: tr.findAll()) {
			data.add(new TagDTO(tag.getName(), tag.getDescription(), tag.getDate().toString()));
		}
		return data;
	}
	
	public ArrayList<String> getTagName() {
		ArrayList<String> data = new ArrayList<String>();
		for(Tag tag: tr.findAll()) {
			data.add(tag.getName());
		}
		return data;
	}
}
