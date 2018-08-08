package com.simbiose.resource;

import com.simbiose.service.MercadoLivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/v1/mercadolivre")
public class Resource {

    @Value("${ml.redirect.uri}")
    private String redirectUri;

    @Autowired
    private MercadoLivreService mercadoLivreService;

    @GetMapping("/{clientId}")
    private void requestAuthorization(
            @PathVariable("clientId") String clientId,
            HttpServletResponse response) throws IOException {

        response.sendRedirect(URI.create("https://auth.mercadolivre.com.br/authorization?response_type=code&client_id=" + clientId).toString());
    }

    @GetMapping("/redirect")
    private ResponseEntity<?> requestToken(@RequestParam("code") String code) throws IOException {
        mercadoLivreService.requestToken(code);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
