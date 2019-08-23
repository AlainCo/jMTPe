package jmtp;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
// folder or storage
public interface PortableDeviceContainerObject extends PortableDeviceObject {

	PortableDeviceObject[] getChildObjects();

	PortableDeviceAudioObject addAudioObject(File bestand, String artist, String title, BigInteger duration) throws IOException;

	PortableDeviceAudioObject addAudioObject(File file, String artist, String title, BigInteger duration, String genre, String album, Date releaseDate,
			int track) throws IOException;

	PortableDevicePlaylistObject createPlaylistObject(String name, PortableDeviceObject[] references);

	PortableDeviceFolderObject createFolderObject(String name);

}