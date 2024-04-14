package org.example.micro1.client;

import org.example.micro1.response.ResponseReniec;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "reniec-client", url = "https://api.apis.net.pe/v2/reniec/")
public interface ReniecClient {
    @GetMapping("/dni")
    Optional<ResponseReniec> getInfoReniec(@RequestParam("numero") String numero,
                           @RequestHeader("Authorization") String authorizationHeader);
}
