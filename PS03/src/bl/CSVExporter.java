/**
 * 
 */
package bl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

import models.Ticket;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class CSVExporter implements Exporter {

	/**
	 * constructor
	 */
	public CSVExporter() {
		
	}

	@Override
	public Boolean export(Integer showId) {
		Boolean ok = false;
		TicketManager tm = new TicketManager();
		CSVWriter writer;
		
		try {
			writer = new CSVWriter(new FileWriter("show.csv"), ',');
			// feed in your array (or convert your data to an array)
		    String[] entries = "id#nr#row".split("#");
		    writer.writeNext(entries);

		    List<Ticket> tickets = tm.getAllTicketsByShowId(showId);
			for (Ticket t : tickets)
			{
				String[] temp = (t.getId() + "#" + t.getNr() + "#" + t.getRow()).split("#");
				writer.writeNext(temp);
			}
			writer.close();
		    
		    ok = true;
		    
		} catch (IOException e) {
			
		}
		
	    return ok;
	}

}
