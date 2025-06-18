    package com.arion.savelinks.entity;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Link {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @NotBlank
        private String link_title;

        @NotBlank
        private String link;

        private String description;

        // Owning side of the relationship
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false) // creates user_id foreign key in Link table
        private User user;
    }
