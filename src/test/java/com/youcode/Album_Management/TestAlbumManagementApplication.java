package com.youcode.Album_Management;

import org.springframework.boot.SpringApplication;

public class TestAlbumManagementApplication {

	public static void main(String[] args) {
		SpringApplication.from(AlbumManagementApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
