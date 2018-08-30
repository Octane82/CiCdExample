package com.everlapp.cicdexample.repositories;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NameRepositoryTest {



    //private final static File FILE = new File("test_file");

    private final File DIR = new File("test_dir");
    private final File FILE = Paths.get(DIR.toString(), "test_file").toFile();


    /**
     * Класс Rule позволяет задать функции схожие с Before и After (для уменьшения дублирования кода)
     */
    //@Rule
    //public final CreateFileRule fileRule = new CreateFileRule(FILE, "{name: Dima}");

    /**
     * RuleChain необходима, чтобы определить порядок запуска TestRule
     */
    /*@Rule
    public final RuleChain chain = RuleChain
            .outerRule(new CreateDirRule(DIR))
            .around(new CreateFileRule(FILE, "{name: Dima}"));*/


    FileReader fileReader = mock(FileReader.class);

    private NameRepository nameRepository = new NameRepository(fileReader);


    @Before
    public void setUp() throws Exception {
        when(fileReader.readFile()).thenReturn("{name: Dima}");


        /*PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(FILE), "UTF-8")), true);
        writer.write("{name: Dima}");
        writer.close();*/
    }


    /*@After
    public void tearDown() {
        FILE.delete();
    }*/


    @Test
    public void getName_isDima() throws IOException {
        String name = nameRepository.getName();
        assertEquals(name, "Dima");
    }

    @Test
    public void getName_notMia() throws IOException {
        String name = nameRepository.getName();
        assertNotEquals(name, "Mia");
    }



}