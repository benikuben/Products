package ru.neoflex.products.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AuthValidService", url = "${auth.url}", path = "/auth")
public interface AuthValidServiceClient {
    @PostMapping("/token/validate")
    ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authorization,
                                          @RequestHeader("referer") String referer);
}
