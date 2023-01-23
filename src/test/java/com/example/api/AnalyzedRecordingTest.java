package com.example.api;

import com.example.api.recording.AnalyzedRecording;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyzedRecordingTest {
    //TODO: Test no hesitations, one hesitation, multiple hesitations
    //TODO: Test hesitation at start, hesitation at end (hesitation timing)
    //TODO: Test accuracy percentage
    //TODO: Test short recording, long recording
    //TODO: Test hesitations back to back and each type of hesitation
    //TODO: Test setters
    private AnalyzedRecording testRecording;
    private String pathToTestRecordings = "C:\\Users\\willi\\CPSCPersonal\\UmBeGone-API\\src\\test\\java\\com\\example\\api\\testRecordings\\";

    @Test
    public void testNoHesitations() {
        try {
            InputStream stream = new FileInputStream(new File(pathToTestRecordings + "testNoHesi.wav"));
            testRecording = new AnalyzedRecording(stream);
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException!");
        } catch (IOException e) {
            fail("Unexpected IOException!");
        }
        assertEquals(0, testRecording.getNumHesitations());
        assertEquals(0, testRecording.getHesiTimes().length);
    }

    @Test
    public void testOneHesitation() {
        try {
            InputStream stream = new FileInputStream(new File(pathToTestRecordings + "testOneHesi.wav"));
            testRecording = new AnalyzedRecording(stream);
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException!");
        } catch (IOException e) {
            fail("Unexpected IOException!");
        }
        assertEquals(1, testRecording.getNumHesitations());
        assertEquals(1, testRecording.getHesiTimes().length);
    }

    @Test
    public void testMultipleHesitations() {
        try {
            InputStream stream = new FileInputStream(new File(pathToTestRecordings + "testMultiHesi.wav"));
            testRecording = new AnalyzedRecording(stream);

        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException!");
        } catch (IOException e) {
            fail("Unexpected IOException!");
        }
        assertEquals(2, testRecording.getNumHesitations());
        assertEquals(2, testRecording.getHesiTimes().length);
    }

    @Test
    public void testHesitationTimestamp() {
        try {
            InputStream stream = new FileInputStream(new File(pathToTestRecordings + "testOneHesi.wav"));
            testRecording = new AnalyzedRecording(stream);

        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException!");
        } catch (IOException e) {
            fail("Unexpected IOException!");
        }
        assertEquals(1, testRecording.getNumHesitations());
        assertTrue(1.90 <= testRecording.getHesiTimes()[0] && testRecording.getHesiTimes()[0] <= 2.10);
    }

    @Test
    public void testNumWords() {
        try {
            InputStream stream = new FileInputStream(new File(pathToTestRecordings + "testOneHesi.wav"));
            testRecording = new AnalyzedRecording(stream);

        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException!");
        } catch (IOException e) {
            fail("Unexpected IOException!");
        }
        assertEquals(1, testRecording.getNumHesitations());
        assertEquals(7, testRecording.getNumWords());
    }

    @Test
    public void testSetIdOnce() {
        try {
            InputStream stream = new FileInputStream(new File(pathToTestRecordings + "testOneHesi.wav"));
            testRecording = new AnalyzedRecording(stream);

        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException!");
        } catch (IOException e) {
            fail("Unexpected IOException!");
        }
        testRecording.setId(1L);
        assertEquals(1L, testRecording.getId());
    }

    @Test
    public void testSetIdMultipleTimes() {
        try {
            InputStream stream = new FileInputStream(new File(pathToTestRecordings + "testOneHesi.wav"));
            testRecording = new AnalyzedRecording(stream);

        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException!");
        } catch (IOException e) {
            fail("Unexpected IOException!");
        }

        testRecording.setId(1L);
        testRecording.setId(2L);
        assertEquals(2L, testRecording.getId());
    }
}
