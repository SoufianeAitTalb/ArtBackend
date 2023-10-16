package fr.intellcap.artproject.services;


import fr.intellcap.artproject.dto.PaintDTO;
import fr.intellcap.artproject.entities.Paint;

import java.util.List;

public interface PaintService {

    Paint addNewPaint(PaintDTO paintDto);
    Paint updatePaint(Long id, PaintDTO paintDTO);
    Paint updateData(Paint paint, PaintDTO paintDto);
    void deletePaint(Long id);
    List<PaintDTO> listPaintsDTO();
    List<Paint> listPaints();
    Paint loadPaintById(Long id);
    PaintDTO loadPaintByPaintId(Long id);


}
