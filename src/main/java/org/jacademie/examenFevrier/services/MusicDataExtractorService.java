package org.jacademie.examenFevrier.services;

import java.io.Reader;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MusicDataExtractorService {
	public List<MusicData> getDatas(Reader reader) throws MusicDataException;
}
