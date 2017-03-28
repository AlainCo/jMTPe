package jmtp.tools.utils;

public class MTPDeviceInfo
{
	private final String model;
	private final String serialNumber;

	public MTPDeviceInfo(final String model, final String serialNumber) {
		this.model = model;
		this.serialNumber = serialNumber;
	}

	@Override
	public String toString() {
		return model + " - " + serialNumber;
	}
}
