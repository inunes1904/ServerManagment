package com.ivo_nunes.servermanagment.service;

import com.ivo_nunes.servermanagment.model.Server;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface ServerService {

  Server create(Server server);
  Server ping(String ipAddress);
  Collection<Server> list(int limit);
  Server get(Long id);
  Server update(Server server);
  Boolean delete(Long id);

}
