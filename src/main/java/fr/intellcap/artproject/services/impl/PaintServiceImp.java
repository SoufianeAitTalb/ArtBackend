package fr.intellcap.artproject.services.impl;

import fr.intellcap.artproject.dto.PaintDTO;
import fr.intellcap.artproject.entities.*;
import fr.intellcap.artproject.repositories.*;
import fr.intellcap.artproject.services.PaintService;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PaintServiceImp implements PaintService {

    private final PaintRepo paintRepo;
    private final CommandRepo commandRepo;
    private final CategoryRepo categoryRepo;
    private final ArtistRepo artistRepo;


    @Autowired
    public PaintServiceImp(PaintRepo paintRepo, CommandRepo commandRepo,CategoryRepo categoryRepo,ArtistRepo artistRepo){
        this.paintRepo = paintRepo;
        this.commandRepo=commandRepo;
        this.categoryRepo=categoryRepo;
        this.artistRepo=artistRepo;

    }

    @Transactional
    @Override
    public Paint addNewPaint(PaintDTO paintDto) {
        Paint paint = new Paint();
        paint.setName(paintDto.getName());
        if(paintDto.getArtistId()!=null){

        Artist artist=artistRepo.findById(paintDto.getArtistId()).orElse(null);
        paint.setArtist(artist);
        }
        paint.setMaterials(paintDto.getMaterials());
        paint.setXDimension(paintDto.getXDimension());
        paint.setYDimension(paintDto.getYDimension());
        paint.setDescPaint(paintDto.getDescPaint());
        paint.setDescArtist(paintDto.getDescArtist());
        Optional<Paint> lastRecord = paintRepo.findTopByOrderByPaintIdDesc();
        if(lastRecord.isPresent())
        paint.setImage((lastRecord.get().getPaintId()+1)+".jpg");
        else paint.setImage("1.jpg");
        paint.setPrice(paintDto.getPrice());
        paint.setQuantity(paintDto.getQuantity());
        paint.setInventoryStatus(paintDto.getInventoryStatus());
        paint.setRating(paintDto.getRating());
        paint.setCategory(paintDto.getCategory());
        return this.paintRepo.save(paint);
    }

    @Transactional 
    @Override
    public Paint updatePaint(Long id, PaintDTO paintDto) {
        Paint paint = this.paintRepo.findById(id).orElse(null);

        if(paint != null){

            paint = this.updateData(paint, paintDto);

            return this.paintRepo.save(paint);
        }
        return null;
    }

    @Override
    public Paint updateData(Paint paint, PaintDTO paintDto) {
        paint.setName(paintDto.getName());
        Artist artist=artistRepo.findById(paintDto.getArtistId()).orElse(null);
        paint.setArtist(artist);
        paint.setMaterials(paintDto.getMaterials());
        paint.setXDimension(paintDto.getXDimension());
        paint.setYDimension(paintDto.getYDimension());
        paint.setDescPaint(paintDto.getDescPaint());
        paint.setDescArtist(paintDto.getDescArtist());
        paint.setImage(paintDto.getImage());
        paint.setPrice(paintDto.getPrice());
        paint.setQuantity(paintDto.getQuantity());
        paint.setInventoryStatus(paintDto.getInventoryStatus());
        paint.setRating(paintDto.getRating());
        paint.setCategory(paintDto.getCategory());
        return paint;
    }

    @Override
    public void deletePaint(Long id) {

//        paintRepo.deleteById(id);
        Paint paint = paintRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));

        List<Command> commands = paint.getCommands();
        for (Command command : commands) {
            commandRepo.deleteById(command.getCommandId());

        }

        paintRepo.delete(paint);
    }

    @Override
    public List<Paint> listPaints() {
        return this.paintRepo.findAll();
    }

    @Override
    public List<PaintDTO> listPaintsDTO() {
        List<Paint> paints = this.paintRepo.findAll();
        List<PaintDTO> paintsDTO = paints.stream()
                .map(this::convertToPaintDTO)
                .collect(Collectors.toList());
        return paintsDTO; // Return the list of PaintDTOs
    }

    public PaintDTO convertToPaintDTO(Paint paint) {
         if(paint == null){
            return null;
        }
        PaintDTO paintDto = new PaintDTO();

         paintDto.setPaintId(paint.getPaintId());
         paintDto.setName(paint.getName());
         if(paint.getArtist()!=null)
        paintDto.setArtistId(paint.getArtist().getUserId());
        paintDto.setMaterials(paint.getMaterials());
        paintDto.setXDimension(paint.getXDimension());
        paintDto.setYDimension(paint.getYDimension());
        paintDto.setDescPaint(paint.getDescPaint());
        paintDto.setDescArtist(paint.getDescArtist());
        paintDto.setImage(paint.getImage());
        paintDto.setPrice(paint.getPrice());
        paintDto.setQuantity(paint.getQuantity());
        paintDto.setInventoryStatus(paint.getInventoryStatus());
        paintDto.setRating(paint.getRating());



        paintDto.setCategory(paint.getCategory());
        return paintDto;
    }


    @Override
    public Paint loadPaintById(Long id) {
        return this.paintRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public PaintDTO loadPaintByPaintId(Long id) {
        Paint paint = this.paintRepo.findById(id).orElse(null);
        if(paint == null){
            return null;
        }
        PaintDTO paintDto = new PaintDTO();

        paintDto.setPaintId(paint.getPaintId());
        paintDto.setName(paint.getName());
        paintDto.setArtistId(paint.getArtist().getUserId());
        paintDto.setMaterials(paint.getMaterials());
        paintDto.setXDimension(paint.getXDimension());
        paintDto.setYDimension(paint.getYDimension());
        paintDto.setDescPaint(paint.getDescPaint());
        paintDto.setDescArtist(paint.getDescArtist());
        paintDto.setImage(paint.getImage());
        paintDto.setPrice(paint.getPrice());
        paintDto.setQuantity(paint.getQuantity());
        paintDto.setInventoryStatus(paint.getInventoryStatus());
        paintDto.setRating(paint.getRating());

        return paintDto;
    }











}
