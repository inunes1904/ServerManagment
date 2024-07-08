package com.ivo_nunes.servermanagment.service.implementation;

import com.ivo_nunes.servermanagment.model.Server;
import com.ivo_nunes.servermanagment.repo.ServerRepository;
import com.ivo_nunes.servermanagment.service.ServerService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ServerServiceImpl implements ServerService {

  ServerRepository serverRepository;

  @Override
  public Server create(Server server) {
    return null;
  }

  @Override
  public Server ping(String ipAddress) {
    return null;
  }

  @Override
  public Collection<Server> list(int limit) {
    return null;
  }

  @Override
  public Server get(Long id) {
    return null;
  }

  @Override
  public Server update(Server server) {
    return null;
  }

  @Override
  public Boolean delete(Long id) {
    return null;
  }
}
