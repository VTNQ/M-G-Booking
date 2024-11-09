package com.mgbooking.server.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "SPRING_SESSION_ATTRIBUTES")
public class SpringSessionAttribute {
    @EmbeddedId
    private SpringSessionAttributeId id;

    @MapsId("sessionPrimaryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "SESSION_PRIMARY_ID", nullable = false)
    private SpringSession sessionPrimary;

    @Column(name = "ATTRIBUTE_BYTES", nullable = false)
    private byte[] attributeBytes;

    public SpringSessionAttributeId getId() {
        return id;
    }

    public void setId(SpringSessionAttributeId id) {
        this.id = id;
    }

    public SpringSession getSessionPrimary() {
        return sessionPrimary;
    }

    public void setSessionPrimary(SpringSession sessionPrimary) {
        this.sessionPrimary = sessionPrimary;
    }

    public byte[] getAttributeBytes() {
        return attributeBytes;
    }

    public void setAttributeBytes(byte[] attributeBytes) {
        this.attributeBytes = attributeBytes;
    }

}