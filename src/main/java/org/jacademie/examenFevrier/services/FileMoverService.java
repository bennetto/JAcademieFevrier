package org.jacademie.examenFevrier.services;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Repository;

@Repository
public interface FileMoverService {
	public void moveFileToFolder(File file, String destinationPath) throws IOException;
}
