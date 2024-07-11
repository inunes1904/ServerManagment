package com.ivo_nunes.servermanagment.resource;

import com.ivo_nunes.servermanagment.enumeration.Status;
import com.ivo_nunes.servermanagment.model.Response;
import com.ivo_nunes.servermanagment.model.Server;
import com.ivo_nunes.servermanagment.service.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import static com.ivo_nunes.servermanagment.enumeration.Status.SERVER_UP;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

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

}
