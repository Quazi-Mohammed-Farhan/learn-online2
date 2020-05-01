/*
 * TODO: Security work is in pending
 * We will do it later on
 */

/*
 * package com.learn.online.securities;
 * 
 * import java.util.Collection; import java.util.List;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import com.learn.online.dtos.StudentDto;
 * 
 * public class UserPrincipal implements UserDetails {
 * 
 * private String userName; private String password; private boolean active;
 * private List<GrantedAuthority> roles;
 * 
 * public UserPrincipal(StudentDto studentDto) { this.userName =
 * studentDto.getEmail(); this.password = studentDto.getEncryptedPassword();
 * this.active = studentDto.isActive(); }
 * 
 * public UserPrincipal() { super(); }
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
 * return null; }
 * 
 * @Override public String getPassword() { return password; }
 * 
 * @Override public String getUsername() { return userName; }
 * 
 * @Override public boolean isAccountNonExpired() { return true; }
 * 
 * @Override public boolean isAccountNonLocked() { return true; }
 * 
 * @Override public boolean isCredentialsNonExpired() { return true; }
 * 
 * @Override public boolean isEnabled() { return active; }
 * 
 * }
 */