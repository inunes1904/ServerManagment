package com.ivo_nunes.servermanagment.resource;

import com.ivo_nunes.servermanagment.model.Response;
import com.ivo_nunes.servermanagment.model.Server;
import com.ivo_nunes.servermanagment.service.implementation.ServerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static com.ivo_nunes.servermanagment.enumeration.Status.SERVER_UP;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

  private final ServerServiceImpl serverService;

  @GetMapping("/list")
  public ResponseEntity<Response> getServers(){
   return ResponseEntity.ok(
     Response.builder()
       .timeStamp(now())
       .data(Map.of("servers", serverService.list(30)))
       .message("Servers Retrieved")
       .status(OK)
       .statusCode(OK.value())
       .build()
   );
  }
  @GetMapping("/ping/{ipAddress}")
  public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
    Server server = serverService.ping(ipAddress);
    return ResponseEntity.ok(
     Response.builder()
       .timeStamp(now())
       .data(Map.of("servers", serverService.list(30)))
       .message(server.getStatus() == SERVER_UP ? "PING SUCCESS" : "PING FAILED")
       .status(OK)
       .statusCode(OK.value())
       .build()
   );
  }
  @PostMapping("/save")
  public ResponseEntity<Response> pingServer(@RequestBody @Valid Server server) {
   return ResponseEntity.ok(
     Response.builder()
       .timeStamp(now())
       .data(Map.of("server", serverService.create(server)))
       .message("SERVER CREATED")
       .status(CREATED)
       .statusCode(CREATED.value())
       .build()
   );
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
    return ResponseEntity.ok(
      Response.builder()
        .timeStamp(now())
        .data(Map.of("server", serverService.get(id)))
        .message("SERVER RETRIEVED")
        .status(OK)
        .statusCode(OK.value())
        .build()
    );
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
    return ResponseEntity.ok(
      Response.builder()
        .timeStamp(now())
        .data(Map.of("deleted", serverService.delete(id)))
        .message("SERVER DELETED")
        .status(OK)
        .statusCode(OK.value())
        .build()
    );
  }
  @GetMapping(path="/image/{fileName}", produces = IMAGE_PNG_VALUE)
  public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
    return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Downloads/images/"+fileName));
  }

}
