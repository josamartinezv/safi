package com.called.attention.repository;

import com.called.attention.domain.*;

import com.called.attention.domain.enumeration.PhaseVerbalCalled;
import com.called.attention.domain.enumeration.TypeVerbalCalled;
import com.called.attention.domain.enumeration.UsersCourseRol;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerbalCalledRepository extends JpaRepository<VerbalCalled, Integer> {
    //invidivual

    Page<VerbalCalled> findByAprenticeOrderByDateCalledDesc(Users users, Pageable pageable);//organizar la fecha

    Page<VerbalCalled> findByProgramNumberOrderByDateCalledDesc(ProgramNumber programNumber, Pageable pageable);//organizar la fecha

    Page<VerbalCalled> findByStateOrderByDateCalledDesc(Boolean state, Pageable pageable);//organizar la fecha

    Page<VerbalCalled> findByTypeVerbalCalledOrderByDateCalledDesc(TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByPhaseVerbalCalledOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, Pageable pageable);//organizar la fecha

    Page<VerbalCalled> findByInstructorOrderByDateCalledDesc (Users users1, Pageable pageable);//Organizar fecha





    //together
    //aprentice
    Page<VerbalCalled> findByAprenticeAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc (Users users, ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Boolean state,  Pageable pageable);

    Page<VerbalCalled> findByAprenticeAndProgramNumberAndStateOrderByDateCalledDesc(Users users, ProgramNumber programNumber, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByAprenticeAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(Users users, ProgramNumber programNumber, TypeVerbalCalled verbalCalled, Pageable pageable);

    Page<VerbalCalled> findByAprenticeAndTypeVerbalCalledAndStateOrderByDateCalledDesc(Users users, TypeVerbalCalled verbalCalled, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByAprenticeAndProgramNumberOrderByDateCalledDesc(Users users, ProgramNumber programNumber, Pageable pageable);

    Page<VerbalCalled> findByAprenticeAndTypeVerbalCalledOrderByDateCalledDesc(Users users, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByAprenticeAndStateOrderByDateCalledDesc(Users users, Boolean state, Pageable pageable);
    //number
    Page<VerbalCalled> findByProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Boolean state,  Pageable pageable);

    Page<VerbalCalled> findByProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByProgramNumberAndStateOrderByDateCalledDesc(ProgramNumber programNumber, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByTypeVerbalCalledAndStateOrderByDateCalledDesc (TypeVerbalCalled typeVerbalCalled, Boolean state,Pageable pageable);

    //PhaseVerbalCalled
    Page<VerbalCalled> findByPhaseVerbalCalledAndAprenticeAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, Users users, ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Boolean state,  Pageable pageable);

    //PhaseVerbalCalled typeVerbalCalled
    Page<VerbalCalled> findByPhaseVerbalCalledAndAprenticeAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, Users users, ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByPhaseVerbalCalledAndAprenticeAndTypeVerbalCalledOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, Users users, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByPhaseVerbalCalledAndTypeVerbalCalledOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    //PhaseVerbalCalled state
    Page<VerbalCalled> findByPhaseVerbalCalledAndAprenticeAndProgramNumberAndStateOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, Users users, ProgramNumber programNumber, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByPhaseVerbalCalledAndAprenticeAndStateOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, Users users, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByPhaseVerbalCalledAndStateOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, Boolean state, Pageable pageable);

    //PhaseVerbalCalled ProgramNumber
    Page<VerbalCalled> findByPhaseVerbalCalledAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByPhaseVerbalCalledAndProgramNumberAndStateOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, ProgramNumber programNumber, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByPhaseVerbalCalledAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByPhaseVerbalCalledAndAprenticeAndProgramNumberOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, Users users, ProgramNumber programNumber, Pageable pageable);

    Page<VerbalCalled> findByPhaseVerbalCalledAndProgramNumberOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, ProgramNumber programNumber, Pageable pageable);

    //PhaseVerbalCalled Aprentice
    Page<VerbalCalled> findByPhaseVerbalCalledAndAprenticeAndTypeVerbalCalledAndStateOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, Users users, TypeVerbalCalled typeVerbalCalled, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByPhaseVerbalCalledAndAprenticeOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, Users users,Pageable pageable);

    Page<VerbalCalled> findByPhaseVerbalCalledAndTypeVerbalCalledAndStateOrderByDateCalledDesc(PhaseVerbalCalled phaseVerbalCalled, TypeVerbalCalled typeVerbalCalled, Boolean state,  Pageable pageable);

    //document instructor
    Page<VerbalCalled>findByInstructorAndPhaseVerbalCalledAndAprenticeAndProgramNumberAndStateAndTypeVerbalCalledOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, Users users, ProgramNumber programNumber,   Boolean state, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndAprenticeAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, Users users, ProgramNumber programNumber,  TypeVerbalCalled typeVerbalCalled,  Boolean state, Pageable pageable);

    //document instructor typeVerbalCalled
    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndAprenticeAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, Users users, ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndAprenticeAndTypeVerbalCalledOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, Users users, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndTypeVerbalCalledOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndTypeVerbalCalledOrderByDateCalledDesc(Users users1, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    //document instructor state
    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndAprenticeAndProgramNumberAndStateOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, Users users, ProgramNumber programNumber, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndAprenticeAndStateOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, Users users, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndStateOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, Boolean state,  Pageable pageable);

    Page<VerbalCalled> findByInstructorAndStateOrderByDateCalledDesc(Users users1, Boolean state,  Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndTypeVerbalCalledAndStateOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, TypeVerbalCalled typeVerbalCalled,Boolean state, Pageable pageable);


    //document instructor ProgramNumber
    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, ProgramNumber programNumber,  TypeVerbalCalled typeVerbalCalled, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndProgramNumberAndStateOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, ProgramNumber programNumber, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndProgramNumberOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, ProgramNumber programNumber, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndProgramNumberOrderByDateCalledDesc(Users users1, ProgramNumber programNumber, Pageable pageable);

    //document instructor Aprentice

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndAprenticeAndTypeVerbalCalledAndStateOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, Users users,TypeVerbalCalled typeVerbalCalled, Boolean state,  Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndAprenticeAndProgramNumberOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled, Users users, ProgramNumber programNumber, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledAndAprenticeOrderByDateCalledDesc(Users users1,  PhaseVerbalCalled phaseVerbalCalled, Users users, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndAprenticeOrderByDateCalledDesc(Users users1, Users users,  Pageable pageable);

    //document instructor phaseVerbalCalled
    Page<VerbalCalled> findByInstructorAndAprenticeAndTypeVerbalCalledAndStateOrderByDateCalledDesc(Users users1, Users users,  TypeVerbalCalled typeVerbalCalled, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndPhaseVerbalCalledOrderByDateCalledDesc(Users users1, PhaseVerbalCalled phaseVerbalCalled,  Pageable pageable);

    Page<VerbalCalled> findByInstructorAndAprenticeAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(Users users1, Users users, ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Boolean state,  Pageable pageable);

    Page<VerbalCalled> findByInstructorAndAprenticeAndProgramNumberAndStateOrderByDateCalledDesc(Users users1, Users users, ProgramNumber programNumber, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndAprenticeAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(Users users1, Users users, ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndAprenticeAndTypeVerbalCalledOrderByDateCalledDesc(Users users1, Users users, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndAprenticeAndStateOrderByDateCalledDesc(Users users1, Users users, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndAprenticeAndProgramNumberOrderByDateCalledDesc(Users users1, Users users, ProgramNumber programNumber, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(Users users1,  ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Boolean state,  Pageable pageable);

    Page<VerbalCalled> findByInstructorAndProgramNumberAndStateOrderByDateCalledDesc(Users users1, ProgramNumber programNumber, Boolean state, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(Users users1,  ProgramNumber programNumber, TypeVerbalCalled typeVerbalCalled, Pageable pageable);

    Page<VerbalCalled> findByInstructorAndTypeVerbalCalledAndStateOrderByDateCalledDesc(Users users1,  TypeVerbalCalled typeVerbalCalled, Boolean state, Pageable pageable);














}
