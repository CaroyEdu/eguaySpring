package com.test.eguay.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Rol {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rolid", nullable = false)
    private long rolid;
    @Basic
    @Column(name = "type", nullable = false)
    private int type;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @OneToMany(mappedBy = "rolByRolid")
    private Collection<UserRol> usersrolsByRolid;

    public long getRolid() {
        return rolid;
    }

    public void setRolid(long rolid) {
        this.rolid = rolid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rol rol = (Rol) o;

        if (rolid != rol.rolid) return false;
        if (type != rol.type) return false;
        if (name != null ? !name.equals(rol.name) : rol.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (rolid ^ (rolid >>> 32));
        result = 31 * result + type;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Collection<UserRol> getUsersrolsByRolid() {
        return usersrolsByRolid;
    }

    public void setUsersrolsByRolid(Collection<UserRol> usersrolsByRolid) {
        this.usersrolsByRolid = usersrolsByRolid;
    }
}
