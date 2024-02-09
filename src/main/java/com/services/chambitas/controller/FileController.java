package com.services.chambitas.controller;
import static com.services.chambitas.constant.FileConstant.FORWARD_SLASH;
import static com.services.chambitas.constant.FileConstant.IMAGES_FOLDER;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = { "/file" })
public class FileController {

	@GetMapping(path = "/image/{folder}/{fileName}", produces = IMAGE_JPEG_VALUE)
	public byte[] getProfileImage(@PathVariable("folder") String username, @PathVariable("fileName") String fileName)
			throws IOException {
		return Files.readAllBytes(Paths.get(IMAGES_FOLDER + username + FORWARD_SLASH + fileName));
	}
	
}
