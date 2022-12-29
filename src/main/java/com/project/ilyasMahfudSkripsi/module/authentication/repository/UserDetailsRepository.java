package com.project.ilyasMahfudSkripsi.module.authentication.repository;

import com.project.ilyasMahfudSkripsi.module.authentication.entity.User;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, UUID> {
    Optional<UserDetails> findByUser(User user);
}
