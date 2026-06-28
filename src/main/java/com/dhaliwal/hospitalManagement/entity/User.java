package com.dhaliwal.hospitalManagement.entity;

import com.dhaliwal.hospitalManagement.entity.type.AuthProviderType;
import com.dhaliwal.hospitalManagement.entity.type.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users",
    indexes = {
        @Index(name = "idx_provider_id_provider_type", columnList = "providerId, providerType")
    }
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JoinColumn(unique = true)
    private String username;

    private String password;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private AuthProviderType providerType;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<RoleType>  roles = new HashSet<>();

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(roles ->
                        new SimpleGrantedAuthority(
                                "ROLE_" + roles.name()
                        )
                )
                .collect(Collectors.toSet());
    }
}
