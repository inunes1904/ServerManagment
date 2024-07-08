package com.ivo_nunes.servermanagment.repo;

import com.ivo_nunes.servermanagment.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
  Server findByIpAddress(String ipAddress);
}
