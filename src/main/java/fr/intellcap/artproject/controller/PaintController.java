package fr.intellcap.artproject.controller;

import fr.intellcap.artproject.dto.PaintDTO;
import fr.intellcap.artproject.entities.Paint;
import fr.intellcap.artproject.repositories.PaintRepo;
import fr.intellcap.artproject.services.PaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("**")
@RestController
@RequestMapping("api/paints")
public class PaintController {

    private final PaintService paintService;
    private final PaintRepo paintRepo;

    @Autowired
    public PaintController(PaintService paintService,PaintRepo paintRepo){
        this.paintService = paintService;
        this.paintRepo=paintRepo;
    }

    @GetMapping("/")
    public List<Paint> getAllPaints(){
        return this.paintService.listPaints();
    }
    @GetMapping("/dto")
    public List<PaintDTO> getAllPaintsDTO(){
        return this.paintService.listPaintsDTO();
    }

    @GetMapping("/{id}")
    public PaintDTO getPaintById(@PathVariable Long id){
        return this.paintService.loadPaintByPaintId(id);
    }

   
    @PostMapping("/add-paint")
    public ResponseEntity<Map<String, String>> addNewPaint(@RequestBody PaintDTO paintDto) {
    Map<String, String> response = new HashMap<>();



    if (this.paintService.addNewPaint(paintDto) != null) {
        response.put("status", "success");
        response.put("message", "La peinture a été ajouté avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de l'ajout de peinture");
    return ResponseEntity.badRequest().body(response);
}


   
    @PostMapping("/update-paint/{id}")
    
    public ResponseEntity<Map<String, String>> updatePaint(@PathVariable Long id, @RequestBody PaintDTO paintDto) {
    Map<String, String> response = new HashMap<>();


    if(this.paintService.updatePaint(id, paintDto) != null ) {
        response.put("status", "success");
        response.put("message", "La peinture a été modifié avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de la modification de peinture");
    return ResponseEntity.badRequest().body(response);
}

    @DeleteMapping("/delete-paint/{id}")
    public ResponseEntity<Map<String, String>> deletePaint(@PathVariable Long id){
        this.paintService.deletePaint(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "La peinture a été supprimé avec succès");
        return ResponseEntity.ok(response);

    }

    @GetMapping(path = "/imagePaint/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws Exception{
        Paint paint = paintRepo.findById(id).get();
        String photoName = paint.getImage();
        File file = new File(System.getProperty("user.home")+"/art/images/"+photoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }


}
