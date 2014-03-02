package org.jacademie.examenFevrier.services.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.jacademie.examenFevrier.services.MusicData;
import org.jacademie.examenFevrier.services.MusicDataException;
import org.jacademie.examenFevrier.services.MusicDataExtractorService;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;

@Service("musicDataExtractor")
public class CSVMusicDataExtractor implements MusicDataExtractorService {

	CSVReader csvReader;
	public CSVMusicDataExtractor() {
	}

	@Override
	public List<MusicData> getDatas(Reader reader) throws MusicDataException {
		csvReader = new CSVReader(reader);
		
		List<MusicData> datas = new ArrayList<MusicData>();
		String[] rowAsTokens;
		
		try {
			while ((rowAsTokens = csvReader.readNext()) != null) {
				if(rowAsTokens.length != 7){
					MusicDataException dataException = new MusicDataException("Error while reading CSV File: Misformated row");
					throw dataException;
				}
				MusicData data = new MusicData();
				data.setCodeArtist(Integer.parseInt(rowAsTokens[0]) );
				data.setNomArtiste(rowAsTokens[1] );
				data.setCodeAlbum(Integer.parseInt(rowAsTokens[2]) );
				data.setNomAlbum(rowAsTokens[3] );
				data.setNumeroChanson(Integer.parseInt(rowAsTokens[4]) );
				data.setTitreChanson(rowAsTokens[5] );
				data.setDureeChanson(Integer.parseInt(rowAsTokens[6]) );
				datas.add(data);
			}
			return datas;
			
		} catch (IOException e) {
			MusicDataException dataException = new MusicDataException("Error while reading CSV File");
			throw dataException;
		} catch (NumberFormatException e){
			MusicDataException dataException = new MusicDataException("Cannot parse string as int");
			throw dataException;
		}
	}

}
