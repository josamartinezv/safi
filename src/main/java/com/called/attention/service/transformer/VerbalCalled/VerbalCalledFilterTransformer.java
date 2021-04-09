package com.called.attention.service.transformer.VerbalCalled;
import com.called.attention.domain.VerbalCalled;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledFilterDocumentDTO;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledFiltersDTO;


public class VerbalCalledFilterTransformer {
    public static VerbalCalledFilterDocumentDTO getVerbalByDocumentDTO(VerbalCalled verbalCalled) {
        if (verbalCalled == null) {
            return null;
        }

        VerbalCalledFilterDocumentDTO dto = new VerbalCalledFilterDocumentDTO();
        dto.setIdVerbalCalled(verbalCalled.getIdVerbalCalled());
        dto.setDateCalled(verbalCalled.getDateCalled());
        dto.setTypeVerbalCalled(verbalCalled.getTypeVerbalCalled());
        dto.setPhaseVerbalCalled(verbalCalled.getPhaseVerbalCalled());
        dto.setState(verbalCalled.getState());
        dto.setProcedure(verbalCalled.getProcedure());
        dto.setSituation(verbalCalled.getSituation());
        dto.setSummary(verbalCalled.getSummary());
        dto.setImpact(verbalCalled.getImpact());
        dto.setEffect(verbalCalled.getEffect());
        dto.setCommitment(verbalCalled.getCommitment());
        dto.setPhaseVerbalCalled(verbalCalled.getPhaseVerbalCalled());
        dto.setRecovery(verbalCalled.getRecovery());
        dto.setProgramNumber(verbalCalled.getProgramNumber());
        dto.setInstructor(verbalCalled.getInstructor());
        dto.setAprentice(verbalCalled.getAprentice());

        return dto;
    }

    public static VerbalCalledFiltersDTO getVerbalCalledByFiltersDTO(VerbalCalled verbalCalled) {
        if (verbalCalled == null) {
            return null;
        }

        VerbalCalledFiltersDTO dto = new VerbalCalledFiltersDTO();
        dto.setIdVerbalCalled(verbalCalled.getIdVerbalCalled());
        dto.setDateCalled(verbalCalled.getDateCalled());
        dto.setTypeVerbalCalled(verbalCalled.getTypeVerbalCalled());
        dto.setPhaseVerbalCalled(verbalCalled.getPhaseVerbalCalled());
        dto.setState(verbalCalled.getState());
        dto.setProcedure(verbalCalled.getProcedure());
        dto.setSituation(verbalCalled.getSituation());
        dto.setSummary(verbalCalled.getSummary());
        dto.setImpact(verbalCalled.getImpact());
        dto.setEffect(verbalCalled.getEffect());
        dto.setCommitment(verbalCalled.getCommitment());
        dto.setPhaseVerbalCalled(verbalCalled.getPhaseVerbalCalled());
        dto.setRecovery(verbalCalled.getRecovery());
        dto.setProgramNumber(verbalCalled.getProgramNumber());
        dto.setInstructor(verbalCalled.getInstructor());
        dto.setAprentice(verbalCalled.getAprentice());

        return dto;

    }



}
