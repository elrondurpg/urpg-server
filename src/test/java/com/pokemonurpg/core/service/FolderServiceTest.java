package com.pokemonurpg.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FolderServiceTest {
    private final static String OLDPATH = "/old/path";
    private static File OLDFILE;

    private final static String NEWPATH = "/test/path";
    private static File NEWFILE;

    @InjectMocks
    private FolderService folderService;

    @Mock
    private FileService fileService;

    @BeforeEach
    public void init() {
        NEWFILE = mock(File.class);
        when(fileService.findByName(NEWPATH)).thenReturn(NEWFILE);
        when(NEWFILE.mkdir()).thenReturn(true);

        OLDFILE = mock(File.class);
        when(fileService.findByName(OLDPATH)).thenReturn(OLDFILE);
        when(OLDFILE.renameTo(NEWFILE)).thenReturn(true);
    }

    @Test
    public void createCallsFileMkdir() {
        boolean created = folderService.create(NEWPATH);
        assertTrue(created);
        verify(NEWFILE, times(1)).mkdir();
    }

    @Test
    public void createReturnsFalseWhenPathIsNull() {
        assertFalse(folderService.create(null));
    }

    @Test
    public void renameReturnsTrueWhenNewPathIsNull() {
        assertTrue(folderService.rename(null, null));
    }

    @Test
    public void renameReturnsTrueWhenOldAndNewPathAreEqual() {
        assertTrue(folderService.rename(NEWPATH, NEWPATH));
    }

    @Test
    public void renameReturnsFalseWhenNewFileExists() {
        when(NEWFILE.exists()).thenReturn(true);
        assertFalse(folderService.rename(OLDPATH, NEWPATH));
    }

    @Test
    public void renameReturnsTrueWhenOldFileExistsAndNewFileDoesnt() {
        when(NEWFILE.exists()).thenReturn(false);
        when(OLDFILE.exists()).thenReturn(true);
        assertTrue(folderService.rename(OLDPATH, NEWPATH));
        verify(OLDFILE, times(1)).renameTo(NEWFILE);
    }

    @Test
    public void renameCreatesWhenOldFileAndNewFileDontExist() {
        when(NEWFILE.exists()).thenReturn(false);
        when(OLDFILE.exists()).thenReturn(false);
        assertTrue(folderService.rename(OLDPATH, NEWPATH));
        verify(NEWFILE, times(1)).mkdir();
    }

}