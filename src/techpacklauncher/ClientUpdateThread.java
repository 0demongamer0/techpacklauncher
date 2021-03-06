/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 */

package truelauncher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class ClientUpdateThread extends Thread {
	// Thread for downloading clients

	private GUI gui;
	private String urlfrom;
	private String packedclientto;
	private String unpackto;

	ClientUpdateThread(GUI gui) {
		try {
			this.gui = gui;
			this.urlfrom = AllSettings.getClientDownloadLinkByName(gui.listdownloads.getSelectedItem().toString());
			this.packedclientto = LauncherUtils.getDir() + File.separator + AllSettings.getCientTempFolderPath() + File.separator + new File(new URL(this.urlfrom).getFile()).getName();
			this.unpackto = LauncherUtils.getDir() + File.separator + AllSettings.getClientUnpackToFolderByName(gui.listdownloads.getSelectedItem().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void filedownloader(String urlfrom, String clientto)
			throws Exception {
		
		new File(packedclientto).getParentFile().mkdirs();
		
		URL url = new URL(urlfrom);
		URLConnection conn = url.openConnection();
		if ((conn instanceof HttpURLConnection)) {
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.connect();
		}
		
		InputStream inputstream = conn.getInputStream();

		FileOutputStream writer = new FileOutputStream(clientto);
		byte[] buffer = new byte[153600];

		long downloadedAmount = 0;
		final long totalAmount = conn.getContentLength();

		gui.pbar.setMaximum((int) totalAmount);
		gui.pbar.setMinimum(0);

		int bufferSize = 0;
		while ((bufferSize = inputstream.read(buffer)) > 0) {
			writer.write(buffer, 0, bufferSize);
			buffer = new byte[153600];
			downloadedAmount += bufferSize;
			final long pbda = downloadedAmount;

			gui.pbar.setValue((int) pbda);

		}

		writer.close();
		inputstream.close();
	}

	@Override
	public void run() {
		try {

			// remove old zip file
			gui.download.setText("Usuń");
			new File(packedclientto).delete();

			// download packed zip
<<<<<<< HEAD
			gui.download.setText("Pobierz paczke");
			filedownloader(urlfrom, packedclientto);

			// delete old client
			gui.download.setText("Usuń starą paczkę");
=======
			gui.download.setText("Pobierz klienta");
			filedownloader(urlfrom, packedclientto);

			// delete old client
			gui.download.setText("Usuń stary klienta");
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
			deleteDirectory(new File(unpackto));
			new File(unpackto).mkdirs();

			// unpack new cient
<<<<<<< HEAD
			gui.download.setText("Wypakuj paczkę");
=======
			gui.download.setText("Wypakuj klienta");
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
			Zip zip = new Zip(gui);
			zip.unpack(packedclientto, unpackto);

			// show finish message
<<<<<<< HEAD
			gui.download.setText("Paczka jest zainstalowana");
=======
			gui.download.setText("klient jest zainstalowany");
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
			gui.listdownloads.setEnabled(true);
			
			//recheck client
			LauncherUtils.checkClientJarExist(gui);

		} catch (final Exception ex) {

			gui.download.setText("Błąd");
			gui.listdownloads.setEnabled(true);
			LauncherUtils.logError(ex);

		}
	}

	public void deleteDirectory(File file) {
		if (!file.exists())
			return;
		if (file.isDirectory()) {
			for (File f : file.listFiles())
				deleteDirectory(f);
			file.delete();
		} else {
			file.delete();
		}
	}

}
