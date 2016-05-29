/**
 * class to create exporter classes
 */
package bl;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class ExporterFactory {

	/**
	 * constructor
	 */
	public ExporterFactory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * creates and returns a desired element that implements Exporter interface depending on type value
	 * @param type - if 0 creates a JSONExporter, otherwise creates a CSVExporter
	 * @return an element that implements Exporter interface
	 */
	public Exporter createExporter(int type)
	{
		if (type == 0)
		{
			return new JSONExporter();
		}
		else
		{
			return new CSVExporter();
		}
	}

}
