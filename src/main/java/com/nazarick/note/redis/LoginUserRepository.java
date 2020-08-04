package com.nazarick.note.redis;

import com.nazarick.note.security.domain.LoginUser;
import org.springframework.data.repository.CrudRepository;

public interface LoginUserRepository extends CrudRepository<LoginUser, String> {
}
