package com.called.attention.service.transformer.VerbalCalled;

import com.called.attention.domain.VerbalCalled;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledDTO;

public class VerbalCalledTransformer {
    public static VerbalCalledDTO getVerbalCalledDTOFromVerbalCalled (VerbalCalled verbalCalled) {
        if (verbalCalled == null) {
            return null;
        }
        VerbalCalledDTO dto = new VerbalCalledDTO();

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

    public static VerbalCalled getVerbalCalledFromVerbalCalledDTO (VerbalCalledDTO dto) {
        if (dto == null) {
            return null;
        }
        VerbalCalled verbalCalled = new VerbalCalled();

        verbalCalled.setIdVerbalCalled(dto.getIdVerbalCalled());
        verbalCalled.setDateCalled(dto.getDateCalled());
        verbalCalled.setTypeVerbalCalled(dto.getTypeVerbalCalled());
        verbalCalled.setPhaseVerbalCalled(dto.getPhaseVerbalCalled());
        verbalCalled.setState(dto.getState());
        verbalCalled.setProcedure(dto.getProcedure());
        verbalCalled.setSituation(dto.getSituation());
        verbalCalled.setSummary(dto.getSummary());
        verbalCalled.setImpact(dto.getImpact());
        verbalCalled.setEffect(dto.getEffect());
        verbalCalled.setCommitment(dto.getCommitment());
        verbalCalled.setPhaseVerbalCalled(dto.getPhaseVerbalCalled());
        verbalCalled.setRecovery(dto.getRecovery());
        verbalCalled.setProgramNumber(dto.getProgramNumber());
        verbalCalled.setInstructor(dto.getInstructor());
        verbalCalled.setAprentice(dto.getAprentice());

        return verbalCalled;


    }

}
