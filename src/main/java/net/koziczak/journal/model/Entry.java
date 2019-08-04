package net.koziczak.journal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entry_id;
    private LocalDate dateOfEntry = LocalDate.now();
    @NotBlank
    private String title;
    @NotBlank
    @Column(columnDefinition = "text")
    private String entry_text;

    public Entry(@NotBlank String title, @NotBlank String entry_text, User user) {
        this.title = title;
        this.entry_text = entry_text;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;


}
