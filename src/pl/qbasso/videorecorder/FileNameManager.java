package pl.qbasso.videorecorder;

public class FileNameManager {

	private static final String FILE_PREFIX = "video_";
	private static final String FILE_EXTENSION = ".mp4";
	private static final int MAX_FILES = 5;

	private static String sCurrentFileName;
	private static int sFileId;
	private static boolean sOneRound = false;

	static {
		sFileId = 0;
	}

	public static String getNextFile() {
		if (sFileId == MAX_FILES) {
			sFileId = 0;
			sOneRound = true;
		}
		int id = sFileId++ % MAX_FILES;
		StringBuffer b = new StringBuffer();
		b.append(FILE_PREFIX);
		b.append(String.valueOf(id));
		b.append(FILE_EXTENSION);
		sCurrentFileName = b.toString();
		return sCurrentFileName;
	}

	public static String getPrevFile() {
		StringBuffer b = new StringBuffer();
		int id = sFileId - 2;
		b.append(FILE_PREFIX);
		if (id < 0 && !sOneRound) {
			return null;
		}
		if (id < 0) {
			id = MAX_FILES - 1;
		}
		b.append(String.valueOf(id));
		b.append(FILE_EXTENSION);
		return b.toString();
	}

}
