package fr.intellcap.artproject.controller;

import fr.intellcap.artproject.dto.ArtistDTO;
import fr.intellcap.artproject.entities.Artist;
import fr.intellcap.artproject.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("**")
@RestController
@RequestMapping("api/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }

    @GetMapping("/")
    public List<Artist> getAllArtists(){
        return this.artistService.listArtists();
    }
    @GetMapping("/dto")
    public List<ArtistDTO> getAllArtistsDTO(){
        return this.artistService.listArtistsDTO();
    }

    @GetMapping("/{id}")
    public ArtistDTO getArtistById(@PathVariable Long id){
        return this.artistService.loadArtistByArtistId(id);
    }

   
    @PostMapping("/add-artist")
    public ResponseEntity<Map<String, String>> addNewArtist(@RequestBody ArtistDTO artistDto) {
    Map<String, String> response = new HashMap<>();

    if (this.artistService.loadArtistByEmail(artistDto.getEmail()) != null) {
        response.put("status", "error");
        response.put("message", "Cet email est déjà utilisé");
        return ResponseEntity.badRequest().body(response);
    }

    if (this.artistService.addNewArtist(artistDto) != null) {
        response.put("status", "success");
        response.put("message", "L'artist a été ajouté avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de l'ajout d'artist");
    return ResponseEntity.badRequest().body(response);
}


   
    @PostMapping("/update-artist/{id}")
    
    public ResponseEntity<Map<String, String>> updateArtist(@PathVariable Long id, @RequestBody ArtistDTO artistDto) {
    Map<String, String> response = new HashMap<>();


    if(this.artistService.updateArtist(id, artistDto) != null ) {
        response.put("status", "success");
        response.put("message", "L'artist a été modifié avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de la modification d'artist");
    return ResponseEntity.badRequest().body(response);
}

    @DeleteMapping("/delete-artist/{id}")
    public ResponseEntity<Map<String, String>> deleteArtist(@PathVariable Long id){
        this.artistService.deleteArtist(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "L'artist a été supprimé avec succès");
        return ResponseEntity.ok(response);

    }
    

}
