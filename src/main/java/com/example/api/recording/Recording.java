package com.example.api.recording;

import javax.persistence.*;


public class Recording {

    @SequenceGenerator(
            name = "recording_sequence",
            sequenceName = "recording_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recording_sequence"
    )

    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String recording;

    public Recording() {

    }

    public Recording(Long id, String recording) {
        this.id = id;
        this.recording = recording;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }
}
