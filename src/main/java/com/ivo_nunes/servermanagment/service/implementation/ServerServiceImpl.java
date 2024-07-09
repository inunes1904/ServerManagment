package com.ivo_nunes.servermanagment.service.implementation;

import com.ivo_nunes.servermanagment.enumeration.Status;
import com.ivo_nunes.servermanagment.model.Server;
import com.ivo_nunes.servermanagment.repo.ServerRepository;
import com.ivo_nunes.servermanagment.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Optional;

import static com.ivo_nunes.servermanagment.enumeration.Status.SERVER_DOWN;
import static com.ivo_nunes.servermanagment.enumeration.Status.SERVER_UP;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

  private final ServerRepository serverRepository;

    @Override
  public Server create(Server server) {
    log.info("Saving a new server: {}", server.getName());
    server.setImgUrl(setServerImageUrl());
    return serverRepository.save(server);
  }

  @Override
  public Server ping(String ipAddress) throws IOException {
    log.info("Pinging server with the IP: {}", ipAddress);
    Server server = serverRepository.findByIpAddress(ipAddress);
    InetAddress address = InetAddress.getByName(ipAddress);
    server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
    serverRepository.save(server);
    return server;
  }

  @Override
  public Collection<Server> list(int limit) {
      log.info("Fetching all servers with the limit of: {}", limit);
    return serverRepository.findAll(PageRequest.of(0,limit)).toList();
  }

  @Override
  public Optional<Server> get(Long id) {
      log.info("Fetching the server with the id: {}", id);
    return serverRepository.findById(id);
  }

  @Override
  public Server update(Server server) {
      log.info("Updating the server with the server: {}", server.getName());
    return serverRepository.save(server);
  }

  @Override
  public Boolean delete(Long id) {
      log.info("Deleting the server with the id: {}", id);
      serverRepository.deleteById(id);
      return true;
  }

  private String setServerImageUrl() {
    return null;
  }

}
