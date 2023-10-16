package fr.intellcap.artproject.services.impl;

import fr.intellcap.artproject.dto.ArtistDTO;
import fr.intellcap.artproject.entities.Artist;
import fr.intellcap.artproject.entities.Paint;
import fr.intellcap.artproject.repositories.ArtistRepo;

import fr.intellcap.artproject.repositories.PaintRepo;
import fr.intellcap.artproject.services.ArtistService;
import javax.persistence.*;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArtistServiceImp implements ArtistService {

    private final ArtistRepo artistRepo;
    private final PaintRepo paintRepo;


    @Autowired
    public ArtistServiceImp(ArtistRepo artistRepo, PaintRepo paintRepo){
        this.artistRepo = artistRepo;
        this.paintRepo=paintRepo;

    }

    @Transactional
    @Override
    public Artist addNewArtist(ArtistDTO artistDto) {
        Artist artist = new Artist();

        artist.setEmail(artistDto.getEmail());
        artist.setPassword(artistDto.getPassword());
        artist.setAge(artistDto.getAge());
        artist.setPaintingStyle(artistDto.getPaintingStyle());
        artist.setFirstName(artistDto.getFirstName());
        artist.setLastName(artistDto.getLastName());
        artist.setCin(artistDto.getCin());


        return this.artistRepo.save(artist);
    }

    @Transactional 
    @Override
    public Artist updateArtist(Long id, ArtistDTO artistDto) {
        Artist artist = this.artistRepo.findById(id).orElse(null);

        if(artist != null){

            artist = this.updateData(artist, artistDto);

            return this.artistRepo.save(artist);
        }
        return null;
    }

    @Override
    public Artist updateData(Artist artist, ArtistDTO artistDto) {
        artist.setEmail(artistDto.getEmail());
        artist.setPassword(artistDto.getPassword());
        artist.setAge(artistDto.getAge());
        artist.setPaintingStyle(artistDto.getPaintingStyle());
        artist.setFirstName(artistDto.getFirstName());
        artist.setLastName(artistDto.getLastName());
        artist.setCin(artistDto.getCin());

        return artist;
    }

    @Override
    public void deleteArtist(Long id) {

//        artistRepo.deleteById(id);
        Artist artist = artistRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));



        List<Paint> paints = artist.getPaints();
        for (Paint paint : paints) {
            paintRepo.deleteById(paint.getPaintId());

        }

        artistRepo.delete(artist);
    }

    @Override
    public List<Artist> listArtists() {
        return this.artistRepo.findAll();
    }

    @Override
    public List<ArtistDTO> listArtistsDTO() {
        List<Artist> artists = this.artistRepo.findAll();
        List<ArtistDTO> artistsDTO = artists.stream()
                .map(this::convertToArtistDTO)
                .collect(Collectors.toList());
        return artistsDTO; // Return the list of ArtistDTOs
    }

    public ArtistDTO convertToArtistDTO(Artist artist) {
         if(artist == null){
            return null;
        }
        ArtistDTO artistDto = new ArtistDTO();
         artistDto.setArtistId(artist.getUserId());
        artistDto.setEmail(artist.getEmail());
        artistDto.setPassword(artist.getPassword());
        artistDto.setAge(artist.getAge());
        artistDto.setPaintingStyle(artist.getPaintingStyle());
        artistDto.setFirstName(artist.getFirstName());
        artistDto.setLastName(artist.getLastName());
        artistDto.setCin(artist.getCin());
        return artistDto;
    }

    @Override
    public Artist loadArtistById(Long id) {
        return this.artistRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public ArtistDTO loadArtistByArtistId(Long id) {
        Artist artist = this.artistRepo.findById(id).orElse(null);
        if(artist == null){
            return null;
        }
        ArtistDTO artistDto = new ArtistDTO();


        artistDto.setEmail(artist.getEmail());
        artistDto.setPassword(artist.getPassword());
        artistDto.setAge(artist.getAge());
        artistDto.setPaintingStyle(artist.getPaintingStyle());
        artistDto.setFirstName(artist.getFirstName());
        artistDto.setLastName(artist.getLastName());
        artistDto.setCin(artist.getCin());

        return artistDto;
    }


    @Override
    public Artist loadArtistByEmail(String email) {
        return this.artistRepo
                .findByEmail(email)
                .orElse(null);
    }









}
