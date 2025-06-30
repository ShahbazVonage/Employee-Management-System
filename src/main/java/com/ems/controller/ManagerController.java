package com.ems.controller;

import com.ems.entity.Manager;
import com.ems.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping
    public Manager addManager(@RequestBody Manager manager){
        return managerService.addManager(manager);
    }

    @GetMapping("/{id}/team")
    public Manager getManagerTeam(@PathVariable Long id){
        return managerService.getManagerWithTeam(id);
    }

}
