package com.project.ilyasMahfudSkripsi.module.authentication.repository;

import com.project.ilyasMahfudSkripsi.module.authentication.entity.Role;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    List<User> findByRole(Role role);

    @Query(value = "SELECT fullName " +
            "FROM \n" +
            "user_details "+
            "INNER JOIN users\n" +
            "ON users.user_id=user_details.user_id\n" +
            "WHERE fullName LIKE %:fullName% ", nativeQuery = true)
    String findbyEmailForFullname(@Param("fullName") String email);
}
