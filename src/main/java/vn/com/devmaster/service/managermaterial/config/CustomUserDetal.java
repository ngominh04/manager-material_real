package vn.com.devmaster.service.managermaterial.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.com.devmaster.service.managermaterial.projecttion.ICustomer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetal implements UserDetails {
    private String username;
    private String pasword;
    private String role;

    public CustomUserDetal(ICustomer iCustomer, String admin, String admin1) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(this.role);
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.pasword;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
