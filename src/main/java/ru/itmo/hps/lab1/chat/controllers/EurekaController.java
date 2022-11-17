package ru.itmo.hps.lab1.chat.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EurekaController {

    private final DiscoveryClient discoveryClient;

//    @GetMapping("/services/{applicationName}")
//    public List<ServiceInstance> serviceInstancesByApplicationName(
//            @PathVariable String applicationName) {
//        return this.discoveryClient.getInstances(applicationName);
//    }
//
//    @GetMapping("/services")
//    public List<String> servicesList() {
//        return this.discoveryClient.getServices();
//    }

}
