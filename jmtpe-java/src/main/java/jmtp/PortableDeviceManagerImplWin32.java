/*
 * Copyright 2007 Pieter De Rycke
 * 
 * This file is part of JMTP.
 * 
 * JTMP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of 
 * the License, or any later version.
 * 
 * JMTP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU LesserGeneral Public 
 * License along with JMTP. If not, see <http://www.gnu.org/licenses/>.
 */

package jmtp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import be.derycke.pieter.com.COM;
import be.derycke.pieter.com.COMException;
import be.derycke.pieter.com.COMReference;

/**
 * TODO phantomreferences -> gebruiken
 * 
 * @author Pieter De Rycke
 */
class PortableDeviceManagerImplWin32 implements PortableDeviceManagerProxy {

	static {
		if (System.getProperty("os.arch").contains("64")) {
			System.out.println("Loading 64bit library");
			System.loadLibrary("jmtp64");
		} else {
			System.out.println("Loading 32bit library");
			System.loadLibrary("jmtp32");
		}

	}

	private COMReference pDeviceManager;

	private Map<String, PortableDeviceImplWin32> deviceMap;

	public PortableDeviceManagerImplWin32() {
		try {
			pDeviceManager = COM.CoCreateInstance(WPDImplWin32.CLSID_PortableDeviceManager,
					0, COM.CLSCTX_INPROC_SERVER, WPDImplWin32.IID_IPortableDeviceManager);
			deviceMap = new HashMap<>();
		} catch (Exception e) {
			throw new RuntimeException("probleem met de com");
		}
	}

	private native String[] getDevicesImpl() throws COMException;

	private native void refreshDeviceListImpl() throws COMException;

	@Override
	public PortableDeviceImplWin32[] getDevices() {
		try {
			String[] devices = getDevicesImpl();

			Set<String> deviceSet = new HashSet<>();
			for (String deviceID : devices) {
				if (!deviceMap.containsKey(deviceID))
					deviceMap.put(deviceID, new PortableDeviceImplWin32(pDeviceManager, deviceID));

				deviceSet.add(deviceID);
			}

			for (String deviceID : deviceMap.keySet())
				if (!deviceSet.contains(deviceID))
					deviceMap.remove(deviceID);

			return deviceMap.values().toArray(new PortableDeviceImplWin32[0]);
		} catch (COMException e) {
			e.printStackTrace();
			return new PortableDeviceImplWin32[0];
		}
	}

	@Override
	public void refreshDeviceList() {
		try {
			refreshDeviceListImpl();
		} catch (COMException e) {
			e.printStackTrace();
		}
	}

	public Iterator<PortableDevice> iterator() {
		return new PortableDeviceIterator();
	}

	private class PortableDeviceIterator implements Iterator<PortableDevice> {

		private final Iterator<String> iterator;

		public PortableDeviceIterator() {
			getDevices(); //de map met devices updaten
			iterator = deviceMap.keySet().iterator();
		}

		public boolean hasNext() {
			return iterator.hasNext();
		}

		public PortableDevice next() {
			String deviceID = iterator.next();
			return deviceMap.get(deviceID);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Devices can't be removed");
		}

	}
}
