package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtil {
	private File file;

	public FileUtil(File file) {
		if (file.exists()) {
			this.file = file;
		}
	}

	public FileUtil(String filePath) {
		File file = new File(filePath);

		if (file.exists()) {
			this.file = file;
		}
	}

	public File getFile() {
		return file;
	}

	public Properties getProperties() throws IOException {
		File file = this.getFile();
		FileInputStream reader = new FileInputStream(file);
		Properties props = new Properties();

		props.load(reader);

		return props;
	}
}
