package com.ems.service;

import com.ems.entity.Manager;
import com.ems.exception.DuplicateEmailException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    /**
     * Add a new Manager to DB
     * @param manager object that will be added
     * @return Manager object which is added.
     */

    public Manager addManager(Manager manager){
        log.info("Starting to add Manager");
        try{
            return managerRepository.save(manager);
        } catch (DataIntegrityViolationException e){
            log.error("Error in Adding Manager");
            throw new DuplicateEmailException("Email already exist: " + manager.getEmail());
        }

    }

    /**
     * Get managers team by manager's id
     * @param id of the manager user want to see the team
     * @return Manager and their team.
     */
    public Manager getManagerWithTeam(Long id){
        return managerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + id));
    }
}
