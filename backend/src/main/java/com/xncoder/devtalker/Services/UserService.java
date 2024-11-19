package com.xncoder.devtalker.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xncoder.devtalker.Models.User;
import com.xncoder.devtalker.Repositories.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo ur;
	
	public HashMap<String, String> getProfile(String email) {
		HashMap<String, String> data = new HashMap<String, String>();
		User user = ur.findByEmail(email);
		if(user != null) {
			data.put("id", ""+user.getId());
			data.put("email", user.getEmail());
			data.put("name", user.getName());
			data.put("date", user.getDate().toString());
			data.put("image", user.getImage());
			data.put("location", user.getLocation());
			data.put("title", user.getTitle());
			data.put("about", user.getAbout());
			data.put("web_link", user.getWeb_link());
			data.put("twi_link", user.getTwi_link());
			data.put("git_link", user.getGit_link());
			data.put("full_name", user.getFull_name());
		}
		return data;
	}
	
	public User updateProfile(String email, User user) {
		User us = ur.findByEmail(email);
		us.setName(user.getName());
		us.setLocation(user.getLocation());
		us.setTitle(user.getTitle());
		us.setAbout(user.getAbout());
		us.setWeb_link(user.getWeb_link());
		us.setTwi_link(user.getTwi_link());
		us.setGit_link(user.getGit_link());
		us.setFull_name(user.getFull_name());
		us.setImage(user.getImage());
		ur.save(us);
		return us;
	}
	
	public List<HashMap<String, String>> getAllProfile() {
		List<HashMap<String, String>> users = new ArrayList<HashMap<String, String>>();
		for(User user: ur.findAll()) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("id", ""+user.getId());
			data.put("name", user.getName());
			data.put("votes", ""+user.getVotedQuestions().size());
			data.put("location", user.getLocation());
			data.put("image", user.getImage());
			users.add(data);
		}
		return users;
	}

	public HashMap<String, String> getProfileById(Long id) {
		HashMap<String, String> data = new HashMap();
		User user = ur.findById(id).orElse(null);
		data.put("name", user.getName());
		data.put("date", user.getDate().toString());
		data.put("image", user.getImage());
		return data;
	}
	
}
