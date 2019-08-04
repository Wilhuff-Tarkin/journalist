package net.koziczak.journal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.koziczak.journal.model.User;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class EntryDto {

    @NotBlank
    private String title;
    @NotBlank
    private String entry_text;

}