package com.youcode.Album_Management.service.Interface;

import com.youcode.Album_Management.dto.request.AlbumRequestDTO;
import com.youcode.Album_Management.dto.response.AlbumResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumService {
    Page<AlbumResponseDTO> getAllAlbums(Pageable pageable);
    Page<AlbumResponseDTO> searchAlbumsByTitle(String title, Pageable pageable);
    Page<AlbumResponseDTO> searchAlbumsByArtist(String artist, Pageable pageable);
    Page<AlbumResponseDTO> filterAlbumsByYear(Integer year, Pageable pageable);
    AlbumResponseDTO createAlbum(AlbumRequestDTO albumDTO);
    AlbumResponseDTO updateAlbum(String id, AlbumRequestDTO albumDTO);
    void deleteAlbum(String id);
}
