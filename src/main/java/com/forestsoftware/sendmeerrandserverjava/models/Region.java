package com.forestsoftware.sendmeerrandserverjava.models;

import com.forestsoftware.sendmeerrandserverjava.models.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;


@Entity
@Getter
@Setter
public class Region extends DateAudit {
    private String title;
    private boolean available;
}
