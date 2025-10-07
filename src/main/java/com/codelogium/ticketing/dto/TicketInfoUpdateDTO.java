package com.codelogium.ticketing.dto;

import java.time.Instant;

import com.codelogium.ticketing.entity.enums.Category;
import com.codelogium.ticketing.entity.enums.Priority;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketInfoUpdateDTO {

    // Backward-compatible constructor (before macId was introduced)
    public TicketInfoUpdateDTO(String title, String description, Instant creationDate, Category category, Priority priority) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.category = category;
        this.priority = priority;
        this.macId = null;
    }
    
    @NotBlank(message = "Title cannot be blank or null")
    private String title;

    @NotBlank(message = "Description cannot be blank or null")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant creationDate;

    @NotNull(message = "Category cannot be null")
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotNull(message = "Priority cannot be null")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    // Optional MAC address for hardware/laptop-related tickets
    @Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$", message = "MAC ID must be in format XX:XX:XX:XX:XX:XX or XX-XX-XX-XX-XX-XX")
    private String macId;
}
