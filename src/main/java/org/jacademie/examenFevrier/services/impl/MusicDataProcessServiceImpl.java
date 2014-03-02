package org.jacademie.examenFevrier.services.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jacademie.examenFevrier.services.FileMoverService;
import org.jacademie.examenFevrier.services.MusicData;
import org.jacademie.examenFevrier.services.MusicDataException;
import org.jacademie.examenFevrier.services.MusicDataExtractorService;
import org.jacademie.examenFevrier.services.MusicDataProcessService;
import org.jacademie.examenFevrier.services.MusicDataUpdaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("musicDataProcessService")
public class MusicDataProcessServiceImpl implements MusicDataProcessService
{
	private static Logger logger = Logger.getLogger(MusicDataProcessServiceImpl.class);
	@Autowired
	private MusicDataExtractorService dataExtractor;
	@Autowired
	private MusicDataUpdaterService dataUpdater;
	@Autowired
	private FileMoverService fileMover;
	
    
    /* (non-Javadoc)
	 * @see org.jacademie.examenFevrier.services.impl.MusicDataProcessService#updateMusicFromFiles()
	 */
    @Override
	public void updateMusicFromFiles(){

    	logger.info("updateMusicFromFiles");
		Properties prop = new Properties();
		
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("confCSV.properties"));
			String folderPath = prop.getProperty("inputPath");
			String sucessPath = prop.getProperty("successOutputPath");
			String failPath = prop.getProperty("failOutputPath");
			String dateFormat = prop.getProperty("timeExtension");
			String dateExt = new SimpleDateFormat(dateFormat).format(new Date());
			
			File repository = new File(folderPath);
			logger.info(repository.getPath());
			String[] fileNames = repository.list();
			logger.info(fileNames);
			
			/*
		      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			
			logger.info(" new CSVMusicDataExtractor()");
			MusicDataExtractorService dataExtractor = (MusicDataExtractorService) context.getBean("musicDataExtractor");//new CSVMusicDataExtractor();
			logger.info(" new MusicDataUpdaterServiceImpl()");
			MusicDataUpdaterService dataUpdater = (MusicDataUpdaterService) context.getBean("musicDataUpdaterService");//new MusicDataUpdaterServiceImpl();
			logger.info(" new FileMoverServiceImpl()");
			FileMoverService fileMover = (FileMoverService) context.getBean("fileMoverService");//new FileMoverServiceImpl();
			*/
			
			for ( int i=0; i<fileNames.length; i++ ) {
				if ( fileNames[i].toLowerCase().endsWith(".music") == true ) {
					String pathFileMusic = folderPath + "/" + fileNames[i];
					File file = new File(pathFileMusic);
					Reader reader = new FileReader(pathFileMusic);
					try {
						try {
							List<MusicData> datas = dataExtractor.getDatas(reader);
							
								logger.info("datas : "+datas);
							reader.close();
							dataUpdater.update(datas);
							logger.info("Fichier " + fileNames[i] + " mis a jour avec succès");
							
							fileMover.moveFileToFolder(file, sucessPath+dateExt);
						} catch (MusicDataException e) {
							logger.error("Erreur lors de la mise a jour a partir du fichier " + fileNames[i], e);
							reader.close();
							fileMover.moveFileToFolder(file, failPath+dateExt);
						}
					} catch(IOException e){
						reader.close();
						logger.error("Erreur lors du déplacement d'un fichier", e);
					}
				}
			}
		} catch (IOException e) {
			logger.error("Erreur lors de la lecture du fichier", e);
		}
		
    }
}
