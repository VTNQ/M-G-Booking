package com.mgbooking.server.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SpringSessionAttributeId implements Serializable {
    private static final long serialVersionUID = 23343348866575407L;
    @Column(name = "SESSION_PRIMARY_ID", nullable = false, length = 36)
    private String sessionPrimaryId;

    @Column(name = "ATTRIBUTE_NAME", nullable = false, length = 200)
    private String attributeName;

    public String getSessionPrimaryId() {
        return sessionPrimaryId;
    }

    public void setSessionPrimaryId(String sessionPrimaryId) {
        this.sessionPrimaryId = sessionPrimaryId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SpringSessionAttributeId entity = (SpringSessionAttributeId) o;
        return Objects.equals(this.sessionPrimaryId, entity.sessionPrimaryId) &&
                Objects.equals(this.attributeName, entity.attributeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionPrimaryId, attributeName);
    }

}