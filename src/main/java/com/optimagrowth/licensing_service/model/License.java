package com.optimagrowth.licensing_service.model;

import lombok.*;
import org.springframework.hateoas.*;

@Getter
@Setter @ToString
public class License extends RepresentationModel<License> {
    private int id;
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
}