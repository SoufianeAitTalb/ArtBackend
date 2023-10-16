package fr.intellcap.artproject.services;



import fr.intellcap.artproject.dto.ArtistDTO;
import fr.intellcap.artproject.entities.Artist;


import java.util.List;

public interface ArtistService {

    Artist addNewArtist(ArtistDTO artistDto);
    Artist updateArtist(Long id, ArtistDTO artistDTO);
    Artist updateData(Artist client, ArtistDTO artistDto);
    void deleteArtist(Long id);
    List<ArtistDTO> listArtistsDTO();
    List<Artist> listArtists();
    Artist loadArtistByEmail(String email);
    Artist loadArtistById(Long id);
    ArtistDTO loadArtistByArtistId(Long id);


}
