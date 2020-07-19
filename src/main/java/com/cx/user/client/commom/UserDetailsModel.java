package com.cx.user.client.commom;

import com.cx.user.entity.UserBaseInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserDetailsModel implements UserDetails {

    private static final long serialVersionUID = 1998415861886139915L;

    private UserBaseInfo UserBaseInfo;

    private String sessionId;

    private Set<String> roles;

    private Set<String> permissions;

    public UserBaseInfo getUserBaseInfo() {
        return UserBaseInfo;
    }

    public void setUserBaseInfo(UserBaseInfo UserBaseInfo) {
        this.UserBaseInfo = UserBaseInfo;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        return UserBaseInfo.getLoginPassword();
    }

    @Override
    public String getUsername() {
        return UserBaseInfo.getLoginName();
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
        return UserBaseInfo.getUserStatus().intValue() == 1;
    }

}
