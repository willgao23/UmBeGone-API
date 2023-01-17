package com.example.api.recording;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.result.WordResult;
import edu.cmu.sphinx.util.TimeFrame;

import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static edu.cmu.sphinx.linguist.dictionary.Dictionary.SILENCE_SPELLING;

@Entity
@Table
public class AnalyzedRecording {

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
    private Integer numHesitations;
    private Integer numWords;
    private float[] hesiTimes;

    public AnalyzedRecording() {
    }

    public AnalyzedRecording(InputStream audio) throws IOException {
        Integer numWords = 0;
        Integer numHesitations = 0;
        List<TimeFrame> timestamps = new ArrayList<TimeFrame>();
        List<Float> hesitations = new ArrayList<Float>();

        Configuration configuration = new Configuration();

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);

        recognizer.startRecognition(audio);

        SpeechResult result;
        while ((result = recognizer.getResult()) != null) {
            for (WordResult r : result.getWords()) {
                String word = r.getWord().getSpelling();
                if (word.equals("um") || word.equals("uh") || word.equals("er") || word.equals("ah")) {
                    numHesitations++;
                    timestamps.add(r.getTimeFrame());
                } else if (!word.equals(SILENCE_SPELLING)) {
                    numWords++;
                }
            }
        }

        this.numHesitations = numHesitations;
        this.numWords = numWords;
        for (TimeFrame t : timestamps) {
            float start = (float) t.getStart() / 1000;
            hesitations.add(start);
        }

        int len =  hesitations.size();
        this.hesiTimes = new float[len];

        for (int i = 0; i < len; i++) {
            this.hesiTimes[i] = hesitations.get(i);
        }

    }

    public Integer getNumHesitations() {
        return this.numHesitations;
    }

    public Integer getNumWords() {
        return this.numWords;
    }

    public float[] getHesiTimes() {
        return this.hesiTimes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
