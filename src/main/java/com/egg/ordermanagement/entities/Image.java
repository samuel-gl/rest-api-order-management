package com.egg.ordermanagement.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
    public class Image {
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        private String id;

        private String mime;
        private String name;

        @Lob @Basic(fetch = FetchType.LAZY)
        private byte[] content;
}
