package net.koziczak.journal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryDto {

    @NotBlank
    private String title;
    @NotBlank
    private String entry_text;

}