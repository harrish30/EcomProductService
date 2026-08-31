package dev.harrish.EcomProductService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass

public abstract class BaseModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @CreationTimestamp //hibernate's annotation
    private Instant createdAt; //no.of seconds/nanoseconds passed since 1 Jan 1970 UTC
    @UpdateTimestamp //hibernate's annotation
    private Instant updatedAt; //no.of seconds/nanoseconds passed since 1 Jan 1970 UTC
}