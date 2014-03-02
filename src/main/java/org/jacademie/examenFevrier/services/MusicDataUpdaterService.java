package org.jacademie.examenFevrier.services;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MusicDataUpdaterService {
	public void update() throws MusicDataException;
	public void update(List<MusicData> datas) throws MusicDataException;
}
